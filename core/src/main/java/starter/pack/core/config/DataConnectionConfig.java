package starter.pack.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import starter.pack.shared.model.EnvironmentConfigurations;
import starter.pack.shared.util.PropertyUtil;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {"starter.pack.core.repositories", "starter.pack.core.model.data"})
public class DataConnectionConfig {
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws Exception {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(c3p0DataSource());
        em.setPackagesToScan(new String[]{"starter.pack.core.model.data"});
        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(false);
        em.setJpaVendorAdapter(vendorAdapter);

        Properties hibernateProperties = new Properties();
        // hibernateProperties.setProperty("hibernate.show_sql", "true");
        // hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
        em.setJpaProperties(hibernateProperties);

        return em;
    }

    @Bean
    public DataSource c3p0DataSource() throws Exception {
        final com.mchange.v2.c3p0.ComboPooledDataSource dataSource = new com.mchange.v2.c3p0.ComboPooledDataSource();

        EnvironmentConfigurations environmentConfigurations = PropertyUtil.getEnvironmentConfigurationsByOS();

        dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
        dataSource.setJdbcUrl(environmentConfigurations.getJdbcUrl());
        dataSource.setUser(environmentConfigurations.getDbUser());
        dataSource.setPassword(environmentConfigurations.getDbPassword());
        dataSource.setMaxPoolSize(10);
        dataSource.setMinPoolSize(1);
        dataSource.setAcquireIncrement(1);
        dataSource.setIdleConnectionTestPeriod(200);
        dataSource.setMaxStatements(0);
        dataSource.setMaxIdleTime(300);

        return dataSource;
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
