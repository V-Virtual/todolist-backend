package oocl.example.todolist.service;

import oocl.example.todolist.exception.TodoIllegalCreateException;
import oocl.example.todolist.exception.TodoIllegalUpdateException;
import oocl.example.todolist.exception.TodoNotFoundException;
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
        Todo todo = todoRepository.findById(id);
        if(todo == null){
            throw new TodoNotFoundException("Todo is not found");
        }
        return todoRepository.findById(id);
    }

    public List<Todo> getAll() {
        return todoRepository.findAll();
    }

    public Todo updateTodo(long id, Todo todoUpdate) {
        if(todoUpdate.getText() == null || todoUpdate.getText().isEmpty()){
            throw new TodoIllegalUpdateException("Todo Update should not be empty");
        }
        Todo todo = getTodo(id);
        todoRepository.updateTodo(todo, todoUpdate);
        return todo;
    }
}
