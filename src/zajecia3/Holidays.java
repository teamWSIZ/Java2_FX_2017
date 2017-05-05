package zajecia3;

import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * Próby wyliczenia polskich świąt państwowych...
 */
public class Holidays {
    public static boolean isHoliday(LocalDate day)
    {
        if (day.getDayOfWeek().equals(DayOfWeek.SUNDAY)) return true;
        int month = day.getMonthValue();
        int dday = day.getDayOfMonth();
        int year = day.getYear();
        
        
        if (month == 1 && dday == 1) return true; // Nowy Rok
        if (month == 5 && dday == 1) return true; // 1 maja
        if (month == 5 && dday == 3 && (year >= 1918 && year <= 1950 || year >= 1990)) return true; // 3 maja
        if (month == 7 && dday == 22 && (year >= 1945 && year <= 1989)) return true; // Narodowe Święto Odrodzenia Polski
        if (month == 8 && dday == 15 && (year <= 1960 || year >= 1989)) return true; // Wniebowzięcie Najświętszej Marii Panny, Święto Wojska Polskiego (rocznica “cudu nad Wisłą”)
        if (month == 11 && dday == 1) return true; // Dzień Wszystkich Świętych
        if (month == 11 && dday == 11 && (year == 1937 || year == 1938 || year >= 1990)) return true; // Dzień Niepodległości
        if (month == 12 && dday == 25) return true; // Boże Narodzenie
        if (month == 12 && dday == 26) return true; // Boże Narodzenie
        int a = year % 19;
        int b = year % 4;
        int c = year % 7;
        int d = (a * 19 + 24) % 30;
        int e = (2 * b + 4 * c + 6 * d + 5) % 7;
        if (d == 29 && e == 6) d -= 7;
        if (d == 28 && e == 6 && a > 10) d -= 7;
        LocalDate easterSunday = LocalDate.of(year, 3, 22);
        LocalDate easterMonday = easterSunday.plusDays(1);
        LocalDate bozeCialo = easterSunday.plusDays(60);
        if (day.equals(easterMonday) || day.equals(bozeCialo)) return true;

//        DateTime easter = new DateTime (year, 3, 22).AddDays (d + e);
//        if (day.AddDays (-1) == easter)
//            return true; // Wielkanoc (poniedziałek)
//        if (day.AddDays (-60) == easter)
//            return true; // Boże Ciało
//        return false;
        return false;
    }
}
