package ir.mohaymen.tsm.framework.services;

import java.time.LocalDate;

public interface DateConverter {
    LocalDate jalaliToGregorian(LocalDate jalali);
    LocalDate GregorianToJalali(LocalDate gregorian);
}
