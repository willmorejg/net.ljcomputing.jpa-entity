/**
           Copyright 2016, James G. Willmore

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

package net.ljcomputing.config;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * Persistence configuration.
 * 
 * @author James G. Willmore
 *
 */
@Configuration
@ComponentScan(basePackages = { "net.ljcomputing" })
@PropertySource("classpath:datasource.properties")
@EnableAutoConfiguration
@EnableAspectJAutoProxy
@EnableJpaRepositories("net.ljcomputing.repository")
@EntityScan({ "net.ljcomputing.entity" })
@EnableTransactionManagement
@SuppressWarnings("PMD.AtLeastOneConstructor")
public class PersistenceConfiguration {

  /** The SLF4J Logger. */
  private static final Logger LOGGER = LoggerFactory.getLogger(PersistenceConfiguration.class);

  /** The url. */
  @Value("${dataSource.url}")
  private transient String url;

  /** The username. */
  @Value("${dataSource.username}")
  private transient String username;

  /** The password. */
  @Value("${dataSource.password}")
  private transient String password;

  /**
   * Data source.
   *
   * @return the data source
   */
  @Bean
  public DataSource dataSource() {
    LOGGER.debug("using the following configuration: [{}] [{}] [{}]", url, username, password);

    try {
      return new HikariDataSource(hikariConfig());
    } catch (SQLException exception) {
      LOGGER.error("Could not create data source:", exception);
    }

    return null;
  }

  /**
   * Gets the driver class name.
   *
   * @return the driver class name
   * @throws SQLException the SQL exception
   */
  private String getDriverClassName() throws SQLException {
    final Driver driver = DriverManager.getDriver(url);
    final Class<?> driverClass = driver.getClass();
    return driverClass.getName();
  }

  /**
   * Hikari config.
   *
   * @return the hikari config
   * @throws SQLException the SQL exception
   */
  @Bean
  public HikariConfig hikariConfig() throws SQLException {
    final HikariConfig hikariConfig = new HikariConfig();

    initConnectionPool(hikariConfig);

    hikariConfig.setMaxLifetime(60000);

    hikariConfig.setMinimumIdle(2);
    hikariConfig.setMaximumPoolSize(10);

    addAdditionalPoolProperties(hikariConfig);

    return hikariConfig;
  }

  /**
   * Inits the connection pool.
   *
   * @param hikariConfig the hikari config
   * @throws SQLException the SQL exception
   */
  private void initConnectionPool(final HikariConfig hikariConfig) throws SQLException {
    hikariConfig.setDriverClassName(getDriverClassName());
    hikariConfig.setJdbcUrl(url);
    hikariConfig.setUsername(username);
    hikariConfig.setPassword(password);
  }

  /**
   * Adds the additional connection pool properties.
   *
   * @param hikariConfig the hikari config
   */
  private void addAdditionalPoolProperties(final HikariConfig hikariConfig) {
    hikariConfig.setPoolName("hikariConnectionPool");

    hikariConfig.addDataSourceProperty("dataSource.cachePrepStmts", "true");
    hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSize", "250");
    hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSqlLimit", "2048");
    hikariConfig.addDataSourceProperty("dataSource.useServerPrepStmts", "true");
  }

  /**
   * Entity manager factory.
   *
   * @return the entity manager factory
   */
  @Bean
  public EntityManagerFactory entityManagerFactory() {
    final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    vendorAdapter.setGenerateDdl(true);

    final LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
    factory.setJpaVendorAdapter(vendorAdapter);
    factory.setPackagesToScan("net.ljcomputing.entity");
    factory.setDataSource(dataSource());
    factory.afterPropertiesSet();

    return factory.getObject();
  }

  /**
   * Transaction manager.
   *
   * @return the data source transaction manager
   */
  @Bean
  public PlatformTransactionManager transactionManager() {
    final JpaTransactionManager txManager = new JpaTransactionManager();
    txManager.setEntityManagerFactory(entityManagerFactory());
    return txManager;
  }
}
