# Durin MCP Server

A Model Context Protocol (MCP) Server implementation using **Spring AI 1.0 GA** and **Spring Boot 3.5.3** with **STDIO transport**.

This project demonstrates how to create an MCP Server that exposes datetime tools to AI assistants through the standardized Model Context Protocol using STDIO transport for direct communication.

## 🚀 Features

- **Spring AI 1.0 GA**: Latest stable version of Spring AI
- **Spring Boot 3.5.3**: Latest version of Spring Boot  
- **STDIO Transport**: Direct communication without HTTP overhead
- **DateTime Tools**: Multiple datetime-related tools for AI assistants
- **Function-based Tool Registration**: Uses Spring beans as functions for tool registration
- **Production Ready**: Following Spring Boot best practices

## 📋 Prerequisites

- **Java 21+**: Required for Spring Boot 3.5.3
- **Maven 3.6+**: For dependency management
- **SDKMAN**: Recommended for Java version management

## 🛠️ Available Tools

The MCP server exposes the following datetime tools:

1. **`getCurrentDateTime`**: Returns current local date and time in ISO-8601 format
2. **`getFormattedDateTime`**: Returns current date and time in a custom format (takes pattern parameter)
3. **`getTimeComponents`**: Returns current date and time broken down into components

## 🏗️ Architecture

This implementation uses:
- **STDIO Transport**: Direct process communication
- **Function-based Tool Registration**: Spring Bean functions automatically discovered as MCP tools
- **Service Layer**: `DateTimeToolService` provides the core datetime functionality
- **Configuration-driven**: Uses `application.properties` for MCP server settings

## 🚀 Quick Start

### 1. Build the Application
```bash
mvn clean package
```

### 2. MCP Client Configuration

Add this to your MCP client configuration (e.g., Claude Desktop):

```json
{
  "mcpServers": {
    "DurinServer": {
         "type": "stdio",
         "command": "java",
         "args": [
            "-jar",
            "{absolute_path}/durin/target/durin-0.0.1-SNAPSHOT.jar"
         ]
      }
   }
}
```

**Note**: Update the Java path and JAR path to match your system:
- Java path: Use `which java` or `echo $JAVA_HOME/bin/java`
- JAR path: Use the absolute path to your built JAR file

### 3. Test the MCP Server

Once configured, your MCP client (like Claude) can use these tools:

- *"What's the current time?"* → Uses `getCurrentDateTime`
- *"Give me the time in format dd/MM/yyyy"* → Uses `getFormattedDateTime`  
- *"Break down the current time into components"* → Uses `getTimeComponents`

## 📁 Project Structure

```
src/main/java/dev/natig/durin/
├── DurinApplication.java              # Main application with tool registration
└── mcp/
    └── DateTimeToolService.java       # DateTime service implementation

src/main/resources/
└── application.properties             # MCP server configuration

target/
└── durin-0.0.1-SNAPSHOT.jar          # Executable JAR
```

## 🔧 Troubleshooting

### Common Issues

1. **"Unable to locate a Java Runtime"**
   - Solution: Use the full path to Java executable in MCP configuration
   - Find path: `which java` or `echo $JAVA_HOME/bin/java`

2. **"Server disconnected" / "No tools available"**
   - Ensure JAR path is correct and absolute
   - Check that the server builds without errors: `mvn clean package`
   - Verify STDIO configuration is correct in `application.properties`

3. **MCP Client can't find server**
   - Verify the command and args in MCP configuration are correct
   - Test server starts manually: `java -jar target/durin-0.0.1-SNAPSHOT.jar`

## 📚 Technical Details

### Tool Registration Pattern

This implementation uses Spring's function beans for tool registration:

```java
@Bean("getCurrentDateTime")
public Function<Void, String> getCurrentDateTime(DateTimeToolService dateTimeService) {
    return (input) -> dateTimeService.getCurrentDateTime();
}
```

The Spring AI MCP starter automatically discovers these function beans and registers them as MCP tools.

### Dependencies

Key dependencies used:
- `spring-ai-starter-mcp-server`: Spring AI MCP Server STDIO support
- `spring-boot-starter-test`: Testing framework

## 📄 License

This project is open source and available under the MIT License.

