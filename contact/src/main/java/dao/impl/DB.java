package dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {

    private static final String JDBC = "jdbc:mysql:";
    private static final String HOST_AND_PORT = "//localhost:3306";
    private static final String NAME_DB = "/address_book";
    private static final String UNICODE = "useUnicode=true";
    private static final String JDBC_TIME_ZONE_SHIFT = "useJDBCCompliantTimezoneShift=true";
    private static final String DATE_TIME = "useLegacyDatetimeCode=false";
    private static final String SERVICE_TOME_ZONE = "serverTimezone=UTC";
    private static final String SEPARATOR = "?";
    private static final String SEPARATOR_AND = "&";
    private static final String FULL_URL = JDBC + HOST_AND_PORT + NAME_DB + SEPARATOR +
            UNICODE + SEPARATOR_AND + JDBC_TIME_ZONE_SHIFT +
            SEPARATOR_AND + DATE_TIME + SEPARATOR_AND + SERVICE_TOME_ZONE;
    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS contacts(" +
            "  `id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT, " +
            "  `name` VARCHAR(255) NOT NULL, " +
            "  `sur_name` VARCHAR(255) NULL, " +
            "  `phone_number` VARCHAR(45) NOT NULL, " +
            "  `age` INT NULL, " +
            "  `height` FLOAT NULL, " +
            "  `maritalStatus` BOOLEAN DEFAULT false, " +
            "  `create_data_time` VARCHAR(45))";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    static final String INSERT_CONTACT = "insert into contacts " +
            "(name, sur_name, phone_number, age, height, maritalStatus, create_data_time) " +
            "VALUES (?,?,?,?,?,?,?)";
    static final String UPDATE_CONTACT = "UPDATE contacts " +
            "SET name = ?, sur_name = ?, phone_number = ?, age = ?, height = ?, maritalStatus = ? " +
            "WHERE id = ?";
    static final String DELETE_CONTACT = "DELETE FROM contacts WHERE id = ?";
    protected static final String GET_CONTACT = "SELECT FROM contacts WHERE id = ?";
    static final String SELECT_ALL = "select * from contacts";

    private static Connection connection = null;

    static Connection getConnect() {
        if (connection != null) {
            return connection;
        } else {
            try {
                connection = DriverManager.getConnection(FULL_URL, USER, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return connection;
        }
    }

    public static void createAndConnectDB() {
        Statement statement;
        try {
            statement = getConnect().createStatement();
            statement.execute(CREATE_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeDB(){
        try {
            getConnect().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
