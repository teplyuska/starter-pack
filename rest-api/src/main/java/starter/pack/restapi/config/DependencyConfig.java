package starter.pack.restapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import starter.pack.core.config.CoreConfig;
import starter.pack.shared.config.SharedConfig;

@Configuration
@Import({CoreConfig.class, SharedConfig.class})
public class DependencyConfig {
}
