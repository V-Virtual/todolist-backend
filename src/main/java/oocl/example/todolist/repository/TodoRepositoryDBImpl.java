package oocl.example.todolist.repository;

import oocl.example.todolist.model.entity.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import oocl.example.todolist.repository.dao.TodoJpaRepository;

import java.util.List;

@Repository
public class TodoRepositoryDBImpl implements TodoRepository {

    @Autowired
    private TodoJpaRepository todoJpaRepository;

    @Override
    public void setUp() {
        todoJpaRepository.deleteAll();
    }

    @Override
    public Todo save(Todo todoCreate) {
        return todoJpaRepository.save(todoCreate);
    }

    @Override
    public Todo findById(long id) {
        return todoJpaRepository.findById(id).orElse(null);
    }

    @Override
    public List<Todo> findAll() {
        return todoJpaRepository.findAll();
    }

    @Override
    public void updateTodo(Todo todo, Todo todoUpdate) {
        todo.setText(todoUpdate.getText());
        todo.setDone(todoUpdate.isDone());
        todoJpaRepository.save(todo);
    }
}
