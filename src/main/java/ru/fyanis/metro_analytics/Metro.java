package ru.fyanis.metro_analytics;


public class Metro {
    private static final String URL = "jdbc:mysql://localhost:3306/metro_db?useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1234";
    private static final DBConnect DB_CONNECT = new DBConnect(URL, USERNAME, PASSWORD);
    private static final Inquiry INQUIRY = new Inquiry(DB_CONNECT);

    public static void main(String[] args) {

        if (args[0].equals("ttp")) {
            final String TIPES_TICKETS_PERIOD = "SELECT type_ticket, count(type_ticket) " +
                    "FROM passes_select " +
                    "WHERE data_passe > " + args[1] + " " + args[2] + " AND data_passe > " + args[3] + " " + args[4] +
                    "GROUP BY type_ticket " +
                    "ORDER BY count(type_ticket) DESC;";

            INQUIRY.showTipesTicketsPeriod(TIPES_TICKETS_PERIOD);

        } else if (args[0].equals("mat")) {
            final String METRO_ANALYTIC_TIME = "SELECT data_passe, count(data_passe) " +
                    "FROM passes_select " +
                    "GROUP BY data_passe " +
                    "ORDER BY count(data_passe) DESC;";

            INQUIRY.showMetroAnalyticTime(METRO_ANALYTIC_TIME);

        } else if (args[0].equals("tic")) {
            final String TICKETS = "SELECT card_number, balance " +
                    "FROM tickets WHERE NOT balance = 0 ORDER BY balance DESC;";

            INQUIRY.showTickets(TICKETS);

        } else if (args[0].equals("mrp")) {
            final String METRO_RATE_PERIOD = "SELECT name_station, count(name_station) " +
                    "FROM passes_select " +
                    "WHERE data_passe > " + args[1] + " " + args[2] + " AND data_passe > " + args[3] + " " + args[4] +
                    "GROUP BY name_station " +
                    "ORDER BY count(name_station) DESC;";
            INQUIRY.showMetroRatePeriod(METRO_RATE_PERIOD);
        }
    }
}