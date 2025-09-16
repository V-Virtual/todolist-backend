package controller;

import model.entity.TodoList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.TodoListService;

@RestController
@RequestMapping("/todos")
public class TodoListController {

    @Autowired
    private TodoListService todoListService;

    @PostMapping
    public ResponseEntity<TodoList> createTodoList(@RequestBody )
}
