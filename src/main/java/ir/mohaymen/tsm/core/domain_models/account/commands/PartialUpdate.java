package ir.mohaymen.tsm.core.domain_models.account.commands;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import ir.mohaymen.tsm.core.domain_models.account.commands.deserializer.JalaliDateDeserializer;
import ir.mohaymen.tsm.framework.commands.Command;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDate;

@Data
public class PartialUpdate implements Command {
    private Long accountNumber;
    private String customerName;
    private String identificationCode;
    @JsonDeserialize(using = JalaliDateDeserializer.class)
    private LocalDate date;
    private String phoneNumber;
    private String address;
    private String postalCode;
    private String customerType;
}
