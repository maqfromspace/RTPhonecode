package app.controller;

import app.JSONHelper;
import app.dao.RecordDAO;
import app.service.RecordService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SmokeTest {

    @Autowired
    private MainController controller;
    @Autowired
    private JSONHelper jsonHelper;
    @Autowired
    private RecordDAO dao;
    @Autowired
    private RecordService service;

    @Value("${URL.COUNTRY}")
    String countryUrl;

    @Value("${URL.PHONE}")
    String phoneUrl;

    @Test
    public void testContexLoadController() throws Exception {
        assertThat(controller).isNotNull();
    }
    @Test
    public void testContexLoadJSONHelper() throws Exception {
        assertThat(jsonHelper).isNotNull();
    }
    @Test
    public void testContexLoadService() throws Exception {
        assertThat(service).isNotNull();
    }
    @Test
    public void testContexLoadDAO() throws Exception {
        assertThat(dao).isNotNull();
    }

    @Test
    public void testCountryURL(){
        assertThat(countryUrl.equals("http://country.io/names.json")).isTrue();
    }

    @Test
    public void testPhoneURL(){
        assertThat(phoneUrl.equals("http://country.io/phone.json")).isTrue();
    }
}