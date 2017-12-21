package io.sisa;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.sisa.core.model.domain.AppUser;
import io.sisa.core.model.domain.City;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author isaozturk
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class CityControllerTest {

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private ObjectMapper objectMapper;
    private String token;

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();


        AppUser user = new AppUser();
        user.setUsername("sisa");
        user.setPassword("$2a$10$uH8hGTYmdIC/qiSbuFkY1ustH0.YcbdaFRYooDqIQhG8r14T/QtNu");

        MvcResult mvcResult = mockMvc.perform(
                post("/auth/login")
                        .content(objectMapper.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(200);
        token  = mvcResult.getResponse().getContentAsString();
        assertThat(token).isNotEmpty();


    }

    @Test
    public void citiesTest() throws Exception {


        MvcResult mvcResult = mockMvc.perform(
                get("/cities")
                        .header("Authorization","Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(200);
        City[] cities  = objectMapper.readValue(mvcResult.getResponse().getContentAsByteArray(), City[].class);
        assertThat(cities).isNotNull();
        Arrays.stream(cities).forEach(System.out::println);

    }

    @Test
    public void citiesByIdTest() throws Exception {


        MvcResult mvcResult = mockMvc.perform(
                get("/cities/1")
                        .header("Authorization","Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andReturn();

        assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.FORBIDDEN.value());

    }
}
