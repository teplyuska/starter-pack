package starter.pack.shared.util;

import org.springframework.stereotype.Component;
import starter.pack.shared.interfaces.IDateUtil;

import java.time.LocalDateTime;
import java.util.TimeZone;

@Component
public class DateUtil implements IDateUtil {
    private static final String UTC_ID = "UTC";
    private static final TimeZone TIMEZONE_UTC = TimeZone.getTimeZone(UTC_ID);

    @Override
    public TimeZone getTimezoneUtc() {
        return TIMEZONE_UTC;
    }

    @Override
    public long toLocalTime(long time, TimeZone to) {
        return convertTime(time, TIMEZONE_UTC, to);
    }

    @Override
    public long toUTC(long time, TimeZone from) {
        return convertTime(time, from, TIMEZONE_UTC);
    }

    @Override
    public long convertTime(long time, TimeZone from, TimeZone to) {
        return time + getTimeZoneOffset(time, from, to);
    }

    @Override
    public long getTimeZoneOffset(long time, TimeZone from, TimeZone to) {
        int fromOffset = from.getOffset(time);
        int toOffset = to.getOffset(time);
        int diff;

        if (fromOffset >= 0) {
            if (toOffset > 0) {
                toOffset = -1 * toOffset;
            } else {
                toOffset = Math.abs(toOffset);
            }

            diff = (fromOffset + toOffset) * -1;
        } else {
            if (toOffset <= 0) {
                toOffset = -1 * Math.abs(toOffset);
            }

            diff = (Math.abs(fromOffset) + toOffset);
        }

        return diff;
    }

    @Override
    public LocalDateTime getNow() {
        return LocalDateTime.now(TIMEZONE_UTC.toZoneId());
    }
}
