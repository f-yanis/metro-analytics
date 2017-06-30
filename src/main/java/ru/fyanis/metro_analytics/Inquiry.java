package ru.fyanis.metro_analytics;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * Этот класс получает данные из БД и выводит их в консоль
 */
public class Inquiry {
    private final DBConnect DB_CONNECT;

    public Inquiry(DBConnect DB_CONNECT) {
        this.DB_CONNECT = DB_CONNECT;
    }

    /**
     * популярные типы проездных в заданный период времени
     *
     * @param TIPES_TICKETS_PERIOD
     */
    public void showTipesTicketsPeriod(final String TIPES_TICKETS_PERIOD) {

        try (Connection connection = DB_CONNECT.getConnection();
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

    /**
     * анализ популярности станций (рейтинг по загруженности)
     * за заданный период времени
     *
     * @param METRO_RATE_PERIOD
     */
    public void showMetroRatePeriod(final String METRO_RATE_PERIOD) {

        try (Connection connection = DB_CONNECT.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(METRO_RATE_PERIOD);

            String name_station;
            String count;

            while (resultSet.next()) {
                name_station = resultSet.getString("name_station");
                count = resultSet.getString("count(name_station)");
                System.out.println("Станция метро: " + name_station + "     Кол-во проходов: " + count);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * список активных билетов с оставшимся количеством поездок
     *
     * @param TICKETS
     */
    public void showTickets(final String TICKETS) {

        try (Connection connection = DB_CONNECT.getConnection();
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

    /**
     * анализ загрузки метрополитена с учётом часов-пик
     *
     * @param METRO_ANALYTIC_TIME
     */
    public void showMetroAnalyticTime(final String METRO_ANALYTIC_TIME) {

        try (Connection connection = DB_CONNECT.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(METRO_ANALYTIC_TIME);

            String data_passe;
            int count;

            while (resultSet.next()) {
                data_passe = resultSet.getString("data_passe");
                count = resultSet.getInt("count(data_passe)");
                System.out.println("Время прохода: " + data_passe + "   Кол-во проходов: " + count);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
