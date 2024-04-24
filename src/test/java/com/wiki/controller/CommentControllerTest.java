package com.wiki.controller;

import com.wiki.dto.CommentDto;
import com.wiki.model.Comment;
import com.wiki.service.CommentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
public class CommentControllerTest {
  private MockMvc mockMvc;

  @Mock
  private CommentService commentService;
  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
    CommentController commentController = new CommentController(commentService);
    mockMvc = MockMvcBuilders.standaloneSetup(commentController).build();
  }

  @Test
  public void testFindAllComment() throws Exception {
    List<Comment> commentList = new ArrayList<>();
    commentList.add(new Comment());
    commentList.add(new Comment());
    when(commentService.findAllComment()).thenReturn(commentList);

    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/Comment")
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();

    String responseContent = result.getResponse().getContentAsString();
    Assertions.assertNotNull(responseContent);
  }

  @Test
  public void testSaveComment() throws Exception {
    // Arrange
    CommentDto commentDto = new CommentDto();
    commentDto.setAuthor("Cats");
    commentDto.setText("Test");
    Comment savedComment = new Comment();
    savedComment.setAuthor(commentDto.getAuthor());
    savedComment.setText(commentDto.getText());
    when(commentService.saveComment(any(CommentDto.class))).thenReturn(savedComment);

    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/Comment/saveComment")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"author\":\"Cats\",\"text\":\"Test\"}"))
            .andExpect(status().isOk())
            .andReturn();
    String responseContent = result.getResponse().getContentAsString();
    Assertions.assertNotNull(responseContent);
  }

  @Test
  public void testFindById() throws Exception {
    Long commentId = 1L;
    Comment comment = new Comment();
    comment.setId(commentId);
    when(commentService.findComment(commentId)).thenReturn(comment);

    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/Comment/findById?id=1")
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();

    String responseContent = result.getResponse().getContentAsString();
    Assertions.assertNotNull(responseContent);
  }

  @Test
  public void testUpdateComment() throws Exception {
    CommentDto commentDto = new CommentDto();
    commentDto.setAuthor("Cats");
    commentDto.setText("Test");
    Comment updatedComment = new Comment();
    updatedComment.setAuthor(commentDto.getAuthor());
    updatedComment.setText(commentDto.getText());
    when(commentService.updateComment(any(CommentDto.class))).thenReturn(updatedComment);

    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/Comment/updateComment")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"author\":\"Cats\",\"text\":\"Updated test\"}"))
            .andExpect(status().isOk())
            .andReturn();

    String responseContent = result.getResponse().getContentAsString();
    Assertions.assertNotNull(responseContent);
  }
}