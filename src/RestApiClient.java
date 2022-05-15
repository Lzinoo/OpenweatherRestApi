import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;
import org.json.JSONObject;

public class RestApiClient {

    public static void main(String[] args) throws IOException{

        double lat = 57.708870;
        double lon = 11.974560;
        String apiKey = "d979a3548d2e64c263835b0e5f2b925";

        String url = "https://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + lon + "&appid=" + apiKey + "8&units=metric";

        String jsonString = getData(url);
        JSONObject jsonObject = new JSONObject(jsonString);
        // System.out.println(jsonObject);

        String location = jsonObject.getString("name");
        int visibility = jsonObject.getInt("visibility");

        JSONObject temperatureJsonObject = jsonObject.getJSONObject("main");
        double currentTemp = temperatureJsonObject.getDouble("temp");
        double tempMin = temperatureJsonObject.getDouble("temp_min");


        System.out.println("Location: " + location);
        System.out.println("Current temperature: " + currentTemp);
        System.out.println("Minimum temperature: " + tempMin);

    }

    public static String getData(String url) throws IOException{

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if(responseCode == 200){
            String response = "";
            Scanner scanner = new Scanner(connection.getInputStream());
            while(scanner.hasNextLine()){
                response += scanner.nextLine();
                response += "\n";
            }
            scanner.close();

            return response;
        }

        // an error happened
        return null;
    }
}