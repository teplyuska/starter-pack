package starter.pack.core.repository.util;

import javax.sql.DataSource;
import java.util.Properties;

public class DataConnectionUtil {
    public static DataSource getC3p0dataSource(String ip, String databaseName, String dbUser, String dbPassword) throws Exception {
        final com.mchange.v2.c3p0.ComboPooledDataSource dataSource = new com.mchange.v2.c3p0.ComboPooledDataSource();

        dataSource.setDriverClass(getMysqlDriverClassName());
        dataSource.setJdbcUrl(createJdbcUrlWithGlobalConfigurations(ip, databaseName));
        dataSource.setUser(dbUser);
        dataSource.setPassword(dbPassword);
        dataSource.setMaxPoolSize(10);
        dataSource.setMinPoolSize(1);
        dataSource.setAcquireIncrement(1);
        dataSource.setTestConnectionOnCheckin(true);
        dataSource.setMaxIdleTimeExcessConnections(140);
        dataSource.setIdleConnectionTestPeriod(200);
        dataSource.setMaxStatements(0);
        dataSource.setMaxIdleTime(300);

        return dataSource;
    }

    public static Properties getHibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
        return hibernateProperties;
    }

    public static String getMysqlDriverClassName() {
        return com.mysql.cj.jdbc.Driver.class.getCanonicalName();
    }

    public static String createJdbcUrlWithGlobalConfigurations(String mysqlIp, String mysqlDatabaseName) {
        String globalConfigurations = "?autoReconnect=true&useSSL=false";
        String jdbcUrlWithDatabase = createJbdcUrlWithDatabase(mysqlIp, mysqlDatabaseName);
        String jdbcUrl = String.format("%1$s%2$s", jdbcUrlWithDatabase, globalConfigurations);
        return jdbcUrl;
    }

    public static String createJbdcUrlWithDatabase(String mysqlIp, String mysqlDatabaseName) {
        String jdbcUrl = createJbdcUrl(mysqlIp);
        String jdbcUrlWithDatabase = String.format("%1$s%2$s", jdbcUrl, mysqlDatabaseName);
        return jdbcUrlWithDatabase;
    }

    public static String createJbdcUrl(String mysqlIp) {
        return String.format("jdbc:mysql://%1$s/", mysqlIp);
    }
}
