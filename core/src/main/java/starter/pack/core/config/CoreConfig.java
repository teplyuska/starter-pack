package starter.pack.core.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan({"starter.pack.core.service", "starter.pack.core.mapper"})
@Import(DataConnectionConfig.class)
public class CoreConfig {
}
