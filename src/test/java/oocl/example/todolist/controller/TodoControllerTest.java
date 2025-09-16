package oocl.example.todolist.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import oocl.example.todolist.repository.TodoRepository;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TodoRepository todoRepository;

    @BeforeEach
    void setUp() {
        todoRepository.setUp();
    }

    private long createTodo(String requestBody) throws Exception {
        ResultActions resultActions = mockMvc.perform((post("/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)));
        MvcResult mvcResult = resultActions.andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        return new ObjectMapper().readTree(contentAsString).get("id").asLong();
    }

    @Test
    void should_return_todolist_when_get_all_todos() throws Exception {
        String requestBody1 = """
                {
                    "text": "Learn Chinese"
                }
                """;
        long id1 = createTodo(requestBody1);
        String requestBody2 = """
                {
                    "text": "Learn Maths"
                }
                """;
        long id2 = createTodo(requestBody2);
        mockMvc.perform(get("/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(id1))
                .andExpect(jsonPath("$[0].text").value("Learn Chinese"))
                .andExpect(jsonPath("$[0].done").value(false))
                .andExpect(jsonPath("$[1].id").value(id2))
                .andExpect(jsonPath("$[1].text").value("Learn Maths"))
                .andExpect(jsonPath("$[1].done").value(false));
    }

    @Test
    void should_return_todolist_when_get_todo_by_id() throws Exception {
        String requestBody1 = """
                {
                    "text": "Learn Chinese"
                }
                """;
        long id1 = createTodo(requestBody1);
        String requestBody2 = """
                {
                    "id": 5,
                    "text": "Learn Maths",
                    "done": true
                }
                """;
        long id2 = createTodo(requestBody2);
        mockMvc.perform(get("/todos/{id}", id2))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id2))
                .andExpect(jsonPath("$.text").value("Learn Maths"))
                .andExpect(jsonPath("$.done").value(false));
    }

    @Test
    void should_return_not_found_when_get_todo_by_id_given_todo_not_exist() throws Exception {
        mockMvc.perform(get("/todos/{id}", 1))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Todo is not found"));
    }

    @Test
    void should_return_todolist_when_create_todos() throws Exception {
        String requestBody = """
                {
                    "text": "Learn Spring Boot",
                    "done": false
                }
                """;
        mockMvc.perform(post("/todos")
                        .contentType("application/json")
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.text").value("Learn Spring Boot"))
                .andExpect(jsonPath("$.done").value(false));
    }

    @Test
    void should_return_unprocessable_entity_when_create_todo_given_text_is_empty() throws Exception {
        String requestBody = """
                {
                    "text": ""
                }
                """;
        mockMvc.perform(post("/todos")
                        .contentType("application/json")
                        .content(requestBody))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string("Todo text should not be empty"));
    }

    @Test
    void should_return_todolist_when_update_todo() throws Exception {
        String requestBody1 = """
                {
                    "text": "Learn Chinese"
                }
                """;
        long id1 = createTodo(requestBody1);
        String requestBody2 = """
                {
                    "text": "Learn Maths",
                    "done": true
                }
                """;
        mockMvc.perform(put("/todos/{id}", id1)
                        .contentType("application/json")
                        .content(requestBody2))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id1))
                .andExpect(jsonPath("$.text").value("Learn Maths"))
                .andExpect(jsonPath("$.done").value(true));
    }

    @Test
    void should_return_todolist_when_update_todo_given_todo_id() throws Exception {
        String requestBody1 = """
                {
                    "text": "Learn Chinese"
                }
                """;
        long id1 = createTodo(requestBody1);
        String requestBody2 = """
                {
                    "id": 5,
                    "text": "Learn Maths",
                    "done": true
                }
                """;
        mockMvc.perform(put("/todos/{id}", id1)
                        .contentType("application/json")
                        .content(requestBody2))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id1))
                .andExpect(jsonPath("$.text").value("Learn Maths"))
                .andExpect(jsonPath("$.done").value(true));
    }

    @Test
    void should_return_not_found_when_update_todo_given_todo_not_exist() throws Exception {
        String requestBody = """
                {
                    "text": "Learn Maths",
                    "done": true
                }
                """;
        mockMvc.perform(put("/todos/{id}", 999)
                        .contentType("application/json")
                        .content(requestBody))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Todo is not found"));
    }

    @Test
    void should_return_unprocessable_entity_when_update_todo_given_text_is_empty() throws Exception {
        String requestBody1 = """
                {
                    "text": "Learn Chinese"
                }
                """;
        long id1 = createTodo(requestBody1);
        String requestBody2 = """
                {
                    "text": ""
                }
                """;
        mockMvc.perform(put("/todos/{id}", id1)
                        .contentType("application/json")
                        .content(requestBody2))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string("Todo Update should not be empty"));
    }
}