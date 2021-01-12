package com.example.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@WebMvcTest(StackController.class)
public class StackControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private StackDao stackDao;

    @Before
    public void setUp() {
        this.stackDao = new StackDao(5);
    }

    @Test
    public void testSetStackSize() throws Exception {

        mockMvc.perform(post("/setStackSize").param("capacity", String.valueOf(10)))
//                .content(asJsonString(new HashMap<String, Integer>().put("capacity", 10))))
                .andExpect(status().isOk());
    }

    @Test
    public void testViewStack() throws Exception {

        mockMvc.perform(get("/stack"))
                .andExpect(status().isOk());
    }

    @Test
    public void testPopStack() throws Exception {

        mockMvc.perform(post("/pop"))
                .andExpect(status().isOk());
    }

    @Test
    public void testPeekStack() throws Exception {

        mockMvc.perform(get("/top"))
                .andExpect(status().isOk());
    }

    @Test
    public void testPushStack() throws Exception {

        mockMvc.perform(post("/push").param("num", String.valueOf(10)))
                .andExpect(status().is3xxRedirection());
    }



    public static String asJsonString(final Object obj) throws Exception {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw e;
        }
    }




}
