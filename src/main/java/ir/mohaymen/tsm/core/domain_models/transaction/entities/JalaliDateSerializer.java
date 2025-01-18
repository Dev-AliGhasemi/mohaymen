package ir.mohaymen.tsm.core.domain_models.transaction.entities;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import ir.mohaymen.tsm.framework.services.DateConverter;

import java.io.IOException;
import java.time.LocalDate;

public class JalaliDateSerializer extends JsonSerializer<LocalDate> {

    private final DateConverter dateConverter;

    public JalaliDateSerializer(DateConverter dateConverter) {
        this.dateConverter = dateConverter;
    }

    @Override
    public void serialize(LocalDate date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        LocalDate jalaliDate = dateConverter.GregorianToJalali(date);
        String gregorianDate = String.format("%04d-%02d-%02d", jalaliDate.getYear(), jalaliDate.getMonthValue(),jalaliDate.getDayOfMonth());
        jsonGenerator.writeString(gregorianDate);
    }
}
