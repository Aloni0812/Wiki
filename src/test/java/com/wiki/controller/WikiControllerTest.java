package com.wiki.controller;

import com.wiki.dto.WikiDto;
import com.wiki.model.Wiki;
import com.wiki.service.WikiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
public class WikiControllerTest {
  private MockMvc mockMvc;

  @Mock
  private WikiService wikiService;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.initMocks(this);
    WikiController wikiController = new WikiController(wikiService);
    mockMvc = MockMvcBuilders.standaloneSetup(wikiController).build();
  }

  @Test
  public void testFindAllWiki() throws Exception {
    // Arrange
    Wiki wiki1 = new Wiki();
    Wiki wiki2 = new Wiki();
    List<Wiki> wikiList = Arrays.asList(wiki1, wiki2);
    when(wikiService.findAllWiki()).thenReturn(wikiList);

    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/Wiki")
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();
  }

  @Test
  public void testSaveWiki() throws Exception {
    Wiki savedWiki = new Wiki();
    when(wikiService.saveWiki(any(WikiDto.class))).thenReturn(savedWiki);
    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/Wiki/saveWiki")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"property\":\"value\"}"))
            .andExpect(status().isOk())
            .andReturn();

    String responseContent = result.getResponse().getContentAsString();
  }

  @Test
  public void testFindByRequest() throws Exception {
    String request = "Test Request";
    Wiki wiki = new Wiki();
    when(wikiService.findByRequest(request)).thenReturn(wiki);
    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/Wiki/findByRequest?request=Test Request")
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();
  }

  @Test
  public void testUpdateWiki() throws Exception {
    Wiki updatedWiki = new Wiki();
    when(wikiService.updateWiki(any(WikiDto.class))).thenReturn(updatedWiki);
    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/Wiki/updateWiki")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"property\":\"value\"}"))
            .andExpect(status().isOk())
            .andReturn();
  }

  @Test
  public void testDeleteWiki() throws Exception {
    String request = "Test Request";
    mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/Wiki/deleteWiki?request=Test Request")
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    verify(wikiService).deleteWiki(request);
  }

  @Test
  public void testFindByRequestWikiAndAuthor() throws Exception {
    String requestWiki = "Test Request";
    String author = "Author";
    Wiki wiki = new Wiki();
    when(wikiService.findByRequestWikiAndAuthor(requestWiki, author)).thenReturn(wiki);
    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/Wiki/findByRequestAndAuthor")
                    .param("requestWiki", requestWiki)
                    .param("author", author)
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();
  }
}
