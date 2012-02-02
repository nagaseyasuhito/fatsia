package com.github.nagaseyasuhito.fatsia;

import java.io.IOException;
import java.io.Writer;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.persistence.Entity;
import javax.tools.JavaFileObject;

import com.github.nagaseyasuhito.fatsia.criteria.ConditionalCriteria;
import com.github.nagaseyasuhito.fatsia.criteria.Criteria;

@SupportedSourceVersion(SourceVersion.RELEASE_6)
@SupportedAnnotationTypes("javax.persistence.Entity")
public class FatsiaAnnotationProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment environment) {
        Set<? extends Element> elements = environment.getElementsAnnotatedWith(Entity.class);

        try {
            for (Element element : elements) {
                this.buildClass((TypeElement) element, ConditionalCriteria.values());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public void buildClass(TypeElement element, Criteria[] criterias) throws IOException {
        CharSequence className = element.getSimpleName();
        CharSequence packageName = ((PackageElement) element.getEnclosingElement()).getQualifiedName();

        StringBuffer buffer = new StringBuffer();
        buffer.append("package " + packageName + ";");
        buffer.append("public class " + className + "Criteria {");
        for (Criteria criteria : criterias) {
            buffer.append(this.buildSource(criteria, element));
        }
        buffer.append("}");

        JavaFileObject javaFileObject = this.processingEnv.getFiler().createSourceFile(packageName + "." + className + "Criteria");
        Writer javaWriter = javaFileObject.openWriter();
        javaWriter.write(buffer.toString());
        javaWriter.close();
    }

    public CharSequence buildSource(Criteria criteria, TypeElement element) throws IOException {
        CharSequence fqcn = element.getQualifiedName();

        StringBuffer buffer = new StringBuffer();
        buffer.append("public static class " + criteria.getCriteriaClass().getSimpleName());
        buffer.append(" extends " + fqcn);
        buffer.append(" implements " + criteria.getCriteriaClass().getCanonicalName());
        buffer.append("<" + fqcn + ">");
        buffer.append("{");
        buffer.append(criteria.buildMethods(fqcn));
        buffer.append("}");

        return buffer;
    }
}
