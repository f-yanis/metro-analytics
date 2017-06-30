package ru.fyanis.metro_analytics;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/metro_db?useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1234";
    private static final DBConect DB_CONECT = new DBConect(URL, USERNAME, PASSWORD);

    public static void main(String[] args) {
        final String TIPES_TICKETS_PERIOD = "SELECT type_ticket, count(type_ticket)" +
                "FROM passes_select " +
                "WHERE data_passe > " + args[1] + " " + args[2] + " AND data_passe > " + args[3] + " " + args[4] +
                "GROUP BY type_ticket " +
                "ORDER BY count(type_ticket) DESC;";
        final String METRO_ANALYTIC_TIME = "SELECT data_passe, count(data_passe) " +
                "FROM passes_select " +
                "GROUP BY data_passe " +
                "ORDER BY count(data_passe) DESC;";
        final String TICKETS = "SELECT card_number, balance " +
                "FROM tickets WHERE NOT balance = 0 ORDER BY balance DESC;";
        final String METRO_RATE_PERIOD = "SELECT name_station, count(name_station) " +
                "FROM passes_select " +
                "WHERE data_passe > " + args[1] + " " + args[2] + " AND data_passe > " + args[3] + " " + args[4] +
                "GROUP BY name_station " +
                "ORDER BY count(name_station) DESC;";

        if (args[0].equals("ttp")) {
            showTipesTicketsPeriod(TIPES_TICKETS_PERIOD);
        } else if (args[0].equals("mat")) {
            showMetroAnalyticTime(METRO_ANALYTIC_TIME);
        } else if (args[0].equals("tic")) {
            showTickets(TICKETS);
        } else if (args[0].equals("mrp")) {
            showMetroRatePeriod(METRO_RATE_PERIOD);
        }
    }

    /**Метод получаес из БД и выводит в консоль популярные типы проездных в заданный период времени
     * @param TIPES_TICKETS_PERIOD
     */
    public static void showTipesTicketsPeriod(final String TIPES_TICKETS_PERIOD) {

        try (Connection connection = DB_CONECT.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(TIPES_TICKETS_PERIOD);

            String type_ticket;
            int count;

            while (resultSet.next()) {
                type_ticket = resultSet.getString("type_ticket");
                count = resultSet.getInt("count(type_ticket)");
                System.out.println("Тип проездного: " + type_ticket + "     Кол-во проходов: " + count);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**Метод получаес из БД и выводит в консоль анализ популярности станций (рейтинг по загруженности)
     * за заданный период времени
     * @param METRO_RATE_PERIOD
     */
    public static void showMetroRatePeriod(final String METRO_RATE_PERIOD) {

        try (Connection connection = DB_CONECT.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(METRO_RATE_PERIOD);

            String name_station;
            String count;

            while (resultSet.next()) {
                name_station = resultSet.getString("name_station");
                count = resultSet.getString("count(name_station)");
                System.out.println("Станция метро: " + name_station + " Кол-во проходов: " + count);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**Метод получаес из БД и выводит в консоль список активных билетов с оставшимся количеством поездок
     * @param TICKETS
     */
    public static void showTickets(final String TICKETS) {

        try (Connection connection = DB_CONECT.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(TICKETS);

            int card_number;
            int balance;

            while (resultSet.next()) {
                card_number = resultSet.getInt("card_number");
                balance = resultSet.getInt("balance");
                System.out.println("Номер карты: " + card_number + "    Баланс: " + balance);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**Метод получаес из БД и выводит в консоль анализ загрузки метрополитена с учётом часов-пик
     * @param METRO_ANALYTIC_TIME
     */
    public static void showMetroAnalyticTime(final String METRO_ANALYTIC_TIME) {
        DBConect dbConect = new DBConect(URL, USERNAME, PASSWORD);

        try (Connection connection = dbConect.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(METRO_ANALYTIC_TIME);

            String data_passe;
            String count;

            while (resultSet.next()) {
                data_passe = resultSet.getString("data_passe");
                count = resultSet.getString("count(data_passe)");
                System.out.println("Время прохода: " + data_passe + "   Кол-во проходов: " + count);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}