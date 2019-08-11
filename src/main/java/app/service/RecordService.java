package app.service;

import app.domain.Record;

import java.util.List;

/**
 * Сервис получения записей
 */
public interface RecordService {
    public List<Record> getRecordsBySubstringCountry(String substring);
}