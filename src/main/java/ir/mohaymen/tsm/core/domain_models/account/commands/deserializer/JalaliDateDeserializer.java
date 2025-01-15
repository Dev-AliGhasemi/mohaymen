package ir.mohaymen.tsm.core.domain_models.account.commands.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.ibm.icu.util.Calendar;
import com.ibm.icu.util.ULocale;

import java.io.IOException;
import java.sql.Date;

public class JalaliDateDeserializer extends JsonDeserializer<Date> {
    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        String date = jsonParser.readValueAs(String.class);
        Calendar solarCalendar = Calendar.getInstance(new ULocale("fa_IR@calendar=persian"));
        solarCalendar.set(Calendar.YEAR, Integer.parseInt(date.split("-")[0]));
        solarCalendar.set(Calendar.MONTH, Integer.parseInt(date.split("-")[1]));
        solarCalendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date.split("-")[2]));

        long timeInMillis = solarCalendar.getTimeInMillis();

        return new Date(timeInMillis);
    }
}
