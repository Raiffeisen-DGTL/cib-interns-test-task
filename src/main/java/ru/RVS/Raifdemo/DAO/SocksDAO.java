package ru.RVS.Raifdemo.DAO;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class SocksDAO implements InitializingBean {

    private Logger log = Logger.getLogger(getClass().getName()); // дебаг на случай приколов
    static final String dbPath = "socksDB.db";    // Хардкодинг, но щито поделать

    @Override
    public void afterPropertiesSet() throws Exception {
        initDb();
    }

    /*  Инициализация */
    public void initDb() {
        try {
            Class.forName("org.sqlite.JDBC");
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath)) {
                System.out.println("Подключение прошло успешно!");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            log.log(Level.WARNING, "База не подключена", ex);
            ex.printStackTrace();
        }
    }

    /*
     * Для читателей: -1 - 200, -2 - 400, -3 - 500
     * */

    /*
    * Метод income - добавление носков на склад. Присутствуют проверки на корректность введённых данных!
    * Данные передаются в формате строки и парсятся внутри метода.
    * @param color - цвет носков
    * @param cottonPartS - процент хлопка
    * @param quantityS - количество пар
    * */
    public int income(String color, String cottonPartS, String quantityS) {
        StringBuilder query = new StringBuilder();
        int cottonPart = 0, quantity = 0;
        try {
            cottonPart = Integer.parseInt(cottonPartS);
            quantity = Integer.parseInt(quantityS);
        } catch (NumberFormatException e) {
            log.log(Level.WARNING, "Ошибка преобразования числа!");
            return -2;
        }
        // Проверка соответствию (чтоб не было 146% или отрицательных значений)
        if (!color.equals("") & !(cottonPart < 0 | cottonPart > 100) & !(quantity < 0)) {
            query.append("SELECT quantity FROM socks WHERE color='").append(color)
                    .append("' AND cottonPart=").append(cottonPart).append(";");
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
                 Statement stat = conn.createStatement()) {
                ResultSet resultSet = stat.executeQuery(String.valueOf(query));
                resultSet.next(); // Получаем количество столбцов (чтоб понимать, а есть ли оно?)
                query.setLength(0); // удаляем старую строку
                if (resultSet.getRow() > 0) {
                    int quantityFromDB = resultSet.getInt("quantity");
                    quantityFromDB = quantityFromDB + quantity;
                    query.append("UPDATE socks SET quantity=").append(quantityFromDB)
                            .append(" WHERE color='").append(color)
                            .append("' AND cottonPart=").append(cottonPart).append(";");
                } else {
                    query.append("INSERT INTO socks(color, cottonPart, quantity) values ('")
                            .append(color).append("', ").append(cottonPart).append(", ").append(quantity).append(");");
                }
                stat.close(); // Закрываем предыдущий запрос, чтоб не блокировать БД (актуально для SQLite)
                Connection conn2 = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
                PreparedStatement stat2 = conn2.prepareStatement(String.valueOf(query));
                stat2.execute();
                stat2.close();
                return -1;
            } catch (SQLException ex) {
                log.log(Level.WARNING, "Не удалось выполнить запрос");
                return -3;
            }
        } else {
            return -2;
        }
    }

    /*
     * Метод outcome - удаление носков на склад. Присутствуют проверки на корректность введённых данных!
     * Данные передаются в формате строки и парсятся внутри метода.
     * @param color - цвет носков
     * @param cottonPartS - процент хлопка
     * @param quantityS - количество пар
     *
     * В целом, аналогичен предыдущему методу по входным параметрам, но не алгоритму работы.
     * */
    public int outcome(String color, String cottonPartS, String quantityS) {
        StringBuilder query = new StringBuilder();
        int cottonPart = 0, quantity = 0;
        try {
            cottonPart = Integer.parseInt(cottonPartS);
            quantity = Integer.parseInt(quantityS);
        } catch (NumberFormatException e) {
            log.log(Level.WARNING, "Ошибка парсинга JSON");
            return -2;
        }
        if (!color.equals("") & !(cottonPart < 0 | cottonPart > 100) & !(quantity > 0)) {
            query.append("SELECT quantity FROM socks WHERE color='").append(color)
                    .append("' AND cottonPart=").append(cottonPart).append(";");
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
                 Statement stat = conn.createStatement()) {
                ResultSet resultSet = stat.executeQuery(String.valueOf(query));
                resultSet.next();
                query.setLength(0);
                if (resultSet.getRow() > 0) {
                    int quantityFromDB = resultSet.getInt("quantity");
                    quantityFromDB = quantityFromDB + quantity;
                    if (quantityFromDB < 0) {
                        return -2;
                    }
                    query.append("UPDATE socks SET quantity=").append(quantityFromDB)
                            .append(" WHERE color='").append(color)
                            .append("' AND cottonPart=").append(cottonPart).append(";");
                } else {
                    return -2;
                }
                stat.close();
                Connection conn2 = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
                PreparedStatement stat2 = conn2.prepareStatement(String.valueOf(query));
                stat2.execute();
                stat2.close();
                return -1;
            } catch (SQLException ex) {
                log.log(Level.WARNING, "Не удалось выполнить запрос");
                return -3;
            }
        } else {
            return -2;
        }
    }

    /*
     * Метод socks - получение количества новсков по заданным параметрам.
     * Данные передаются в формате строки и парсятся внутри метода.
     * @param color - цвет носков
     * @param operation - метод сравнения для поиска в бд (больше, меньше или равно)
     * @param cottonPartS - процент хлопка
     * */
    public int socks(String color, String operation, String cottonPartS) {
        StringBuilder query = new StringBuilder();
        int cottonPart = 0;
        try {
            cottonPart = Integer.parseInt(cottonPartS);
            if (cottonPart > 100 | cottonPart < 0) {
                return -2;
            }
        } catch (NumberFormatException e) {
            log.log(Level.WARNING, "Ошибка преобразования числа!");
            return -2;
        }
        switch (operation) {
            case "moreThan":
                query.append("SELECT quantity FROM socks WHERE color='").append(color)
                        .append("' AND cottonPart BETWEEN ").append(cottonPart).append(" AND 100;");
                break;
            case "lessThan":
                query.append("SELECT quantity FROM socks WHERE color='").append(color)
                        .append("' AND cottonPart BETWEEN 0 AND ").append(cottonPart).append(";");
                break;
            case "equal":
                query.append("SELECT quantity FROM socks WHERE color='").append(color)
                        .append("' AND cottonPart=").append(cottonPart).append(";");
                break;
            default:
                return -2;
        }
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
             Statement stat = conn.createStatement()) {
            ResultSet resultSet = stat.executeQuery(String.valueOf(query));
            int sum = 0, getQuantityFromDB = 0;
            while (resultSet.next()) {  // в цикле сложение resultSet'а каждой строки, полученной из БД.
                getQuantityFromDB = resultSet.getInt("quantity");
                sum = sum + getQuantityFromDB;
            }
            stat.close();
            return sum;
        } catch (SQLException ex) {
            log.log(Level.WARNING, "Не удалось выполнить запрос");
            return -3;
        }
    }
}