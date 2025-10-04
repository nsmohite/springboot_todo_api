package com.example.todo.api.web;

import com.example.todo.api.model.Todo;
import com.example.todo.api.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
    private final TodoService service;

    public TodoController(TodoService service){
        this.service = service;
    }

    //GET /api/todos

    @GetMapping
    public List<Todo> list(){
        return service.findAll();
    }

    //GET /api/todos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Todo> getById(@PathVariable Long id){
        return service.findById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //POST /api/todos
    @PostMapping
    public ResponseEntity<Todo> create(@RequestBody Todo todo){
        Todo created = service.create(todo);
        URI location =
                ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(location).body(created);
    }

    //DELETE /api/todos/{id}(optional, convenient)

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        boolean removed = service.delete(id);
        return removed ? ResponseEntity.noContent().build():
                ResponseEntity.notFound().build();
    }
}

