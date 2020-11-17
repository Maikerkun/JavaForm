package com.ankieta.memeform.controllers;

import com.ankieta.memeform.domain.Answers;
import com.ankieta.memeform.repositories.AnswerRepository;
import org.hibernate.hql.internal.ast.tree.InsertStatement;

import java.sql.*;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Logger;

public class SQLController {

    private static Logger log;

    static {
        System.setProperty("java.util.logging.SimpleFormatter.format", "[%4$-7s] %5$s %n");
        log =Logger.getLogger(SQLController.class.getName());
    }

   /* public static void main(String[] args) throws Exception{
        log.info("Loading application properties");
        Properties properties = new Properties();
        properties.load(SQLController.class.getClassLoader().getResourceAsStream("application.properties"));

        log.info("Connectin to the database");
        Connection connection = DriverManager.getConnection(properties.getProperty("url"), properties);
        log.info("Database connection test: " + connection.getCatalog());

        log.info("Create database schema");
        Scanner scanner = new Scanner(SQLController.class.getClassLoader().getResourceAsStream("schema.sql"));
        Statement statement = connection.createStatement();
        while (scanner.hasNextLine()){
            statement.execute(scanner.nextLine());
        }

        Answers answers = new Answers("mniej niż 15 lat", "mężczyzna");
        insertData(answers, connection);

        readData(connection);

        log.info("Closing database connection");
        connection.close();

    }*/

    private static void insertData(Answers answers, Connection connection) throws SQLException {
        log.info("Insert data");
        PreparedStatement insertStatement = connection
                .prepareStatement("INSERT INTO answers (age, gender) VALUES (?, ?)");
        insertStatement.setString(1, answers.getAge());
        insertStatement.setString(2, answers.getGender());
        insertStatement.executeUpdate();
    }

    private static Answers readData(Connection connection) throws SQLException {
        log.info("Read data");
        PreparedStatement readStatement = connection.prepareStatement("SELECT * FROM answers");
        ResultSet resultSet = readStatement.executeQuery();
        if(!resultSet.next()){
            log.info("There is no data in the database");
            return null;
        }
        Answers answers = new Answers();
        answers.setId(resultSet.getLong("id"));
        answers.setAge((resultSet.getString("age")));
        answers.setGender((resultSet.getString("gender")));
        log.info("Data read from the database: " + answers.toString());
        return answers;
    }
}
