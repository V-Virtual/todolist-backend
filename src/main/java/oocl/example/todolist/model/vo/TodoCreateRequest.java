package oocl.example.todolist.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TodoCreateRequest {
    private String text;
}
