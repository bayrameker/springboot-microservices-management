package com.example.microservicecli.service;

import com.example.microservicecli.util.FileUtil;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class MicroserviceService {

    private final Map<String, Integer> microservices = new HashMap<>();
    private final Random random = new Random();

    public String createMicroservice(String name, boolean enableCommunication) {
        if (microservices.containsKey(name)) {
            return "Microservice with name " + name + " already exists.";
        }

        int port = findAvailablePort();
        if (port == -1) {
            return "No available port found.";
        }
        microservices.put(name, port);

        // Yeni mikroservis projesi oluşturma
        String templateDir = "microservice-template";
        String newServiceDir = "microservices/" + name;

        try {
            // Şablon dizinini oluştur
            FileUtil.createTemplateStructure(templateDir);
            copyTemplate(templateDir, newServiceDir);
            // Portu ayarlamak için application.yml dosyasını güncelleme
            Path applicationYamlPath = Paths.get(newServiceDir, "src/main/resources/application.yml");
            String content = Files.readString(applicationYamlPath);
            content = content.replace("my-microservice", name);
            content = content.replace("8080", String.valueOf(port));
            Files.writeString(applicationYamlPath, content);

            // Diğer servislerle haberleşmeyi etkinleştirme
            if (enableCommunication) {
                enableServiceCommunication(newServiceDir);
            }

        } catch (IOException e) {
            return "Error creating microservice: " + e.getMessage();
        }

        return "Microservice " + name + " created on port " + port;
    }

    private int findAvailablePort() {
        for (int i = 0; i < 1000; i++) {
            int port = 8000 + random.nextInt(1000);
            if (isPortAvailable(port)) {
                return port;
            }
        }
        return -1;
    }

    private boolean isPortAvailable(int port) {
        try (ServerSocket socket = new ServerSocket(port)) {
            socket.setReuseAddress(true);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private void copyTemplate(String templateDir, String newServiceDir) throws IOException {
        File template = new File(templateDir);
        File newService = new File(newServiceDir);

        if (!newService.exists()) {
            newService.mkdirs();
        }

        String[] files = template.list();
        if (files != null && files.length > 0) {
            for (String file : files) {
                File srcFile = new File(template, file);
                File destFile = new File(newService, file);
                if (srcFile.isDirectory()) {
                    copyTemplate(srcFile.getPath(), destFile.getPath());
                } else {
                    Files.copy(srcFile.toPath(), destFile.toPath());
                }
            }
        } else {
            throw new IOException("Template directory is empty or cannot be read.");
        }
    }

    private void enableServiceCommunication(String serviceDir) throws IOException {
        // Haberleşme için gerekli bağımlılıkları ekleme
        Path pomPath = Paths.get(serviceDir, "pom.xml");
        String pomContent = Files.readString(pomPath);
        String dependency = """
                <dependency>
                    <groupId>org.springframework.cloud</groupId>
                    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
                </dependency>
                """;
        pomContent = pomContent.replace("</dependencies>", dependency + "</dependencies>");
        Files.writeString(pomPath, pomContent);

        // application.yml dosyasını güncelleme
        Path applicationYamlPath = Paths.get(serviceDir, "src/main/resources/application.yml");
        String yamlContent = Files.readString(applicationYamlPath);
        String communicationConfig = """
                eureka:
                  client:
                    service-url:
                      defaultZone: http://localhost:8761/eureka/
                """;
        yamlContent += communicationConfig;
        Files.writeString(applicationYamlPath, yamlContent);
    }

    public String listMicroservices() {
        if (microservices.isEmpty()) {
            return "No microservices available.";
        }
        StringBuilder sb = new StringBuilder();
        microservices.forEach((name, port) -> sb.append(name).append(" running on port ").append(port).append("\n"));
        return sb.toString();
    }

    public String deleteMicroservice(String name) {
        if (!microservices.containsKey(name)) {
            return "Microservice with name " + name + " does not exist.";
        }
        microservices.remove(name);

        // Mikroservis dizinini silme
        String serviceDir = "microservices/" + name;
        try {
            deleteDirectory(Paths.get(serviceDir));
        } catch (IOException e) {
            return "Error deleting microservice: " + e.getMessage();
        }

        return "Microservice " + name + " deleted.";
    }

    private void deleteDirectory(Path path) throws IOException {
        if (Files.isDirectory(path)) {
            try (var entries = Files.newDirectoryStream(path)) {
                for (var entry : entries) {
                    deleteDirectory(entry);
                }
            }
        }
        Files.delete(path);
    }
}
