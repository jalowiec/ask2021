package pl.edu.agh.ask.controller;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.edu.agh.ask.domain.Task;
import pl.edu.agh.ask.service.DbService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class TaskController {

    @Autowired
    private DbService service;

    @GetMapping("/")
    public String index() {
        return "external";
    }

    @GetMapping("/tasks")
    public String getTasks(Principal principal, Model model) {
        String userId = principal.getName();
        List<Task> tasks = service.getTasksByUserId(userId);
        tasks.sort((t1, t2) -> Boolean.compare(t1.getDone(), t2.getDone()));

        model.addAttribute("tasks", tasks);
        model.addAttribute("username", getUserName(principal));
        return "tasks";
    }

    @GetMapping("/tasks/delete/{taskId}")
    public String deleteTask(@PathVariable Long taskId) {
        service.deleteTask(taskId);
        return "redirect:/tasks";
    }

    @GetMapping("/tasks/resolve/{taskId}")
    public String resolveTask(@PathVariable Long taskId) {
        Optional<Task> task = service.getTaskById(taskId);
        task.ifPresentOrElse(Task::markDone,() -> System.out.println("Task with id " + taskId + " not found"));
        task.ifPresent(service::saveTask);
        return "redirect:/tasks";
    }

    @PostMapping("/tasks")
    public String addTask(@RequestParam String title, @RequestParam String content, Principal principal) {
        String userId = principal.getName();
        Task task = new Task(title, content, userId);
        service.saveTask(task);
        return "redirect:/tasks";
    }

    @GetMapping(path = "/logout")
    public String logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return "external";
    }

    private String getUserName(Principal principal) {
        KeycloakSecurityContext session = ((KeycloakPrincipal) principal).getKeycloakSecurityContext();
        AccessToken accessToken = session.getToken();
        return accessToken.getName();
    }
}
