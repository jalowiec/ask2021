package pl.edu.agh.ask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.edu.agh.ask.domain.Task;
import pl.edu.agh.ask.service.DbService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
public class TaskController {

    @Autowired
    private DbService service;

    @GetMapping("/")
    public String index(Model model) {
        return "external";
    }

    @GetMapping("/tasks")
    public String getTasks(Principal principal, Model model) {
        String userId = principal.getName();
        List<Task> tasks = service.getTasksByUserId(userId);
        if (tasks.isEmpty()) {
            Task task = new Task(1L, "testTitle", "testContent", userId);
            tasks.add(task);
        }
        model.addAttribute("tasks", tasks);
        model.addAttribute("username", userId);
        return "tasks";
    }

    @GetMapping(path = "/logout")
    public String logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return "external";
    }
}
