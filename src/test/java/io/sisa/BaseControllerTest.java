package io.sisa;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.sisa.core.model.domain.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * Created by isaozturk on 16.06.2019
 */

public abstract class BaseControllerTest {

    protected MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext webApplicationContext;

    @Autowired
    protected ObjectMapper objectMapper;

    protected String token;

    protected final String URL = "/api/v1";


    public MockHttpServletResponse authLogin(String userName, String password) throws Exception {

        AppUser user = new AppUser();
        user.setUsername(userName);
        user.setPassword(password);

        MvcResult mvcResult = mockMvc.perform(
                post(URL + "/auth/login")
                        .content(objectMapper.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andReturn();

        return mvcResult.getResponse();

    }
}
