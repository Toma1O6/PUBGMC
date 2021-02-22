package dev.toma.pubgmc.client.content;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class DateComparationContext {

    private final long years, months, days, hours, minutes;

    private DateComparationContext(LocalDateTime ldt1, LocalDateTime ldt2) {
        LocalDateTime tempDateTime = LocalDateTime.from(ldt1);
        years = tempDateTime.until(ldt2, ChronoUnit.YEARS);
        tempDateTime = tempDateTime.plusYears(years);
        months = tempDateTime.until(ldt2, ChronoUnit.MONTHS);
        tempDateTime = tempDateTime.plusMonths(months);
        days = tempDateTime.until(ldt2, ChronoUnit.DAYS);
        tempDateTime = tempDateTime.plusDays(days);
        hours = tempDateTime.until(ldt2, ChronoUnit.HOURS);
        tempDateTime = tempDateTime.plusHours(hours);
        minutes = tempDateTime.until(ldt2, ChronoUnit.MINUTES);
    }

    public static DateComparationContext from(LocalDateTime ldt1, LocalDateTime ldt2) {
        return new DateComparationContext(ldt1, ldt2);
    }

    private static String format(String unitName, long value) {
        return String.format("In %d %s%s", value, unitName, value > 1 ? "s" : "");
    }

    public long getYears() {
        return years;
    }

    public long getMonths() {
        return months;
    }

    public long getDays() {
        return days;
    }

    public long getHours() {
        return hours;
    }

    public long getMinutes() {
        return minutes;
    }

    @Override
    public String toString() {
        return "DateComparationContext{" + "years=" + years + ", months=" + months + ", days=" + days + ", hours=" + hours + ", minutes=" + minutes + '}';
    }

    public String trimmedString() {
        if (years < 0 || months < 0 || days < 0 || hours < 0 || minutes < 0) {
            return null;
        } else if (years > 0) {
            return format("year", years);
        } else if (months > 0) {
            return format("month", months);
        } else if (days > 0) {
            return format("day", days);
        } else if (hours > 0) {
            return format("hour", hours);
        } else if (minutes > 0) {
            return format("minute", minutes);
        }
        return null;
    }
}
