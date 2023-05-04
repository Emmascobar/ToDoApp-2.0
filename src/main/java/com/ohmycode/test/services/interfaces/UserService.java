package com.ohmycode.test.services.interfaces;

import com.ohmycode.test.models.entities.ToDo;
import com.ohmycode.test.models.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    public List<ToDo> getToDoList(String keyWord);
    public List<ToDo> getToDoListByUser(String username);

    public Optional<ToDo> getToDoById(Long id);

    public ToDo addNewToDo(ToDo todo);

    public void updateToDo(Long id);

    public void deleteToDo(Long id);

    Page<ToDo> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
    User addNewUser(User user);


}
