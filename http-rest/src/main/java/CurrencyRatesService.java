import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CurrencyRatesService {

    private final static String URL = "https://api.exchangeratesapi.io/latest?symbols=RON";

    public double getEuroRonRate() {
        ResponseEntity<String> result = new RestTemplate().getForEntity(URL, String.class);
        String ron = result.getBody().substring(result.getBody().lastIndexOf("{"), result.getBody().indexOf("}") + 1);
        Matcher m = Pattern.compile("[0-9]+(\\.[0-9]+)?").matcher(ron);
        m.find();
        return Double.parseDouble(m.group());
    }


    public double getEuroRonRateWithUrlConnection() {
        try {
            URL url = new URL(URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("GET");
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                System.out.println("could not retrieve EURO RON rates");
            }
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            conn.disconnect();
            String rates = sb.toString();
            String ron =rates.substring(rates.lastIndexOf("{"),
                    rates.indexOf("}") + 1);
            Matcher m = Pattern.compile("[0-9]+(\\.[0-9]+)?").matcher(ron);
            m.find();
            return Double.parseDouble(m.group());
        } catch (Exception e) {
            e.printStackTrace();
            return Double.MIN_VALUE;
        }
    }

}
