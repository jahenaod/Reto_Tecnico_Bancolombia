package co.com.bancolombia.jpa;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JpaConfigTest {
    /*
    @Mock
    private Environment environment;
    private co.com.bancolombia.jpa.config.DBSecret secret;

    @Value("${spring.datasource.driverClassName}")
    private String driverClass;
    @InjectMocks
    private JpaConfig jpaConfig;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        jpaConfig = new JpaConfig();
    }

    @Test
    void testDbSecret() {
        // Set up the mocked environment properties
        when(environment.getProperty("spring.datasource.url")).thenReturn("jdbc:mysql://localhost:3306/mydb");
        when(environment.getProperty("spring.datasource.username")).thenReturn("root");
        when(environment.getProperty("spring.datasource.password")).thenReturn("password");

        // Call the method under test
        DBSecret dbSecret = jpaConfig.dbSecret(environment);

        // Verify that the DBSecret object is created correctly
        assertNotNull(dbSecret);
        assertEquals("jdbc:mysql://localhost:3306/mydb", dbSecret.getUrl());
        assertEquals("root", dbSecret.getUsername());
        assertEquals("password", dbSecret.getPassword());
    }
    @Test
    void testDataSource() {
        // Create a mock DBSecret object
        when(secret.getUrl()).thenReturn("jdbc:mysql://localhost:3306/mydb");
        when(secret.getUsername()).thenReturn("root");
        when(secret.getPassword()).thenReturn("password");

        // Set up the mocked driver class name
        String driverClass = "com.mysql.jdbc.Driver";

        // Call the method under test
        HikariDataSource dataSource = (HikariDataSource) jpaConfig.datasource(secret, driverClass);

        // Verify that the HikariDataSource is created correctly
        assertNotNull(dataSource);
        assertEquals("jdbc:mysql://localhost:3306/mydb", dataSource.getJdbcUrl());
        assertEquals("root", dataSource.getUsername());
        assertEquals("password", dataSource.getPassword());
        assertEquals("com.mysql.jdbc.Driver", dataSource.getDriverClassName());
    }

     */
}
