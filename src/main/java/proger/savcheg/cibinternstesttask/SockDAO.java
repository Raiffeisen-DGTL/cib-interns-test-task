package proger.savcheg.cibinternstesttask;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.sql.*;

/**
 * Класс-компонент с аннотацией @Component реализующий паттерн Data Access Object(DAO)
 */
@Component
public class SockDAO {
    /**
     * Параметры подключения к БД
     * P.S. не смог вынести в файл *.properties
     * Данные для подключения даны Heroku
     */
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://ec2-54-155-61-133.eu-west-1.compute.amazonaws.com:5432/d5mnf88jn2nbdo";
    private static final String USERNAME = "trdvvdjlgyiesc";
    private static final String PASSWORD = "23fe60e0d4ea318aa4142c4ebfcad6199137945940d2b0e946842d2187760c9a";

    private static Connection connection;

    /**
     * Обработка подключения к реляционной БД postgreSQL
     *
     * Инициализация БД описана скриптом initDB_postgres.sql в папке resources/db
     * Там же находится pg_dump CSV файл локальной БД postgreSQL
     */
    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Добавление нового типа Носков в БД
     */
    public void newSocks(Sock sock) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("insert into sockslist values (?,?,?,?)");
            preparedStatement.setInt(1, sock.hashCode());
            preparedStatement.setString(2, sock.getColor());
            preparedStatement.setInt(3, sock.getCottonPart());
            preparedStatement.setInt(4, sock.getQuantity());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Реализация GET /api/socks с параметрами color, operation, cottonPart
     */
    public ResponseEntity<Integer> showWithParam(String color, String operation, int cottonPart) {
        Sock sock = new Sock();
        try {
            PreparedStatement preparedStatementMT =
                    connection.prepareStatement("select * from sockslist where cottonpart > ? and color = ?");
            PreparedStatement preparedStatementLT =
                    connection.prepareStatement("select * from sockslist where cottonpart < ? and color = ?");
            PreparedStatement preparedStatementE =
                    connection.prepareStatement("select * from sockslist where cottonpart = ? and color = ?");
            preparedStatementMT.setInt(1, cottonPart);
            preparedStatementMT.setString(2, color);

            preparedStatementLT.setInt(1, cottonPart);
            preparedStatementLT.setString(2, color);

            preparedStatementE.setInt(1, cottonPart);
            preparedStatementE.setString(2, color);

            ResultSet resultSet;
            if (operation.equals("moreThan"))
                resultSet = preparedStatementMT.executeQuery();
            else if (operation.equals("lessThan"))
                resultSet = preparedStatementLT.executeQuery();
            else if (operation.equals("equal"))
                resultSet = preparedStatementE.executeQuery();
            else
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

            if(resultSet.next()) {
                sock.setId(resultSet.getInt("id"));
                sock.setColor(resultSet.getString("color"));
                sock.setCottonPart(resultSet.getInt("cottonpart"));
                sock.setQuantity(resultSet.getInt("quantity"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new ResponseEntity<>(sock.getQuantity(), HttpStatus.OK);
    }

    /**
     * Реализация POST /api/socks/income
     *
     * Если в БД есть объект с id = hash(color, cottonPart) то увеличить количество пар носков
     *
     * Иначе создать новый объект в таблице
     */
    public HttpStatus income(Sock sock) { //принимает JSON объект
        try {
            PreparedStatement checkStatement =
                    connection.prepareStatement("select * from sockslist where id = ?");
            PreparedStatement updateStatement =
                    connection.prepareStatement("update sockslist set quantity = quantity + ? where id = ?");
            checkStatement.setInt(1, sock.hashCode());
            ResultSet resultSet = checkStatement.executeQuery();
            if (!resultSet.next()) {
                sock.setId(sock.hashCode());
                newSocks(sock);
            } else {
                updateStatement.setInt(1, sock.getQuantity());
                updateStatement.setInt(2, sock.hashCode());
                updateStatement.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.OK;
    }

    /**
     * Реализация POST /api/socks/outcome
     *
     * Проверка на наличие объкта в БД -> Bad Request
     *
     * Проверка не приведёт ли разность к количеству меньше нуля -> Bad Request
     *
     * Проверка неприведёт ли разность к количеству равному нулю -> delete row
     *
     * Иначе увеличить количество пар на значение переданное в JSON-объекте -> OK
     */
    public HttpStatus outcome(Sock sock) { //принимает JSON объект
        try {
            PreparedStatement checkStatement =
                    connection.prepareStatement("select * from sockslist where id = ?");
            PreparedStatement updateStatement =
                    connection.prepareStatement("update sockslist set quantity = quantity - ? where id = ?");
            PreparedStatement deleteStatement =
                    connection.prepareStatement("delete from sockslist where id = ?");

            checkStatement.setInt(1, sock.hashCode());
            ResultSet resultSet = checkStatement.executeQuery();
            updateStatement.setInt(1, sock.getQuantity());
            updateStatement.setInt(2, sock.hashCode());
            deleteStatement.setInt(1, sock.hashCode());

            if (!resultSet.next())
                return HttpStatus.BAD_REQUEST;
            if (resultSet.getInt("quantity") < sock.getQuantity())
                return HttpStatus.BAD_REQUEST;
            if (resultSet.getInt("quantity") == sock.getQuantity()){
             deleteStatement.executeUpdate();
             return HttpStatus.OK;
            }
            updateStatement.executeUpdate();
            return HttpStatus.OK;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
