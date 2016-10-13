package fr.norsys.config;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    private static final String JNDI_NAME = "java:comp/env/jdbc/bonecp";

    @Bean
    public DataSource dataSource() throws NamingException {
        final Context ctx = new InitialContext();
        // final JndiDataSourceLookup lookup = new JndiDataSourceLookup();
        return (DataSource) ctx.lookup( JNDI_NAME );
    }
}
