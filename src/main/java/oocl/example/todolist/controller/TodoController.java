package oocl.example.todolist.controller;

import oocl.example.todolist.model.entity.Todo;
import oocl.example.todolist.model.request.TodoCreateRequest;
import oocl.example.todolist.model.request.TodoUpdateRequest;
import oocl.example.todolist.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @PostMapping
    public ResponseEntity<Todo> createTodoList(@RequestBody TodoCreateRequest todoCreateRequest) {
        Todo todoCreate = new Todo(todoCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(todoService.createTodo(todoCreate));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodo(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(todoService.getTodo(id));
    }

    @GetMapping
    public ResponseEntity<List<Todo>> getTodoList() {
        return ResponseEntity.status(HttpStatus.OK).body(todoService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @RequestBody TodoUpdateRequest todoUpdateRequest) {
        Todo todoUpdate = new Todo(todoUpdateRequest);
        return ResponseEntity.status(HttpStatus.OK).body(todoService.updateTodo(id, todoUpdate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
