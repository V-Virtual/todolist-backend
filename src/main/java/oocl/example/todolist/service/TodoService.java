package oocl.example.todolist.service;

import oocl.example.todolist.exception.TodoIllegalCreateException;
import oocl.example.todolist.model.entity.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import oocl.example.todolist.repository.TodoRepository;

import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public Todo createTodo(Todo todoCreate) {
        if(todoCreate.getText() == null || todoCreate.getText().isEmpty()){
            throw new TodoIllegalCreateException("Todo text should not be empty");
        }
        todoCreate.setDone(false);
        return todoRepository.save(todoCreate);
    }

    public Todo getTodo(Long id) {
        return todoRepository.findById(id);
    }

    public List<Todo> getAll() {
        return todoRepository.findAll();
    }
}
