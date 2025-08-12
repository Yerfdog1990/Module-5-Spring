package springmvcdemo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import springmvcdemo.controller.StudentController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class StudentControllerTest {
  private MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(new StudentController()).build();
  }

  @Test
  void testGetStudentByIdFound() throws Exception {
    mockMvc
        .perform(get("/students/1"))
        .andExpect(status().isOk())
        .andExpect(content().string("Student found"));
  }

  @Test
  void testGetStudentByIdNotFound() throws Exception {
    mockMvc
        .perform(get("/students/2"))
        .andExpect(status().isNotFound())
        .andExpect(content().string("Student Not Found"));
  }

  @Test
  void testCreateStudent() throws Exception {
    mockMvc
        .perform(post("/students").contentType(MediaType.APPLICATION_JSON).content("\"John\" \"Doe\""))
        .andExpect(status().isCreated())
        .andExpect(content().string("Student Created: John Doe"));
  }
}
