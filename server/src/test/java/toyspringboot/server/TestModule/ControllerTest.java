package toyspringboot.server.TestModule;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import toyspringboot.server.ServerApplicationTests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@AutoConfigureMockMvc
public class ControllerTest extends ServerApplicationTests {
    @Autowired
    ObjectMapper mapper;

    @Autowired
    MockMvc mockMvc;

    public String getRequestJson(Object object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }

    public boolean sendRequest(String api, String json, MediaType mediaType) throws Exception {
        mockMvc.perform(post(api)
                .content(json)
                .contentType(mediaType)
        );

        return true;
    }
}
