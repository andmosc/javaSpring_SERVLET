package ru.andmosc.javaspring_servlet.service;

import org.springframework.stereotype.Component;
import ru.andmosc.javaspring_servlet.controller.PostController;
import ru.andmosc.javaspring_servlet.exception.NotFoundException;
import ru.andmosc.javaspring_servlet.model.Post;
import ru.andmosc.javaspring_servlet.repository.PostRepository;

import java.util.List;
@Component
public class PostService {
    private final PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }
    public List<Post> all() {
        return repository.all();
    }
    public Post getById(long id) throws NotFoundException {
        return repository.getById(id).orElseThrow(() -> new NotFoundException(PostController.ID_NOT_FOUND));
    }
    public Post save(Post post) {
        return repository.save(post);
    }
    public void removeById(long id) {
        repository.removeById(id);
    }
}

