package app;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Component
public class JSONHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(JSONHelper.class);

    HttpURLConnection connection;

    @Cacheable(
            value = "jsonhelpercache",
            key = "#urlString")
    public String getJsonByURL(String urlString) {
        try {
            LOGGER.info("Начинаем получение JSON с источника " + urlString + "...");
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            String result = IOUtils.toString(connection.getInputStream(), StandardCharsets.UTF_8);
            connection.disconnect();
            LOGGER.info("JSON с источника " + urlString + " получен");
            return result;
        } catch (Exception e) {
            LOGGER.error("При получении JSON с " + urlString + " возникло исключение:\n" + e.getMessage());
            if (connection != null)
                connection.disconnect();
            return null;
        }
    }
}