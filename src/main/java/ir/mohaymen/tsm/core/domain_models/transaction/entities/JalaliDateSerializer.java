package ir.mohaymen.tsm.core.domain_models.transaction.entities;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.github.eloyzone.jalalicalendar.DateConverter;
import com.github.eloyzone.jalalicalendar.JalaliDate;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

public class JalaliDateSerializer extends JsonSerializer<Date> {

    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        DateConverter dateConverter = new DateConverter();
        LocalDate dt = date.toLocalDate();
        JalaliDate jalaliDate = dateConverter.gregorianToJalali(dt.getYear(), dt.getMonthValue(), dt.getDayOfMonth());
        String gregorianDate = String.format("%04d-%02d-%02d", jalaliDate.getYear(), jalaliDate.getMonthPersian().getValue(),jalaliDate.getDay());
        jsonGenerator.writeString(gregorianDate);
    }
}
