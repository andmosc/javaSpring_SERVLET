package ru.andmosc.javaspring_servlet.service;

import ru.andmosc.javaspring_servlet.controller.PostController;
import ru.andmosc.javaspring_servlet.exception.NotFoundException;
import ru.andmosc.javaspring_servlet.model.Post;
import ru.andmosc.javaspring_servlet.repository.PostRepository;

import java.util.List;

public class PostService {
    private final PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }
    public List<Post> all() {
        return repository.all();
    }
    public Post getById(long id) {
        return repository.getById(id).orElseThrow(() -> new NotFoundException(PostController.ID_NOT_FOUND));
    }
    public Post save(Post post) {
        return repository.save(post);
    }
    public void removeById(long id) {
        repository.removeById(id);
    }
}

