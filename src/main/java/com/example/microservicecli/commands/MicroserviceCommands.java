package com.example.microservicecli.commands;

import com.example.microservicecli.service.MicroserviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class MicroserviceCommands {

    private final MicroserviceService microserviceService;

    @Autowired
    public MicroserviceCommands(MicroserviceService microserviceService) {
        this.microserviceService = microserviceService;
    }

    @ShellMethod("Create a new microservice")
    public String createMicroservice(
            @ShellOption String name,
            @ShellOption(defaultValue = "false") boolean enableCommunication) {
        return microserviceService.createMicroservice(name, enableCommunication);
    }

    @ShellMethod("List all microservices")
    public String listMicroservices() {
        return microserviceService.listMicroservices();
    }

    @ShellMethod("Delete a microservice")
    public String deleteMicroservice(@ShellOption String name) {
        return microserviceService.deleteMicroservice(name);
    }
}
