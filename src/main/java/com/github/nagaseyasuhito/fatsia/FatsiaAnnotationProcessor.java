package com.github.nagaseyasuhito.fatsia;

import java.io.IOException;
import java.io.Writer;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.persistence.Entity;
import javax.tools.JavaFileObject;

import com.github.nagaseyasuhito.fatsia.criteria.Criteria;
import com.github.nagaseyasuhito.fatsia.criteria.EntityCriteria;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

@SupportedSourceVersion(SourceVersion.RELEASE_6)
@SupportedAnnotationTypes("javax.persistence.Entity")
public class FatsiaAnnotationProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment environment) {
        Set<? extends Element> elements = environment.getElementsAnnotatedWith(Entity.class);

        try {
            for (Element element : elements) {
                this.buildClass((TypeElement) element);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public void buildClass(TypeElement element) throws IOException {
        CharSequence className = element.getSimpleName();
        CharSequence packageName = ((PackageElement) element.getEnclosingElement()).getQualifiedName();
        CharSequence fqcn = element.getQualifiedName();

        StringBuffer buffer = new StringBuffer();
        buffer.append("package " + packageName + ".criteria;");
        buffer.append("public class " + className + "Criteria extends " + EntityCriteria.class.getCanonicalName() + "<" + fqcn + "> {");
        buffer.append(this.buildSource(element));
        buffer.append("}");

        JavaFileObject javaFileObject = this.processingEnv.getFiler().createSourceFile(packageName + ".criteria." + className + "Criteria");
        Writer javaWriter = javaFileObject.openWriter();
        javaWriter.write(buffer.toString());
        javaWriter.close();
    }

    public CharSequence buildSource(TypeElement element) {
        Types types = this.processingEnv.getTypeUtils();

        StringBuffer buffer = new StringBuffer();

        Element currentElement = element;

        List<Element> elements = Lists.newArrayList();
        while (currentElement instanceof TypeElement) {
            elements.addAll(currentElement.getEnclosedElements());
            currentElement = types.asElement(((TypeElement) currentElement).getSuperclass());
        }

        final Collection<ExecutableElement> methods = Collections2.transform(Collections2.filter(elements, new Predicate<Element>() {
            @Override
            public boolean apply(Element input) {
                return input instanceof ExecutableElement;
            }
        }), new Function<Element, ExecutableElement>() {
            @Override
            public ExecutableElement apply(Element input) {
                return (ExecutableElement) input;
            }
        });

        Collection<ExecutableElement> setters = Collections2.filter(methods, new Predicate<ExecutableElement>() {
            @Override
            public boolean apply(ExecutableElement input) {
                return FatsiaAnnotationProcessor.this.isSetter(input);
            }
        });

        for (ExecutableElement setter : setters) {
            ExecutableElement getter = this.obtainPairedGetter(setter, methods);
            if (getter == null) {
                continue;
            }

            CharSequence parameter = setter.getParameters().get(0).getSimpleName();
            DeclaredType type = (DeclaredType) getter.getReturnType();

            buffer.append("private " + this.buildType(type) + parameter + ";");

            buffer.append("public void ");
            buffer.append(setter.getSimpleName());
            buffer.append("(" + this.buildType(type) + " value) ");
            buffer.append("{ this." + parameter + " = value; }");

            buffer.append("public " + this.buildType(type));
            buffer.append(getter.getSimpleName());
            buffer.append("() ");
            buffer.append("{ return this." + parameter + "; }");
        }

        return buffer;
    }

    public CharSequence buildType(DeclaredType type) {
        Types types = this.processingEnv.getTypeUtils();
        Elements elements = this.processingEnv.getElementUtils();

        TypeElement collection = elements.getTypeElement(Collection.class.getCanonicalName());
        if (types.isAssignable(type, types.erasure(collection.asType()))) {
            return type.asElement() + "<" + Criteria.class.getCanonicalName() + "<" + Joiner.on(",").join(type.getTypeArguments()) + ">>";
        } else {
            return Criteria.class.getCanonicalName() + "<? extends " + type + ">";
        }
    }

    public boolean isSetter(ExecutableElement element) {
        if (!element.getSimpleName().toString().startsWith("set")) {
            return false;
        }
        if (element.getReturnType().getKind() != TypeKind.VOID) {
            return false;
        }
        if (element.getParameters().size() != 1) {
            return false;
        }

        return true;
    }

    public ExecutableElement obtainPairedGetter(final ExecutableElement setter, Collection<ExecutableElement> methods) {
        return Iterables.find(methods, new Predicate<ExecutableElement>() {
            @Override
            public boolean apply(ExecutableElement input) {
                String methodName = input.getSimpleName().toString();
                if (!methodName.startsWith("get") && !methodName.startsWith("is")) {
                    return false;
                }
                if (input.getParameters().size() != 0) {
                    return false;
                }

                return methodName.substring(methodName.startsWith("get") ? 3 : 2).equals(setter.getSimpleName().toString().substring(3));
            }
        }, null);
    }
}
