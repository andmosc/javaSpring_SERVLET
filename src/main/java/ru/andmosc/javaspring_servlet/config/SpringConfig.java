package ru.andmosc.javaspring_servlet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.andmosc.javaspring_servlet.controller.PostController;
import ru.andmosc.javaspring_servlet.repository.PostRepository;
import ru.andmosc.javaspring_servlet.service.PostService;

@Configuration
public class SpringConfig {
    @Bean
    public PostController postController(PostService service) {
        return new PostController(service);
    }
    @Bean
    public PostService postService(PostRepository repository) {
        return new PostService(repository);
    }
    @Bean
    public PostRepository postRepository() {
        return new PostRepository();
    }
}
