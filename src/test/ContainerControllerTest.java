package com.mmontaldo.caffainer.controller;

import com.mmontaldo.caffainer.service.DockerService;
import com.mmontaldo.caffainer.dto.ContainerDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;

import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ContainerController.class)
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
public class ContainerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DockerService dockerService;

    @BeforeEach
    public void setUp(WebApplicationContext context, RestDocumentationContextProvider restDocs) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
            .apply(documentationConfiguration(restDocs))
            .build();
    }

    @Test
    public void shouldReturnListOfRunningContainers() throws Exception {
        ContainerDto container = new ContainerDto("123", "nginx", "Up 5 minutes");

        given(dockerService.getRunningContainers()).willReturn(List.of(container));

        mockMvc.perform(get("/container/listRunningContainers"))
            .andExpect(status().isOk())
            .andDo(document("containers-list"));
    }
}
