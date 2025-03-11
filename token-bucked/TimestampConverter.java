import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TimestampConverter {
    public static String convertTimestamp(long timestampMs) {
        // Milisaniyeyi Instant objesine çevir
        Instant instant = Instant.ofEpochMilli(timestampMs);

        // +3 saat ekleyerek Türkiye saatine çevir
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.of("UTC")).plusHours(3);

        // İstenen formatta tarih & saat string olarak dönüştür
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        return zonedDateTime.format(formatter);
    }
    
    public static double convertMillisToSeconds(long millis) {
        return millis / 1000.0; // Milisaniyeyi saniyeye çevir
    }

}

