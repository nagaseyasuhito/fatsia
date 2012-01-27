package com.github.nagaseyasuhito.fatsia;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.persistence.Entity;
import javax.tools.JavaFileObject;

@SupportedSourceVersion(SourceVersion.RELEASE_6)
@SupportedAnnotationTypes("javax.persistence.Entity")
public class FatsiaAnnotationProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment environment) {
        Set<? extends Element> elements = environment.getElementsAnnotatedWith(Entity.class);

        try {
            for (Element element : elements) {
                if (element.getKind() != ElementKind.CLASS) {
                    continue;
                }

                TypeElement clazz = (TypeElement) element;
                String andClassName = clazz.getSimpleName() + "$And";

                Writer writer = new StringWriter();

                writer.write("package " + ((PackageElement) clazz.getEnclosingElement()).getQualifiedName() + ";\n\n");
                writer.write("public class " + andClassName + "{}");
                writer.close();

                System.out.println(writer.toString());

                JavaFileObject file = this.processingEnv.getFiler().createSourceFile(andClassName, new Element[0]);
                Writer javaWriter = file.openWriter();
                javaWriter.write(writer.toString());
                javaWriter.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
