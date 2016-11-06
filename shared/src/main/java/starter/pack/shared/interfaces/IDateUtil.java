package starter.pack.shared.interfaces;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.TimeZone;

public interface IDateUtil {
    TimeZone getTimezoneUtc();

    long toLocalTime(long time, TimeZone to);

    long toUTC(long time, TimeZone from);

    long convertTime(long time, TimeZone from, TimeZone to);

    long getTimeZoneOffset(long time, TimeZone from, TimeZone to);

    LocalDateTime getNow();
}
