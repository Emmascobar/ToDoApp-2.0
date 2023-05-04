package com.ohmycode.test.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ohmycode.test.models.entities.ToDo;
import com.ohmycode.test.models.entities.User;
import com.ohmycode.test.repository.ToDoRepository;
import com.ohmycode.test.repository.UserRepository;
import com.ohmycode.test.services.interfaces.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


//@SpringBootTest
@AutoConfigureMockMvc
@WebMvcTest(controllers = UserController.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ToDoRepository toDoRepository;
    @Autowired
    UserService userService;
    @Autowired
    UserController userController;

    @BeforeEach
    void setUp() {
        User user = new User("Bart Simpson", "Bart2023", "Barto10", null, null);
        User user02 = new User("Lisa Simpson", "Lisa2023", "Lisa11", null, null);
        userRepository.save(user);
        userRepository.save(user02);
        ToDo todo = new ToDo("Rent a car for vacations", user);
        ToDo todo02 = new ToDo("Buy Fruits for breakfast", user02);
        toDoRepository.save(todo);
        toDoRepository.save(todo02);
    }


    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllPages() throws Exception {
//        User user = new User("Lisa Simpson", "Lisa2023", "Lisa11", null, null);
//        ToDo todo = new ToDo("Rent a car for vacations", user);
//
//        when(userService.getToDoList(null))
//                .thenReturn(List.of(todo));
//
//        mockMvc.perform(get("/tasks"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.view().name("tasks"))
//                .andExpect(MockMvcResultMatchers.model().attributeExists("title"))
//                .andExpect(MockMvcResultMatchers.model().attribute("title", "All tasks"));
//
    }

    @Test
    void findToDosByUser() {
    }

    @Test
    void shouldCreateNewTask() throws Exception {
//        this.mockMvc
//                .perform(post("/add-task")
//                        .param("title", "call mom")
//                        .param("user", "bart")
//                        .andExpect(status().is3xxRedirection())
//                        .andExpect(header().string("Location", "/customers"));
//    }

    }

    @Test
    void addNewToDo() {
    }

    @Test
    void deleteTask() {
    }

    @Test
    void createUser() {
    }
}