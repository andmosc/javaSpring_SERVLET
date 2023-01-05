package ru.andmosc.javaspring_servlet.servlet;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.andmosc.javaspring_servlet.controller.PostController;
import ru.andmosc.javaspring_servlet.exception.NotFoundException;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainServlet extends HttpServlet {
    private PostController controller;
    public static final String GET = "GET";
    public static final String POST = "POST";
    public static final String DELETE = "DELETE";
    final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("ru.andmosc.javaspring_servlet");
   @Override
    public void init() {
        controller = context.getBean("postController",PostController.class);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        try {
            final String path = req.getRequestURI();
            final String method = req.getMethod();
            final boolean matches = path.matches("/api/posts/\\d+");
            final boolean equalsPath = path.equals("/api/posts");

            if (GET.equals(method) && equalsPath) {
                controller.all(resp);
                return;
            }

            if (GET.equals(method) && matches) {
                final long id = getId(path);
                controller.getById(id, resp);
                return;
            }
            if (POST.equals(method) && equalsPath) {
                controller.save(req.getReader(), resp);
                return;
            }
            if (DELETE.equals(method) && matches) {
                final long id = getId(path);
                controller.removeById(id, resp);
                return;
            }
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } catch (NotFoundException e) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
    private static long getId(String path) {
        return Long.parseLong(path.substring(path.lastIndexOf("/") + 1));
    }
}

