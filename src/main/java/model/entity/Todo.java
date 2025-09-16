package model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.vo.TodoRequest;

@Data
@AllArgsConstructor
@Entity
@Table(name = "t_todo")
@NoArgsConstructor
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String text;
    private boolean completed;

    public Todo(TodoRequest todoRequest) {
        this.text = todoRequest.getText();
    }
}
