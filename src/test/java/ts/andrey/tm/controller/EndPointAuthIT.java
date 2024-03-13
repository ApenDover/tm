package ts.andrey.tm.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ts.andrey.tm.AbstractIT;
import ts.andrey.tm.tmconst.ApiConst;


class EndPointAuthIT extends AbstractIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void successPath() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ApiConst.ADMIN_TEST_API)
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("Andrey", "test")))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void forbiddenPath() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ApiConst.USER_TEST_API)
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("Andrey", "test")))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    void unauthorizedPath() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(ApiConst.USER_TEST_API)
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("Andrey", "errorPassword")))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    void unauthorizedPathErrorApi() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/errorapi")
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("errorLogin", "errorPassword")))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

}
