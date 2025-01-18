package ir.mohaymen.tsm.core.domain_models.account.commands.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.ibm.icu.util.Calendar;
import com.ibm.icu.util.ULocale;
import ir.mohaymen.tsm.framework.services.DateConverter;

import java.io.IOException;
import java.time.LocalDate;

public class JalaliDateDeserializer extends JsonDeserializer<LocalDate> {

    private final DateConverter dateConverter;
    public JalaliDateDeserializer(DateConverter dateConverter) {
        this.dateConverter = dateConverter;
    }

    @Override
    public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        String date = jsonParser.readValueAs(String.class);
        Calendar solarCalendar = Calendar.getInstance(new ULocale("fa_IR@calendar=persian"));
        solarCalendar.set(Calendar.YEAR, Integer.parseInt(date.split("-")[0]));
        solarCalendar.set(Calendar.MONTH, Integer.parseInt(date.split("-")[1]));
        solarCalendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date.split("-")[2]));

        String[] dt = date.split("-");

        long timeInMillis = solarCalendar.getTimeInMillis();

        return dateConverter.jalaliToGregorian(LocalDate.of(Integer.parseInt(dt[0]),Integer.parseInt(dt[1]),Integer.parseInt(dt[2])));

//        return LocalDate.ofEpochDay(timeInMillis);
    }
}
