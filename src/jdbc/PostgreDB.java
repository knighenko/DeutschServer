package jdbc;

import entity.Lesson;
import entity.Task;

import java.sql.*;

public class PostgreDB {
    //  Database credentials
    static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/ddb";
    static final String USER = "duser";
    static final String PASS = "123";

    /**
     * Create DB connection
     */
    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager
                    .getConnection(DB_URL, USER, PASS);
            // log.log(Level.INFO, "Connected to Database: " + DB_URL);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

    /**
     * Close DB connection
     */
    public static void closeConnection(Connection connection) throws SQLException {
        connection.close();
        // log.log(Level.INFO, "Closed connection to Database: " + DB_URL);
    }

    /**
     * Method create e_mail in table USERS
     * return true when create
     */
    public static boolean createUser(String e_mail, String password, String deviceToken) {
        boolean flag = false;
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("insert into ACCOUNTS (e_mail,password,devicetoken,push) values (?,?,?,?)");
            statement.setString(1, e_mail);
            statement.setString(2, password);
            statement.setString(3, deviceToken);
            statement.setBoolean(4, false);
            statement.executeUpdate();
            statement.close();
            closeConnection(connection);
            flag = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return flag;
    }

    /**
     * Method checks current e_mail and password in table USERS
     * return true when current e_mail is in the table
     */
    public static boolean checkUser(String e_mail, String password) {
        boolean flag = false;
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from Accounts where e_mail=" + "\'" + e_mail + "\'");
            while (rs.next()) {
                if (password.equals(rs.getString("password")))
                    flag = true;
            }
            statement.close();
            rs.close();
            closeConnection(connection);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return flag;
    }

    /**
     * Method checks current push from table USERS
     * return true when current e_mail has flag true in the table
     */
    public static boolean checkPush(String e_mail) {
        boolean flag = false;
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select push from Accounts where e_mail=" + "\'" + e_mail + "\'");
            while (rs.next()) {
                if (rs.getBoolean("push") == true)
                    flag = true;
            }
            statement.close();
            rs.close();
            closeConnection(connection);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return flag;
    }

    /**
     * method return Progress of the user
     */
    public static boolean myProgress(String e_mail) {
        boolean flag = false;

        return flag;
    }

    /**
     * Method return all lesoons
     */
    public static String getLessons() {

        StringBuffer lessons = new StringBuffer();

        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from Lessons");
            while (rs.next()) {
                Lesson lesson = new Lesson();
                lesson.setId(rs.getInt(1));
                lesson.setTitle(rs.getString(2));
                lesson.setYouTubeUrl(rs.getString(3));
                lesson.setServerUrl(rs.getString(4));

                lessons.append(lesson);
            }
            statement.close();
            rs.close();
            closeConnection(connection);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return lessons.toString();
    }

    /**
     * Method add task into the table Tasks
     */
    public static boolean addTask(String rusString, String deutschString, int lessonId) {
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("insert into Tasks (rusString, deutschString, lessonId) values (?,?,?)");
            statement.setString(1, rusString);
            statement.setString(2, deutschString);
            statement.setInt(3, lessonId);
            statement.executeUpdate();
            statement.close();
            closeConnection(connection);
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    /**
     * Method get tasks and answer from the table Tasks by lessonId
     */
    public static String getTasks(int lessonId) {
        StringBuffer tasks = new StringBuffer();
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select russtring, deutschstring from Tasks where lessonId=" + "\'" + lessonId + "\'");

            while (rs.next()) {
                Task task = new Task();
                task.setRus(rs.getString(1));
                task.setDeu(rs.getString(2));
                tasks.append(task);

            }
            statement.close();
            rs.close();
            closeConnection(connection);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println(tasks.toString());
        return tasks.toString();
    }

    /**
     * Method create  table e_mailLessonTasks
     * with tasks by lessonId
     */
    public static boolean createUserLessonTasks(String e_mail) {
        boolean flag = false;
        try {

            Connection connection = getConnection();
            String sql = "CREATE TABLE IF NOT EXISTS "+ e_mail+"LessonTasks ( TaskId integer ,    Checks boolean)" ;
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
            statement.close();
            closeConnection(connection);
            flag = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return flag;
    }

    /**
     * Method gets  lessonServerUrl from the table Lessons by id of lesson
     */
    public static String getServerUrlByIdLesson(String id) {
        String serverUrl = "false";
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select serverUrl from Lessons where id=" + "\'" + id + "\'");

            if (rs.next()) {
                serverUrl = rs.getString(1);

            }
            statement.close();
            rs.close();
            closeConnection(connection);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return serverUrl;

    }
}
