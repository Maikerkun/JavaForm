package com.ankieta.memeform.repositories;

import com.ankieta.memeform.domain.Answers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Logger;

@Repository
public class AnswerRepository {

    private static Logger log;

    static{
        System.setProperty("java.util.logging.SimpleFormatter.format", "[%4$-7s] %5$s %n");
        log =Logger.getLogger(AnswerRepository.class.getName());
    }

    @Autowired
    private DataSource dataSource;

    public List<Answers> getAllAnswers() throws SQLException {
        List<Answers> answersList = new ArrayList<>();
        String sql = "select * from asnwers";

        try(Connection con = dataSource.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql)){
            while(rs.next()){
                Long id = rs.getLong("id");
                String age = rs.getString("age");
                String gender = rs.getString("gender");

                Answers answers = new Answers(id, age, gender);
                answersList.add(answers);
            }
        }
        return answersList;
    }

    public Connection databaseConnect() throws Exception {
        log.info("Loading application properties");
        Properties properties = new Properties();
        properties.load(AnswerRepository.class.getClassLoader().getResourceAsStream("application.properties"));

        log.info("Connection to the database");
        Connection con = DriverManager.getConnection(properties.getProperty("url"), properties);
        log.info("Database connection test: " + con.getCatalog());

        log.info("Create database if not exist");
        Scanner scanner = new Scanner(AnswerRepository.class.getClassLoader().getResourceAsStream("schema.sql"));
        Statement statement = con.createStatement();
        while (scanner.hasNextLine()) {
            statement.execute(scanner.nextLine());
        }
        return con;
    }

    public void addAnswers(Answers answers) throws SQLException, Exception {
        log.info("Start inserting new data");
        String sql = "INSERT INTO answers(age, gender) values (?, ?)";

        Connection con = databaseConnect();
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, answers.getAge());
        ps.setString(2, answers.getGender());
        ps.executeUpdate();
    }
}
