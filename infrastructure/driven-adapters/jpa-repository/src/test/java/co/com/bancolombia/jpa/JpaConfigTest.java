package co.com.bancolombia.jpa;

import co.com.bancolombia.jpa.config.DBSecret;
import co.com.bancolombia.jpa.config.JpaConfig;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.assertj.core.groups.Properties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class JpaConfigTest {

    @Mock
    private Environment mockEnv;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDBSecretBuilder() {
        //GIVEN
        DBSecret dbSecret = DBSecret.builder()
                .url("jdbc:mysql://localhost:3306/db_test")
                .username("testuser")
                .password("testpassword")
                .build();

        //THEN - WHEN
        Assertions.assertEquals("jdbc:mysql://localhost:3306/db_test", dbSecret.getUrl());
        Assertions.assertEquals("testuser", dbSecret.getUsername());
        Assertions.assertEquals("testpassword", dbSecret.getPassword());
    }

    @Test
    public void testDBSecretBean() {
        // GIVEN
        when(mockEnv.getProperty("spring.datasource.url")).thenReturn("jdbc:mysql://localhost:3306/db_test");
        when(mockEnv.getProperty("spring.datasource.username")).thenReturn("testuser");
        when(mockEnv.getProperty("spring.datasource.password")).thenReturn("testpassword");


        JpaConfig jpaConfig = new JpaConfig();

        // THEN
        DBSecret dbSecret = jpaConfig.dbSecret(mockEnv);

        // WHEN
        Assertions.assertEquals("jdbc:mysql://localhost:3306/db_test", dbSecret.getUrl());
        Assertions.assertEquals("testuser", dbSecret.getUsername());
        Assertions.assertEquals("testpassword", dbSecret.getPassword());
    }
    /*
    @Test
    public void testDataSourceBean() {
        // Create a mock DBSecret instance
        DBSecret mockSecret = DBSecret.builder()
                .url("jdbc:mysql://localhost:3306/db_test")
                .username("testuser")
                .password("testpassword")
                .build();

        // Create an instance of JpaConfig to test the dataSource bean creation
        JpaConfig jpaConfig = new JpaConfig();

        // Invoke the dataSource bean method
        DataSource dataSource = jpaConfig.datasource(mockSecret, "com.mysql.jdbc.Driver");

        // Assert the DataSource configuration
        assertTrue(dataSource instanceof HikariDataSource);
        HikariDataSource hikariDataSource = (HikariDataSource) dataSource;
        HikariConfig hikariConfig = hikariDataSource.getHikariConfig();
        assertEquals("jdbc:mysql://localhost:3306/db_test", hikariConfig.getJdbcUrl());
        assertEquals("testuser", hikariConfig.getUsername());
        assertEquals("testpassword", hikariConfig.getPassword());
        assertEquals("com.mysql.jdbc.Driver", hikariConfig.getDriverClassName());
    }
}

     */
/*
    @Test
    public void testEntityManagerFactoryBean() {
        // Create a mock DataSource
        DataSource mockDataSource = mock(DataSource.class);

        // Create an instance of JpaConfig to test the entityManagerFactory bean creation
        JpaConfig jpaConfig = new JpaConfig();

        // Invoke the entityManagerFactory bean method
        LocalContainerEntityManagerFactoryBean entityManagerFactory =
                jpaConfig.entityManagerFactory(mockDataSource, "org.hibernate.dialect.MySQL5Dialect");

        // Assert the EntityManagerFactory configuration
        assertNotNull(entityManagerFactory);
        assertEquals(mockDataSource, entityManagerFactory.getDataSource());
        assertEquals("co.com.bancolombia.jpa", entityManagerFactory.getPackagesToScan());
        assertTrue(entityManagerFactory.getJpaVendorAdapter() instanceof HibernateJpaVendorAdapter);

        Properties jpaProperties = entityManagerFactory.getJpaProperties();
        assertEquals("org.hibernate.dialect.MySQL5Dialect", jpaProperties.getProperty("hibernate.dialect"));
    }

 */

}
