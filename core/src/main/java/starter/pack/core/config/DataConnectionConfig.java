package starter.pack.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import starter.pack.core.repository.util.DataConnectionUtil;
import starter.pack.shared.model.EnvironmentConfigurations;
import starter.pack.shared.util.PropertyUtil;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {"starter.pack.core.model.data"})
@EnableJpaRepositories(basePackages = {"starter.pack.core.repository"})
public class DataConnectionConfig {
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws Exception {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(c3p0DataSource());
        em.setPackagesToScan(new String[]{"starter.pack.core.model.data"});

        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(false);
        em.setJpaVendorAdapter(vendorAdapter);

        Properties hibernateProperties = DataConnectionUtil.getHibernateProperties();
        em.setJpaProperties(hibernateProperties);

        return em;
    }

    @Bean
    public DataSource c3p0DataSource() throws Exception {
        EnvironmentConfigurations environmentConfigurations = PropertyUtil.getEnvironmentConfigurationsByOS();

        return DataConnectionUtil.getC3p0dataSource(
                environmentConfigurations.getDatabaseIp(),
                environmentConfigurations.getDatabaseName(),
                environmentConfigurations.getDatabaseUser(),
                environmentConfigurations.getDatabasePassword());
    }

    @Bean
    public PlatformTransactionManager transactionManager(final EntityManagerFactory emf) {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
