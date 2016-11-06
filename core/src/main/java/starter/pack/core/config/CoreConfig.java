package starter.pack.core.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan({"starter.pack.core.service", "starter.pack.core.repository", "starter.pack.core.mapper"})
@Import(DataConnectionConfig.class)
public class CoreConfig {
}
