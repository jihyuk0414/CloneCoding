package com.example.springsecuritypractice.notice;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class NoticeControllerTest {

    private MockMvc mockMvc;

    @BeforeEach
    public void setup (@Autowired WebApplicationContext webApplicationContext)
    {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .alwaysDo(print())
                .build() ;
    }

    @Test
    void getNotice_인증없다() throws Exception{
        mockMvc.perform(get("/notice"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/login")) ;
    }

    @Test
    @WithMockUser //가짜 user를 security에 등록 이후 진행
    void getNotice_인증있다() throws Exception{
        mockMvc.perform(get("/notice"))
                .andExpect(status().isOk());
    }


    @Test
    @WithMockUser(roles = {"ADMIN"}, username = "admin", password = "admin")
    void postNotice_어드민() throws Exception {
        mockMvc.perform(post("/notice").with(csrf())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("title", "제목")
                .param("content", "내용"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("notice")) ;

    }

}