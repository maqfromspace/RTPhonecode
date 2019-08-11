package app.controller;

import app.domain.Record;
import app.service.CacheService;
import app.service.RecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/rest/code")
public class MainController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    @Autowired
    RecordService recordService;

    @Autowired
    CacheService cacheService;

    @GetMapping
    public ResponseEntity getInfo(@RequestParam(name = "country") String country) throws Exception {
        LOGGER.info("Получен GET запрос GET /rest/code?country=" + country);
        LOGGER.info("MainController обращается к RecordService для полуения записей...");

        List<Record> records = recordService.getRecordsBySubstringCountry(country);

        LOGGER.info("RecordService вернул следующие записи :" + records);
        return ResponseEntity.ok(records);
    }
    @GetMapping("/upload")
    public ResponseEntity getInfo() throws Exception {
        LOGGER.info("Получен GET запрос GET /rest/code/upload");
        LOGGER.info("MainController обращается к CacheService для обновления кэша...");

        cacheService.uploadCache();

        LOGGER.info("CacheService успешно обновил кэш");
        return ResponseEntity.ok("Кэш обновлен");
    }
}
