package com.volvadvit.internshipparsing.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Vadim Volkov
 * volvadvit@gmail.com
 */

@SpringBootTest
@AutoConfigureMockMvc
public class MainControllerTest {

    @Autowired
    private MainController mainController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void contextLoads() throws Exception {
        assertThat(mainController).isNotNull();
    }

    @Test
    public void shouldReturnGreetingTitle() throws Exception {
        this.mockMvc
                .perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Html parser")));
    }

    @Test
    public void inputFormOkTest() throws Exception {
        this.mockMvc
                .perform(post("/").param("url", "https://www.simbirsoft.com/"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void inputFormBadRequestTest() throws Exception {
        this.mockMvc
                .perform(post("/").param("url", "ottps://www.simbirsoft.com/"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Error. Try later or change url. ")));
    }
}
