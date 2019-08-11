package app.dao;

import app.domain.Record;

import java.util.List;

/**
 * DAO-слой, выполняющий загрузку записей
 */
public interface RecordDAO {
    List<Record> getRecordsBySubstringCountry(String substring);
    void uploadCache();
}
