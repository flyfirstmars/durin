package dev.natig.durin.mcp;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Service
public class DateTimeToolService {

    /**
     * Returns the current local date and time in ISO-8601 format
     */
    @Tool(name = "get_current_datetime", description = "Get the current local date and time in ISO-8601 format")
    public String getCurrentDateTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    /**
     * Returns the current local date and time in a custom format
     */
    @Tool(name = "get_formatted_datetime", description = "Get the current local date and time in a custom format. Example patterns: 'yyyy-MM-dd HH:mm:ss', 'dd/MM/yyyy', 'HH:mm:ss'")
    public String getFormattedDateTime(String pattern) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            return LocalDateTime.now().format(formatter);
        } catch (DateTimeParseException | IllegalArgumentException e) {
            return "Invalid pattern: " + pattern + ". Please use valid DateTimeFormatter pattern.";
        }
    }

    /**
     * Returns current date and time broken down into components
     */
    @Tool(name = "get_time_components", description = "Get the current date and time broken down into separate components (year, month, day, hour, minute, second)")
    public TimeComponents getCurrentTimeComponents() {
        LocalDateTime now = LocalDateTime.now();
        return new TimeComponents(
                now.getYear(),
                now.getMonthValue(),
                now.getDayOfMonth(),
                now.getHour(),
                now.getMinute(),
                now.getSecond()
        );
    }

    /**
     * Record to hold time components for structured output
     */
    public record TimeComponents(
            int year,
            int month,
            int day,
            int hour,
            int minute,
            int second
    ) {}
} 