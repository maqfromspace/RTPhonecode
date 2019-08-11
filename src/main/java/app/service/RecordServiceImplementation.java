package app.service;

import app.dao.RecordDAO;
import app.domain.Record;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordServiceImplementation implements RecordService{

    private static final Logger LOGGER =
            LoggerFactory.getLogger(RecordServiceImplementation.class);

    @Autowired
    RecordDAO dao;

    @Override
    public List<Record> getRecordsBySubstringCountry(String letters) {
        LOGGER.info("RecordService обращается к RecordDAO для получения записей...");

        List<Record> records = dao.getRecordsBySubstringCountry(letters);

        LOGGER.info("RecordDAO вернул следующие записи: " + records);
        return records;

    }
}
