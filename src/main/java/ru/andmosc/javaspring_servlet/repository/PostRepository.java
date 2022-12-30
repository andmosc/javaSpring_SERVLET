package ru.andmosc.javaspring_servlet.repository;

import ru.andmosc.javaspring_servlet.controller.PostController;
import ru.andmosc.javaspring_servlet.exception.NotFoundException;
import ru.andmosc.javaspring_servlet.model.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class PostRepository {

    private final ConcurrentHashMap<Long, Post> data = new ConcurrentHashMap<>();
    private final AtomicLong counter = new AtomicLong();

    public List<Post> all() {
        return new ArrayList<>(data.values());
    }
    public Optional<Post> getById(long id) {
            return Optional.ofNullable(data.get(id));
    }
    public Post save(Post post) {
        if (post.getId() == 0) {
            post.setId(counter.addAndGet(1));
            data.putIfAbsent(post.getId(), post);
            return post;
        } else {
            if (post.getId() > 0) {
                getById(post.getId()).ifPresent(e -> e.setContent(post.getContent()));
                return data.get(post.getId()) != null &&
                        data.get(post.getId()).getContent().equals(post.getContent()) ? post : null;
            }
        }
        return null;
    }
    public void removeById(long id) {
        if (id < 0 || id > counter.get() || !data.containsKey(id)) {
            throw new NotFoundException(PostController.ID_NOT_FOUND);
        }
            data.remove(id);
    }
}
