package dev.natig.durin;

import dev.natig.durin.mcp.DateTimeToolService;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DurinApplication {

    public static void main(String[] args) {
        SpringApplication.run(DurinApplication.class, args);
    }

    @Bean
    public ToolCallbackProvider weatherTools(DateTimeToolService dateTimeToolService) {
        return MethodToolCallbackProvider.builder().toolObjects(dateTimeToolService).build();
    }
}
