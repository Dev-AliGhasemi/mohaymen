package ir.mohaymen.tsm.infrastructure.services;

import com.github.eloyzone.jalalicalendar.DateConverter;
import java.time.LocalDate;

public class DateConversion {
    public static LocalDate jalaliToGregorian(LocalDate jd){
        DateConverter dateConverter = new DateConverter();
        return dateConverter.jalaliToGregorian(jd.getYear(),jd.getMonthValue(),jd.getDayOfMonth());
    }
}
