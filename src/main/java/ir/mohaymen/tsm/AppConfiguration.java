package ir.mohaymen.tsm;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Configuration
@EnableJpaRepositories(basePackages = {"ir.mohaymen.tsm.core.domain_services.account.repositories",
        "ir.mohaymen.tsm.core.domain_services.audit_log.repositories",
        "ir.mohaymen.tsm.core.domain_services.transaction.repositories"})
@EnableJpaAuditing
@EnableAspectJAutoProxy
public class AppConfiguration {

}
