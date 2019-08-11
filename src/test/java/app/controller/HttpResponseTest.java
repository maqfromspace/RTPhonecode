package app.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Тест возвращаемого ответа
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HttpResponseTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testHTTPResponseCountry() throws Exception {
        MvcResult result = this.mockMvc
                .perform(get("/rest/code?country=be"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        String content = result.getResponse().getContentAsString();

        //Ключи JSON
        Set<String> keysList = new HashSet<String>();

        JSONArray jsonArray = new JSONArray(content);
        JSONObject json = (JSONObject) jsonArray.get(0);

        Iterator keysToCopyIterator = json.keys();

        //Заполяем set ключей
        while (keysToCopyIterator.hasNext()) {
            String key = (String) keysToCopyIterator.next();
            keysList.add(key);
        }

        //Проверяем наличие необходимых ключей в set
        assertThat(keysList.contains("name")).isTrue();
        assertThat(keysList.contains("country")).isTrue();
        assertThat(keysList.contains("code")).isTrue();

        //Очищаем список ключей нужных
        keysList.remove("name");
        keysList.remove("country");
        keysList.remove("code");

        //Проверяем, отсутвие других ключей
        assertThat(keysList.size() == 0).isTrue();

        //Проверяем на наличие подстроки "be" в поле "country" файла json
        for (int i = 0; i < jsonArray.length(); i++) {

            String lowercaseCountry =
                    ((JSONObject) jsonArray.get(i))
                            .getString("country")
                            .toLowerCase();

            boolean isStartedBySubstring =
                    lowercaseCountry.indexOf("be") == 0;
            assertThat(isStartedBySubstring).isTrue();
        }
    }
}
