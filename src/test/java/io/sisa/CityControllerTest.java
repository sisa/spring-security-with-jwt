package io.sisa;

import io.sisa.core.model.domain.City;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * @author isaozturk
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class CityControllerTest extends BaseControllerTest {

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();

    }


    @Test
    public void citiesTest() throws Exception {

        MockHttpServletResponse response = authLogin("sisa", "$2a$10$uH8hGTYmdIC/qiSbuFkY1ustH0.YcbdaFRYooDqIQhG8r14T/QtNu");
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        token = response.getContentAsString();
        assertThat(token).isNotEmpty();


        MvcResult mvcResult = mockMvc.perform(
                get(URL + "/cities")
                        .header("Authorization","Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(200);
        City[] cities  = objectMapper.readValue(mvcResult.getResponse().getContentAsByteArray(), City[].class);
        assertThat(cities).isNotNull();
        Arrays.stream(cities).forEach(System.out::println);

    }

    @Test
    public void citiesByIdWithForbiddenTest() throws Exception {

        MockHttpServletResponse response = authLogin("sisa", "$2a$10$uH8hGTYmdIC/qiSbuFkY1ustH0.YcbdaFRYooDqIQhG8r14T/QtNu");
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        token = response.getContentAsString();
        assertThat(token).isNotEmpty();


        MvcResult mvcResult = mockMvc.perform(
                get(URL + "/cities/1")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andReturn();

        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.FORBIDDEN.value());

    }

    @Test
    public void citiesByIdTest() throws Exception {

        MockHttpServletResponse response = authLogin("admin", "$2a$10$HG0tQJHWZZen.kerZYz1rePqDx8EjI7LO.pDJOjF3udpWPTbfODF2");
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        token = response.getContentAsString();
        assertThat(token).isNotEmpty();

        MvcResult mvcResult = mockMvc.perform(
                get(URL + "/cities/1")
                        .header("Authorization","Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andReturn();

        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());

    }
}
