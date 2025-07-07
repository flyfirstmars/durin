package dev.natig.durin.mcp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for DateTimeToolService functionality.
 * Verifies that all datetime tools work correctly.
 */
@SpringBootTest
@ActiveProfiles("test")
class DateTimeToolServiceTest {

    private final DateTimeToolService dateTimeToolService = new DateTimeToolService();

    @Test
    void testGetCurrentDateTime() {
        // Given
        String result = dateTimeToolService.getCurrentDateTime();
        
        // Then
        assertThat(result).isNotNull();
        assertThat(result).matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d+");
        
        // Verify it can be parsed back to LocalDateTime
        LocalDateTime parsed = LocalDateTime.parse(result, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        assertThat(parsed).isBeforeOrEqualTo(LocalDateTime.now());
    }

    @Test
    void testGetFormattedDateTimeWithValidPattern() {
        // Given
        String pattern = "yyyy-MM-dd HH:mm:ss";
        
        // When
        String result = dateTimeToolService.getFormattedDateTime(pattern);
        
        // Then
        assertThat(result).isNotNull();
        assertThat(result).matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}");
    }

    @Test
    void testGetFormattedDateTimeWithInvalidPattern() {
        // Given
        String invalidPattern = "invalid-pattern";
        
        // When
        String result = dateTimeToolService.getFormattedDateTime(invalidPattern);
        
        // Then
        assertThat(result).contains("Invalid pattern");
        assertThat(result).contains(invalidPattern);
    }

    @Test
    void testGetCurrentTimeComponents() {
        // When
        DateTimeToolService.TimeComponents components = dateTimeToolService.getCurrentTimeComponents();
        
        // Then
        assertThat(components).isNotNull();
        assertThat(components.year()).isGreaterThan(2020);
        assertThat(components.month()).isBetween(1, 12);
        assertThat(components.day()).isBetween(1, 31);
        assertThat(components.hour()).isBetween(0, 23);
        assertThat(components.minute()).isBetween(0, 59);
        assertThat(components.second()).isBetween(0, 59);
    }

    @Test
    void testTimeComponentsRecord() {
        // Given
        DateTimeToolService.TimeComponents components = 
            new DateTimeToolService.TimeComponents(2025, 1, 15, 14, 30, 45);
        
        // Then
        assertThat(components.year()).isEqualTo(2025);
        assertThat(components.month()).isEqualTo(1);
        assertThat(components.day()).isEqualTo(15);
        assertThat(components.hour()).isEqualTo(14);
        assertThat(components.minute()).isEqualTo(30);
        assertThat(components.second()).isEqualTo(45);
    }
} 