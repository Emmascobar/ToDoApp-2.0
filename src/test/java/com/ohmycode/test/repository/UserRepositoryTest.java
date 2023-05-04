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

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {

    @Autowired
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
//
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
//        user = new User("Bart Simpson", "Bart2023", "Barto10");
//        userRepository.save(user);
//        todo = new ToDo("Rent a car for vacations", user);
//        toDoRepository.save(todo);
//    }
//
//    @AfterEach
//    void tearDown() {
////        userRepository.deleteAll();
////        toDoRepository.deleteAll();
//    }
//
//    @Test
//    void findByUsernameEquals() {
//    }
//
//    @Test
//    void findByUsername() {
//    }
}