package oocl.example.todolist.repository.dao;

import oocl.example.todolist.model.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoJpaRepository extends JpaRepository<Todo, Long> {

}
