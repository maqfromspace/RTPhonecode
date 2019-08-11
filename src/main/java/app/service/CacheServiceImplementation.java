package app.service;

import app.JSONHelper;
import app.dao.RecordDAO;
import net.sf.ehcache.CacheManager;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CacheServiceImplementation implements CacheService{

    private static final Logger LOGGER =
            LoggerFactory.getLogger(RecordServiceImplementation.class);

    @Autowired
    RecordDAO dao;

    @Override
    public void uploadCache() {
        LOGGER.info("CacheService обращается к RecordDAO для обновления кэша...");

        dao.uploadCache();

        LOGGER.info("RecordDAO успешно обновил кэш");

    }
}
