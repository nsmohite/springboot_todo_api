package com.example.todo.api.service;

import com.example.todo.api.model.Todo;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TodoService {
    //Use a synchronized list
    private final List<Todo> todos = Collections.synchronizedList(new ArrayList());
    private final AtomicLong idCounter = new AtomicLong(1L);

    public List<Todo> findAll(){
        //return copy to avoid exposing internal list
        return new ArrayList<Todo>(todos);
    }

    public Optional<Todo> findById(Long id){
        synchronized (todos){
            return todos.stream().filter(t-> Objects.equals(t.getId(), id)).findFirst();
        }
    }

    public Todo create(Todo todo){
        Long id = idCounter.getAndIncrement();
        Todo created = new Todo(id, todo.getTitle(),todo.isCompleted());
        todos.add(created);
        return created;
    }

    public boolean delete(Long id){
        return todos.removeIf(t ->Objects.equals(t.getId(), id));
    }
}
