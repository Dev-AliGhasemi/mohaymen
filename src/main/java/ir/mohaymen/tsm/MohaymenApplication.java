package ir.mohaymen.tsm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication(scanBasePackages = {"ir.mohaymen.tsm"})
@EnableCaching
public class MohaymenApplication {
    public static void main(String[] args) {
        SpringApplication.run(MohaymenApplication.class, args);
    }
}
