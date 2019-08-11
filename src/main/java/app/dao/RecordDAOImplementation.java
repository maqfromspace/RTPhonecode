package app.dao;

import app.App;
import app.JSONHelper;
import app.domain.Record;
import net.sf.ehcache.CacheManager;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class RecordDAOImplementation implements RecordDAO{

    private static final Logger LOGGER = LoggerFactory.getLogger(RecordDAOImplementation.class);

    @Autowired
    JSONHelper helper;

    @Value("${URL.COUNTRY}")
    String countryUrl;

    @Value("${URL.PHONE}")
    String phoneUrl;

    @Override
    public List<Record> getRecordsBySubstringCountry(String substring){
        LOGGER.info("RecordDAOImplementation начинает создание набора записей...");

        List<Record> listOfRecords = new ArrayList<Record>();

        String lowercaseSubstring = substring.toLowerCase();

        JSONObject countryJSON = new JSONObject(helper.getJsonByURL(countryUrl));
        JSONObject phoneJSON = new JSONObject(helper.getJsonByURL(phoneUrl));

        Iterator<String> iterator = countryJSON.keys();

        while (iterator.hasNext()) {
            String key = iterator.next();
            Record record = new Record(
                    key,
                    countryJSON.getString(key),
                    phoneJSON.getString(key)
            );

            if (record.getCountry().toLowerCase().indexOf(lowercaseSubstring) == 0)
                listOfRecords.add(record);
        }
        LOGGER.info("Набор записей создан: " + listOfRecords.toString());
        return listOfRecords;
    }

    public void uploadCache(){
        LOGGER.info("RecordDAOImplementation обновляет кэш");

        CacheManager manager = CacheManager.create();
        manager.clearAll();

        JSONObject countryJSON = new JSONObject(helper.getJsonByURL(countryUrl));
        JSONObject phoneJSON = new JSONObject(helper.getJsonByURL(phoneUrl));
        LOGGER.info("RecordDAOImplementation закончил обновления кэша");
    }
}