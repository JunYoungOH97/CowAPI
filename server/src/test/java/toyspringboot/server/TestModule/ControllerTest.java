package toyspringboot.server.TestModule;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import toyspringboot.server.ServerApplicationTests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@AutoConfigureMockMvc
public class ControllerTest extends ServerApplicationTests {
    @Autowired
    ObjectMapper mapper;

    @Autowired
    MockMvc mockMvc;

    public String getRequestJson(Object object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
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

    public ResultActions deleteRequest(String api, String header, String json, MediaType mediaType) throws Exception {
        return mockMvc.perform(delete(api)
                .header(HttpHeaders.AUTHORIZATION, header)
                .content(json)
                .contentType(mediaType));
    }
}
