package ru.andmosc.javaspring_servlet.controller;

import com.google.gson.Gson;
import ru.andmosc.javaspring_servlet.model.Post;
import ru.andmosc.javaspring_servlet.service.PostService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class PostController {
    public static final String APPLICATION_JSON = "application/json";
    public static final String ID_NOT_FOUND = "Id not found or list is empty";
    private final PostService service;
    private final Gson gson = new Gson();

    public PostController(PostService service) {
        this.service = service;
    }
    public void all(HttpServletResponse response) throws IOException {
        response.setContentType(APPLICATION_JSON);
        final List<Post> data = service.all();

        response.getWriter().println(gson.toJson(data));
    }
    public void getById(long id, HttpServletResponse response) throws IOException {
        response.setContentType(APPLICATION_JSON);
        Post post = service.getById(id);
        response.getWriter().println(gson.toJson(post));
    }
    public void save(Reader body, HttpServletResponse response) throws IOException {
        response.setContentType(APPLICATION_JSON);
        final Post post = gson.fromJson(body, Post.class);
        final Post data = service.save(post);
        response.getWriter().print(gson.toJson(data));
    }
    public void removeById(long id, HttpServletResponse response) {
        service.removeById(id);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
