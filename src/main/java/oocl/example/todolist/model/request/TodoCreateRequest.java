package oocl.example.todolist.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TodoCreateRequest {
    private String text;
}
