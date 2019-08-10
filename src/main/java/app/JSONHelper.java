package app;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Component
public class JSONHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(JSONHelper.class);

    HttpURLConnection connection;

    public String getJsonByURL(String urlString) {
        try {
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            String result = IOUtils.toString(connection.getInputStream(), StandardCharsets.UTF_8);
            connection.disconnect();
            return result;
        } catch (Exception e) {
            LOGGER.error("При получении JSON с " + urlString + " возникло исключение:\n" + e.getMessage());
            if (connection != null)
                connection.disconnect();
            return null;
        }
    }
}
