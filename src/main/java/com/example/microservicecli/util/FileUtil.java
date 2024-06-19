package com.example.microservicecli.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtil {

    public static void createTemplateStructure(String templateDir) throws IOException {
        createDirectories(templateDir);
        createFiles(templateDir);
    }

    private static void createDirectories(String templateDir) {
        new File(templateDir + "/src/main/java/com/example/microservice").mkdirs();
        new File(templateDir + "/src/main/resources").mkdirs();
    }

    private static void createFiles(String templateDir) throws IOException {
        createJavaFile(templateDir + "/src/main/java/com/example/microservice/MyMicroserviceApplication.java");
        createYamlFile(templateDir + "/src/main/resources/application.yml");
        createPomFile(templateDir + "/pom.xml");
    }

    private static void createJavaFile(String filePath) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("package com.example.microservice;\n\n");
            writer.write("import org.springframework.boot.SpringApplication;\n");
            writer.write("import org.springframework.boot.autoconfigure.SpringBootApplication;\n\n");
            writer.write("@SpringBootApplication\n");
            writer.write("public class MyMicroserviceApplication {\n\n");
            writer.write("    public static void main(String[] args) {\n");
            writer.write("        SpringApplication.run(MyMicroserviceApplication.class, args);\n");
            writer.write("    }\n");
            writer.write("}\n");
        }
    }

    private static void createYamlFile(String filePath) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("spring:\n");
            writer.write("  application:\n");
            writer.write("    name: my-microservice\n");
            writer.write("server:\n");
            writer.write("  port: 8080\n");
        }
    }

    private static void createPomFile(String filePath) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("<project xmlns=\"http://maven.apache.org/POM/4.0.0\"\n");
            writer.write("         xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n");
            writer.write("         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\n");
            writer.write("    <modelVersion>4.0.0</modelVersion>\n");
            writer.write("    <groupId>com.example</groupId>\n");
            writer.write("    <artifactId>my-microservice</artifactId>\n");
            writer.write("    <version>0.0.1-SNAPSHOT</version>\n");
            writer.write("    <packaging>jar</packaging>\n\n");
            writer.write("    <name>my-microservice</name>\n");
            writer.write("    <description>A new microservice</description>\n\n");
            writer.write("    <parent>\n");
            writer.write("        <groupId>org.springframework.boot</groupId>\n");
            writer.write("        <artifactId>spring-boot-starter-parent</artifactId>\n");
            writer.write("        <version>2.7.5</version>\n");
            writer.write("        <relativePath/> <!-- lookup parent from repository -->\n");
            writer.write("    </parent>\n\n");
            writer.write("    <properties>\n");
            writer.write("        <java.version>17</java.version>\n");
            writer.write("    </properties>\n\n");
            writer.write("    <dependencies>\n");
            writer.write("        <dependency>\n");
            writer.write("            <groupId>org.springframework.boot</groupId>\n");
            writer.write("            <artifactId>spring-boot-starter</artifactId>\n");
            writer.write("        </dependency>\n");
            writer.write("    </dependencies>\n\n");
            writer.write("    <build>\n");
            writer.write("        <plugins>\n");
            writer.write("            <plugin>\n");
            writer.write("                <groupId>org.springframework.boot</groupId>\n");
            writer.write("                <artifactId>spring-boot-maven-plugin</artifactId>\n");
            writer.write("            </plugin>\n");
            writer.write("        </plugins>\n");
            writer.write("    </build>\n");
            writer.write("</project>\n");
        }
    }
}
