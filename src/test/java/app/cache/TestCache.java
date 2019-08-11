package app.cache;

import app.JSONHelper;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//Тест кэша
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestCache {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    JSONHelper jsonHelper;
    @Value("${URL.COUNTRY}")
    String countryUrl;

    @Value("${URL.PHONE}")
    String phoneUrl;

    @Test
    public void testCache() throws Exception {
        //Очистка кэша
        CacheManager manager = CacheManager.create();
        manager.clearAll();

        //Проверяем, очищен ли кэш
        Cache cache = manager.getInstance().getCache("jsonhelpercache");
        assertThat(cache.get(countryUrl)).isNull();
        assertThat(cache.get(phoneUrl)).isNull();

        //Выполняем получение JSON c countryUrl и phoneUrl
        String jsonCountry = jsonHelper.getJsonByURL(countryUrl);
        String jsonPhone = jsonHelper.getJsonByURL(phoneUrl);

        //Проверяем, создался ли кэш
        assertThat(cache.get(countryUrl)).isNotNull();
        assertThat(cache.get(phoneUrl)).isNotNull();

        //Получаем значение кэша для countryUrl и phoneUrl
        String jsonCountryFromCache =
                (String) cache.get(countryUrl).getObjectValue();
        String jsonPhoneFromCache =
                (String) cache.get(phoneUrl).getObjectValue();

        //Сравниваем значение из кэша с результатом выполнения jsonHelper.getJsonByURL
        assertThat(jsonCountryFromCache).isEqualTo(jsonCountry);
        assertThat(jsonPhoneFromCache).isEqualTo(jsonPhone);
    }

    @Test
    public void testCreateCacheAfterRequest() throws Exception {

        //Очищаем кэш
        CacheManager.getInstance().clearAll();

        //Посылаем get запрос
        MvcResult result = this.mockMvc
                .perform(get("/rest/code?country=be"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        //Проверяем наличие кэша для countryUrl и phoneUrl
        Cache cache = CacheManager.getInstance().getCache("jsonhelpercache");
        assertThat(cache.get(countryUrl)).isNotNull();
        assertThat(cache.get(phoneUrl)).isNotNull();
    }
}
