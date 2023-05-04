package com.ohmycode.test.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ohmycode.test.models.entities.ToDo;
import com.ohmycode.test.models.entities.User;
import com.ohmycode.test.models.utils.Address;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import javax.validation.constraints.AssertTrue;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ToDoRepositoryTest {

    @Autowired
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

//    @Autowired
//    UserRepository userRepository;
//    @Autowired
//    ToDoRepository toDoRepository;
//
//    private User user;
//    private ToDo todo;
//    private Address address;
//
//    @BeforeEach
//    void setUp() {
//
//    }
//
//    @AfterEach
//    void tearDown() {
////        userRepository.deleteAll();
////        toDoRepository.deleteAll();
//    }
//    @Test
//    void test_save_ToDo(){
//        //when - acción o el comportamiento que vamos a probar
//        ToDo addTodo = toDoRepository.save(todo);
//
//        //then - verificar la salida
//        assertEquals(1, toDoRepository.findAll());
//    }
//    @Test
//    void findAll_Tasks_All() {
//        List<ToDo> toDoList = toDoRepository.findAll();
//        assertFalse(toDoList.isEmpty());
//        assertEquals(1, toDoList);
//    }
//
//    @Test
//    void find_ById_Expected(){
//        Optional<ToDo> optionalToDo = toDoRepository.findById(todo.getId());
//        assertTrue(optionalToDo.isPresent());
//        assertEquals(todo.getTitle(), optionalToDo.get().getTitle());
//    }
}