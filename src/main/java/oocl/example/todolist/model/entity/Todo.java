package oocl.example.todolist.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import oocl.example.todolist.model.request.TodoCreateRequest;
import oocl.example.todolist.model.request.TodoUpdateRequest;

@Entity
@Table(name = "t_todo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String text;
    private boolean done;

    public Todo(TodoCreateRequest todoCreateRequest) {
        this.text = todoCreateRequest.getText();
    }

    public Todo(TodoUpdateRequest todoUpdateRequest) {
        this.text = todoUpdateRequest.getText();
        this.done = todoUpdateRequest.isDone();
    }
}
