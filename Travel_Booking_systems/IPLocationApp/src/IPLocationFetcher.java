import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class IPLocationFetcher {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(IPLocationFetcher::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("IP Location Finder");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250);

        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Arial", Font.PLAIN, 14));

        JButton fetchButton = new JButton("Fetch Location");
        fetchButton.addActionListener(e -> {
            String location = fetchLocation();
            resultArea.setText(location);
        });

        frame.setLayout(new BorderLayout());
        frame.add(fetchButton, BorderLayout.NORTH);
        frame.add(new JScrollPane(resultArea), BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private static String fetchLocation() {
        try {
            String apiURL = "https://ipinfo.io/json";
            URL url = URI.create(apiURL).toURL(); // Replaces deprecated new URL(String)
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder jsonBuilder = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                jsonBuilder.append(inputLine);
            }
            in.close();

            JSONObject json = new JSONObject(jsonBuilder.toString());
            String ip = json.optString("ip");
            String city = json.optString("city");
            String region = json.optString("region");
            String country = json.optString("country");
            String loc = json.optString("loc");

            return String.format(
                    "IP Address: %s\nCity: %s\nRegion: %s\nCountry: %s\nCoordinates: %s\n\nMap: https://www.google.com/maps?q=%s",
                    ip, city, region, country, loc, loc);
        } catch (Exception e) {
            return "Error fetching location: " + e.getMessage();
        }
    }
}
