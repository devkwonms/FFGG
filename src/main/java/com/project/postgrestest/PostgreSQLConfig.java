package com.project.postgrestest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

@Component
public class PostgreSQLConfig implements ApplicationRunner {

    @Autowired
    DataSource dataSource;
    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public void run(ApplicationArguments args) throws Exception {

        try (Connection connection = dataSource.getConnection()){
            System.out.println("[kwon] > dataSource Class > " + dataSource.getClass());
            System.out.println("[kwon] > URL > " + connection.getMetaData().getURL());
            System.out.println("[kwon] > userName > " + connection.getMetaData().getUserName());

            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE TBL_TEST(NO INTEGER NOT NULL, TEST_NAME VARCHAR(255), PRIMARY KEY (NO))";
            statement.executeUpdate(sql);
        }

        jdbcTemplate.execute("INSERT INTO TBL_TEST VALUES (1, 'kwon')");
    }

}