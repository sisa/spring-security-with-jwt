package io.sisa;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author isaozturk
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class AuthLoginTest extends BaseControllerTest {


	@Before
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders
				.webAppContextSetup(webApplicationContext)
				.build();
	}

	@Test
	public void authLogin() throws Exception {

		MockHttpServletResponse response = authLogin("sisa", "$2a$10$uH8hGTYmdIC/qiSbuFkY1ustH0.YcbdaFRYooDqIQhG8r14T/QtNu");

		assertThat(response.getStatus()).isEqualTo(200);
		token = response.getContentAsString();
		assertThat(token).isNotEmpty();

	}

	@Test
	public void userNameNotFound() throws Exception {

		MockHttpServletResponse response = authLogin("sisa", "$2a$10$uH8hGTYmdIC/qiSbuFkY1ustH0.YcbdaFRYooDqIQhG8r14T/Qt123");


		assertThat(response.getStatus()).isEqualTo(HttpStatus.UNAUTHORIZED.value());

	}

	@Test
	public void authLoginFail() throws Exception {

		MockHttpServletResponse response = authLogin("Test", "$2a$10$uH8hGTYmdIC/qiSbuFkY1ustH0.YcbdaFRYooDqIQhG8r14T/QtNu");


		assertThat(response.getStatus()).isEqualTo(HttpStatus.UNAUTHORIZED.value());

	}




}
