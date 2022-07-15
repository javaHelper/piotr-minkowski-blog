package com.example.demo.config;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import org.springframework.context.annotation.Bean;

import static io.r2dbc.pool.PoolingConnectionFactoryProvider.MAX_SIZE;
import static io.r2dbc.spi.ConnectionFactoryOptions.*;

// just demo
// we can use either application.properties or this way by creating ConnectionFactory

//@Configuration
public class R2DBCConfig {

    @Bean
    public ConnectionFactory connectionFactory() {
        return ConnectionFactories.get(
                ConnectionFactoryOptions.builder()
                        .option(DRIVER, "postgresql")
                        .option(HOST, "localhost")
                        .option(PORT, 5432)
                        .option(USER, "postgres")
                        .option(PASSWORD, "postgres")
                        .option(DATABASE, "productdb")
                        .option(MAX_SIZE, 40)
                        .build());
    }
}