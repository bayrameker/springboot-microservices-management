Certainly! Here's the corrected and formatted README.md:

```markdown
# My Microservice CLI

My Microservice CLI is a command-line tool designed to simplify the creation and management of microservices using Spring Boot. This tool allows users to quickly generate new microservices with predefined templates, assign them unique ports, and optionally enable inter-service communication via Eureka.

## Features

- **Create Microservices**: Easily generate new microservices with a single command.
- **Assign Unique Ports**: Automatically assigns an available port to each new microservice.
- **Enable Communication**: Optionally enable communication between services using Eureka.
- **List Microservices**: View all created microservices and their assigned ports.
- **Delete Microservices**: Remove unwanted microservices.

## Project Structure

```plaintext
my-microservice-cli/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── microservicecli/
│   │   │               ├── commands/
│   │   │               │   └── MicroserviceCommands.java
│   │   │               ├── service/
│   │   │               │   └── MicroserviceService.java
│   │   │               ├── util/
│   │   │               │   └── FileUtil.java
│   │   │               └── MyMicroserviceCliApplication.java
│   │   ├── resources/
│   │   │   └── application.yml
│   ├── test/
│       └── java/
│           └── com/
│               └── example/
│                   └── microservicecli/
│                       └── MicroserviceServiceTests.java
├── microservice-template/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/
│   │   │   │       └── example/
│   │   │   │           └── microservice/
│   │   │   │               └── MyMicroserviceApplication.java
│   │   ├── resources/
│   │       └── application.yml
│   └── pom.xml
└── pom.xml
```

## Getting Started

### Prerequisites

- Java 17
- Maven
- Spring Boot

### Installation

1. **Clone the repository**:

    ```bash
    git clone https://github.com/bayrameker/springboot-microservices-management.git
    cd my-microservice-cli
    ```

2. **Build the project**:

    ```bash
    mvn clean install
    ```

3. **Run the application**:

    ```bash
    mvn spring-boot:run
    ```

### Usage

Once the application is running, you can use the following commands:

#### Create a New Microservice

```bash
create-microservice --name my-service --enableCommunication true
```

This command will create a new microservice named `my-service` and enable communication with Eureka.

#### List All Microservices

```bash
list-microservices
```

This command will list all created microservices along with their assigned ports.

#### Delete a Microservice

```bash
delete-microservice --name my-service
```

This command will delete the specified microservice.

## Contributing

Contributions are welcome! Please open an issue or submit a pull request for any improvements or bug fixes.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Acknowledgments

- Spring Boot
- Spring Shell
- Eureka
```

Feel free to update the repository URL and other details according to your needs. This README provides a clear overview of the project, its structure, usage instructions, and contribution guidelines.