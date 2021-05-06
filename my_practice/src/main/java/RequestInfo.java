import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;
import java.util.Comparator;


public class RequestInfo {
    private final String url = "jdbc:postgresql://localhost:5432/postgres";
    private final String user = "postgres";
    private final String password = "root";
    private final String query = "INSERT INTO logs.user_logs(created_at, first_name, message, second_name, user_id) VALUES (?, ?, ?, ?, ?)";

    public RequestInfo() {

    }

    public void makeRequest(String year, String month, String day) throws IOException {
        URL url = new URL("http://www.dsdev.tech/logs/" + year + month + day);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestProperty("accept", "application/json");

        InputStream responseStream = connection.getInputStream();

        ObjectMapper mapper = new ObjectMapper();
        Info info = mapper.readValue(responseStream, Info.class);

        Connection postgrescon = connect();

//        for (var key: info.logs) {
//            System.out.println(key.created_at);
//        }
        info.logs.sort(Comparator.comparing(UserData::getCreated));

//        System.out.println("SORTED==================================");
//        for (var key: info.logs) {
//            System.out.println(key.created_at);
//        }

        for (var key: info.logs) {
            try {
                PreparedStatement pst = postgrescon.prepareStatement(query);
                pst.setString(1, String.valueOf(key.created_at));
                pst.setString(2, key.first_name);
                pst.setString(3, key.message);
                pst.setString(4, key.second_name);
                pst.setInt(5, key.user_id);
                pst.executeUpdate();
            } catch (SQLException sqlEx) {
                System.out.println(sqlEx.toString());
            }
        }
    }

    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }
}
