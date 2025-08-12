package spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.entity.Task;
import spring.service.TaskService;

import java.util.List;

import static java.util.Objects.isNull;

@Controller
@RequestMapping("/")
public class TaskController {
    private final TaskService taskService;
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    @GetMapping("/")
    public String loadTasks(
            Model model,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "limit", required = false, defaultValue = "10") int limit){
        List<Task> allTasks = taskService.getAllTasks((page - 1) * limit, limit);
        model.addAttribute("tasks", allTasks);
        return "tasks";
    }
    @PostMapping("/{id}")
    public String editTasks(Model model, @PathVariable Integer id, @PathVariable TaskInfo taskInfo){
        if(isNull(id) || id <= 0){
            throw new IllegalArgumentException("Task with id " + id + " not found");
        }
        Task editedTask = taskService.edit(id, taskInfo.getDescription(), taskInfo.getStatus());
        return loadTasks(model, 1, 10);
    }
    @PostMapping("/")
    public String createTasks(Model model, @RequestBody TaskInfo taskInfo){
        Task createdTask = taskService.create(taskInfo.getDescription(), taskInfo.getStatus());
        return loadTasks(model, 1, 10);
    }
    @DeleteMapping("/{id}")
    public String deleteTasks(Model model, @PathVariable Integer id){
        if(isNull(id) || id <= 0){
            throw new IllegalArgumentException("Task with id " + id + " not found");
        }
        taskService.delete(id);
        return loadTasks(model, 1, 10);
    }
}
