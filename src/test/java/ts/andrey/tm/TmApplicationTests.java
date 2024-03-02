package ts.andrey.tm;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class TmApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void SUCCESS_PATH() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/testAdmin")
						.with(SecurityMockMvcRequestPostProcessors.httpBasic("Andrey", "test")))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void FORBIDDEN_PATH() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/testUser")
						.with(SecurityMockMvcRequestPostProcessors.httpBasic("Andrey", "test")))
				.andExpect(MockMvcResultMatchers.status().isForbidden());
	}

	@Test
	void UNAUTHORIZED_PATH() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/testUser")
						.with(SecurityMockMvcRequestPostProcessors.httpBasic("Andrey", "errorPassword")))
				.andExpect(MockMvcResultMatchers.status().isUnauthorized());
	}

	@Test
	void UNAUTHORIZED_PATH_ERROR_API() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/errorapi")
						.with(SecurityMockMvcRequestPostProcessors.httpBasic("errorLogin", "errorPassword")))
				.andExpect(MockMvcResultMatchers.status().isUnauthorized());
	}


}
