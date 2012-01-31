package com.github.nagaseyasuhito.fatsia;

import java.io.IOException;
import java.io.Writer;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
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
import com.github.nagaseyasuhito.fatsia.criteria.OperationalCriteria;


@SupportedSourceVersion(SourceVersion.RELEASE_6)
@SupportedAnnotationTypes("javax.persistence.Entity")
public class FatsiaAnnotationProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment environment) {
        Set<? extends Element> elements = environment.getElementsAnnotatedWith(Entity.class);

        try {
            for (Element element : elements) {
                TypeElement clazz = (TypeElement) element;

                CharSequence fqcn = clazz.getQualifiedName();
                CharSequence packageName = ((PackageElement) clazz.getEnclosingElement()).getQualifiedName();
                CharSequence className = clazz.getSimpleName();

                StringBuffer buffer = new StringBuffer();
                buffer.append("package " + packageName + ";");
                buffer.append("public class " + className + "Criteria {");
                for (Criteria criteria : ConditionalCriteria.values()) {
                    buffer.append(this.buildSource(criteria, Class.forName(clazz.getQualifiedName().toString())));
                }
                buffer.append("}");

                JavaFileObject javaFileObject = this.processingEnv.getFiler().createSourceFile(fqcn + "Criteria");
                Writer javaWriter = javaFileObject.openWriter();
                javaWriter.write(buffer.toString());
                javaWriter.close();
            }

            for (Class<?> clazz : new Class[] {CharSequence.class, Number.class, Date.class, Time.class, Timestamp.class }) {
                CharSequence packageName = this.getClass().getPackage().getName() + ".lang";
                CharSequence className = clazz.getSimpleName();

                StringBuffer buffer = new StringBuffer();
                buffer.append("package " + packageName + ";");
                buffer.append("public class " + className + "Criteria {");
                for (Criteria criteria : OperationalCriteria.values()) {
                    buffer.append(this.buildSource(criteria, clazz));
                }
                buffer.append("}");

                JavaFileObject javaFileObject = this.processingEnv.getFiler().createSourceFile(packageName + "." + className + "Criteria");
                Writer javaWriter = javaFileObject.openWriter();
                javaWriter.write(buffer.toString());
                javaWriter.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public CharSequence buildSource(Criteria criteria, Class<?> clazz) throws IOException {
        CharSequence fqcn = clazz.getCanonicalName();

        StringBuffer buffer = new StringBuffer();
        buffer.append("public static class " + criteria.getCriteriaClass().getSimpleName());
        if (clazz.isInterface()) {
            buffer.append(" implements " + fqcn);
            buffer.append(", " + criteria.getCriteriaClass().getCanonicalName());
        } else {
            buffer.append(" extends " + fqcn);
            buffer.append(" implements " + criteria.getCriteriaClass().getCanonicalName());
        }
        buffer.append("<" + fqcn + ">");
        buffer.append("{");
        buffer.append(criteria.buildMethods(fqcn));
        buffer.append("}");

        return buffer;
    }
}
