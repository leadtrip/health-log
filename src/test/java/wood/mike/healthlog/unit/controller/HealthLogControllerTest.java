package wood.mike.healthlog.unit.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import wood.mike.healthlog.controller.HealthLogController;
import wood.mike.healthlog.repository.HealthLogRepository;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HealthLogController.class)
class HealthLogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private HealthLogRepository logRepository;

    @Test
    void shouldShowLogsPage() throws Exception {
        mockMvc.perform(get("/logs"))
                .andExpect(status().isOk())
                .andExpect(view().name("logs"));
    }

    @Test
    void shouldCreateNewLogEntry() throws Exception {
        mockMvc.perform(post("/logs")
                        .param("feeling", "GOOD")
                        .param("type", "GENERAL")
                        .param("note", "Feeling fine")
                        .param("timestamp", "2025-06-05T12:30"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/logs"));
    }

    @Test
    void shouldDeleteLogEntry() throws Exception {
        mockMvc.perform(post("/logs/delete/1")
                        .with(csrf())
                        .param("_method", "delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/logs"));
    }
}
