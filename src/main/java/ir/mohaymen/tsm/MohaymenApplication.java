package ir.mohaymen.tsm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"ir.mohaymen.tsm"})
public class MohaymenApplication {

    public static void main(String[] args) {
        SpringApplication.run(MohaymenApplication.class, args);
    }

}
