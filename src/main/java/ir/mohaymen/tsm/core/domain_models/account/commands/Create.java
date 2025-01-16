package ir.mohaymen.tsm.core.domain_models.account.commands;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import ir.mohaymen.tsm.core.domain_models.account.commands.deserializer.JalaliDateDeserializer;
import ir.mohaymen.tsm.framework.domain_models.commands.Command;
import lombok.Data;

import java.sql.Date;

@Data
public class Create implements Command {
    private String customerName;
    private String identificationCode;
    @JsonDeserialize(using = JalaliDateDeserializer.class)
    private Date date;
    private String phoneNumber;
    private String address;
    private String postalCode;
    private String customerType;
}
