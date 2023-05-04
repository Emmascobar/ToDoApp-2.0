package com.ohmycode.test.controllers;

import com.ohmycode.test.models.entities.ToDo;
import com.ohmycode.test.models.entities.User;
import com.ohmycode.test.repository.ToDoRepository;
import com.ohmycode.test.repository.UserRepository;
import com.ohmycode.test.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ToDoRepository toDoRepository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("title", "Welcome to 'TO DO APP'");
        return "index";
    }

    @GetMapping("/index")
    public String home(Model model) {
        model.addAttribute("title", "Welcome to 'TO DO APP'");
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/login?error")
    public String loginError(Model model, @Param("username") String username, @Param("password") String password) {
        model.addAttribute("username", username);
        model.addAttribute("password", password);
        return "login";
    }

    @GetMapping("/login?logout")
    public String logout(Model model) {
        model.addAttribute("title", "Thanks for use our TO DO APP");
        return "logout";
    }

    @GetMapping("/tasks")
    public String getAllPages(Model model) {
        model.addAttribute("title", "All Tasks");
        return findPaginated(1, "id", "asc", model);
    }

    @GetMapping("/tasks/findKey")
    public String findToDosByKey(Model model, @Param("keyword") String keyword) {
        model.addAttribute("title", "Tasks result");
        model.addAttribute("keyword", keyword);
        model.addAttribute("tasks", userService.getToDoList(keyword));
        return "tasks";
    }

    @GetMapping("/tasks/findUser")
    public String findToDosByUser(Model model, @Param("username") String username) {
        model.addAttribute("title", "Tasks result");
        model.addAttribute("username", username);
        model.addAttribute("tasks", userService.getToDoListByUser(username));
        return "tasks";
    }


    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 10;

        Page<ToDo> page = userService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<ToDo> listToDos = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("tasks", listToDos);
        return "tasks";
    }

    @GetMapping("/tasks/{id}")
    public String getToDoById(@PathVariable Long id, Model model, RedirectAttributes flash) {
        Optional<ToDo> optionalToDo = toDoRepository.findById(id);
        if (optionalToDo == null) {
            flash.addFlashAttribute("error", "Task not exist in Database. Check the Id");
            return "redirect:/tasks";
        }

        model.addAttribute("title", "To Do List of " + userService.getToDoById(id).get().getUser());
        model.addAttribute("task", userService.getToDoById(id));
        return "tasks_id";
    }

    @GetMapping("/add-task")
    public String addForm(Model model) {
        ToDo todo = new ToDo();
        model.addAttribute("title", "Add a New Task To Do");
        model.addAttribute("todo", todo);
        model.addAttribute("users", userRepository.findAll());
        return "add_task";
    }

    @PostMapping("/save-task")
    public String addNewToDo(@Valid @ModelAttribute("todo") ToDo todo, Model model, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            model.addAttribute("todo", todo);
            return "redirect:/add-task";
        }
        userService.addNewToDo(todo);
        attributes.addFlashAttribute("success", "Task Successfully Registered / Updated In Database");
        return "redirect:/tasks";
    }

    @GetMapping("/update-form/{id}")
    public ModelAndView updateForm(@PathVariable Long id, RedirectAttributes attributes) {
        ModelAndView model = new ModelAndView("Update_task");
        model.addObject("title", "Update Task");
        model.addObject("todo", toDoRepository.findById(id));
        model.addObject("users", userRepository.findAll());
        return model;
    }

    @GetMapping("/delete-task/{id}")
    public String deleteTask(@PathVariable Long id, RedirectAttributes attributes) {
        userService.deleteToDo(id);
        attributes.addFlashAttribute("warning", "Task Removed From Database");
        return "redirect:/tasks";
    }

    @GetMapping("/user-form")
    public String newUserForm(Model model) {
        User user = new User();
        model.addAttribute("title", "Add a New User");
        model.addAttribute("user", user);
        return "add_user";
    }

    @PostMapping("/save-user")
    public String createUser(@Valid @ModelAttribute("user") User user, Model model, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "redirect:/user-form";
        }
        userService.addNewUser(user);
        attributes.addFlashAttribute("success", "User successfully registered in data base");
        return "redirect:/index";

    }
}