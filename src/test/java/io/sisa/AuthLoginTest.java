package io.sisa;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.sisa.core.model.domain.AppUser;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * @author isaozturk
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthLoginTest {

	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext webApplicationContext;
	@Autowired
	private ObjectMapper objectMapper;

	@Before
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders
				.webAppContextSetup(webApplicationContext)
				.build();
	}

	@Test
	public void authLogin() throws Exception {

		AppUser user = new AppUser();
		user.setUsername("sisa");
		user.setPassword("$2a$10$uH8hGTYmdIC/qiSbuFkY1ustH0.YcbdaFRYooDqIQhG8r14T/QtNu");

		MvcResult mvcResult = mockMvc.perform(
				post("/auth/login")
                        .content(objectMapper.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
				.andReturn();

		assertThat(mvcResult.getResponse().getStatus()).isEqualTo(200);
		String token  = mvcResult.getResponse().getContentAsString();
		assertThat(token).isNotEmpty();

	}

	@Test
	public void authLoginFail() throws Exception {

		AppUser user = new AppUser();
		user.setUsername("Test");
		user.setPassword("$2a$10$uH8hGTYmdIC/qiSbuFkY1ustH0.YcbdaFRYooDqIQhG8r14T/QtNu");

		MvcResult mvcResult = mockMvc.perform(
				post("/auth/login")
						.content(objectMapper.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

		assertThat(mvcResult.getResponse().getStatus()).isEqualTo(HttpStatus.UNAUTHORIZED.value());

	}




}
