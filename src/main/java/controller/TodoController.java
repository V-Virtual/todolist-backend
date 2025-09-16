package controller;

import model.entity.Todo;
import model.vo.TodoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.TodoListService;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoListService todoListService;

    @PostMapping
    public ResponseEntity<Todo> createTodoList(@RequestBody TodoRequest todoRequest){
        Todo todo = new Todo(todoRequest);
        Todo createdTodo = todoListService.createTodoList(todo);
        return ResponseEntity.status(201).body(createdTodo);

    }
}
