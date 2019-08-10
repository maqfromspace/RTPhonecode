package app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/rest/code")
public class MainController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    @GetMapping
    public ResponseEntity<String> getInfo(@RequestParam(name = "country") String country) throws Exception {
        LOGGER.info("Получен GET запрос GET /rest/code?country=" + country);
        return ResponseEntity.ok("OK");
    }
}
