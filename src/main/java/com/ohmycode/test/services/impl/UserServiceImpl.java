package com.ohmycode.test.services.impl;

import com.ohmycode.test.models.entities.Role;
import com.ohmycode.test.models.entities.ToDo;
import com.ohmycode.test.models.entities.User;
import com.ohmycode.test.repository.ToDoRepository;
import com.ohmycode.test.repository.UserRepository;
import com.ohmycode.test.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    ToDoRepository toDoRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public List<ToDo> getToDoList(String keyWord) {
        if (keyWord != null) {
            return toDoRepository.findAll(keyWord);
        }
        return toDoRepository.findAll();
    }

    public List<ToDo> getToDoListByUser(String username) {
        if (username != null) {
            return toDoRepository.findByUsername(username);
        }
        return toDoRepository.findAll();
    }

    public Page<ToDo> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.toDoRepository.findAll(pageable);
    }

    public User addNewUser(User userAdd) {
        User user = new User(userAdd.getName(),
                userAdd.getUsername(),
                passwordEncoder.encode(userAdd.getPassword()), userAdd.getAddress(),
                Arrays.asList(new Role("USER")));
        return userRepository.save(user);
    }

    public Optional<ToDo> getToDoById(Long id) {
        Optional<ToDo> optionalToDo = toDoRepository.findById(id);
        if (optionalToDo == null) {
            throw new RuntimeException("Task not found");
        } else {
            return optionalToDo;
        }
    }

    public ToDo addNewToDo(ToDo toDo) {
        return toDoRepository.save(toDo);
    }

    public void updateToDo(Authentication authentication, @RequestParam Long id) {
        Optional<ToDo> optionalToDo = toDoRepository.findById(id);
        if (!authentication.getName().equals(optionalToDo.get().getUser().getUsername())) {
            throw new RuntimeException("You cannot modificate another user tasks.");
        } else {
            optionalToDo.ifPresent(toDo -> toDo.setComplete(optionalToDo.get().isComplete()));
            toDoRepository.save(optionalToDo.get());
        }
    }
    public void deleteToDo(Authentication authentication, @RequestParam Long id) {
        Optional<ToDo> optionalToDo = toDoRepository.findById(id);
        if (!authentication.getName().equals(optionalToDo.get().getUser().getUsername())) {
            throw new RuntimeException("You cannot delete another user tasks.");
        } else {
            toDoRepository.deleteById(id);
        }
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("Invalid Credentials");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(), mapAuthorities((Collection<Role>) user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapAuthorities(Collection<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}

