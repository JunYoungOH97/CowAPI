package toyspringboot.server.TestConfig;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Setter;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import toyspringboot.server.ServerApplicationTests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

public class ControllerTest {
    private static final String port = "8080";
    private static final String version = "/api/v1";
    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    protected MockMvc mockMvc;

    protected String baseUrl() {
        return "http://localhost:" + port;
    }

    protected String baseUrl(String path) {
        return this.baseUrl() + version + path;
    }


    public String getRequestJson(Object object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }

    public ResultActions postRequest(String api, String json, MediaType mediaType) throws Exception {
        return mockMvc.perform(post(api)
                .content(json)
                .contentType(mediaType));
    }

    public ResultActions postRequest(String api, String header, String json, MediaType mediaType) throws Exception {
        return mockMvc.perform(post(api)
                .header(HttpHeaders.AUTHORIZATION, header)
                .content(json)
                .contentType(mediaType));
    }

    public ResultActions putRequest(String api, String header, String json, MediaType mediaType) throws Exception {
        return mockMvc.perform(put(api)
                .header(HttpHeaders.AUTHORIZATION, header)
                .content(json)
                .contentType(mediaType));
    }

    public ResultActions getRequest(String api, String header, MediaType mediaType) throws Exception {
        return mockMvc.perform(get(api)
                .header(HttpHeaders.AUTHORIZATION, header)
                .contentType(mediaType));
    }

    public ResultActions getRequest(String api, MediaType mediaType) throws Exception {
        return mockMvc.perform(get(api)
                .contentType(mediaType));
    }


    public ResultActions deleteRequest(String api, String header, String json, MediaType mediaType) throws Exception {
        return mockMvc.perform(delete(api)
                .header(HttpHeaders.AUTHORIZATION, header)
                .content(json)
                .contentType(mediaType));
    }

    public ResultActions deleteRequest(String api, String header, MediaType mediaType) throws Exception {
        return mockMvc.perform(delete(api)
                .header(HttpHeaders.AUTHORIZATION, header)
                .contentType(mediaType));
    }
}
