package ir.mohaymen.tsm.infrastructure.services;

import com.github.eloyzone.jalalicalendar.DateConverter;
import com.github.eloyzone.jalalicalendar.JalaliDate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DateConversion implements ir.mohaymen.tsm.framework.services.DateConverter {

    @Override
    public LocalDate jalaliToGregorian(LocalDate jalali){
        DateConverter dateConverter = new DateConverter();
        return dateConverter.jalaliToGregorian(jalali.getYear(),jalali.getMonthValue(),jalali.getDayOfMonth());
    }

    @Override
    public LocalDate GregorianToJalali(LocalDate gregorian) {
        DateConverter dateConverter = new DateConverter();
        JalaliDate jalaliDate = dateConverter.gregorianToJalali(gregorian.getYear(), gregorian.getMonthValue(), gregorian.getDayOfMonth());
        return LocalDate.of(jalaliDate.getYear(),jalaliDate.getMonthPersian().getValue(),jalaliDate.getDay());
    }
}
