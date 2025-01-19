package ir.mohaymen.tsm;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.Duration;

@Configuration
@EnableJpaRepositories(basePackages = {"ir.mohaymen.tsm.core.domain_services.account.repositories",
        "ir.mohaymen.tsm.core.domain_services.audit_log.repositories",
        "ir.mohaymen.tsm.core.domain_services.transaction.repositories"})
@EnableJpaAuditing
@EnableAspectJAutoProxy
@EnableCaching
public class AppConfiguration {

}
