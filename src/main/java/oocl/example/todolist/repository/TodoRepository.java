package oocl.example.todolist.repository;

import oocl.example.todolist.model.entity.Todo;

import java.util.List;

public interface TodoRepository {

    void setUp();

    Todo save(Todo todoCreate);

    Todo findById(long id);

    List<Todo> findAll();

    void updateTodo(Todo todo, Todo todoUpdate);
}
