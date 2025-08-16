package spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.entity.Task;
import spring.service.TaskService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        int totalPages = (int) Math.ceil(1.0 * taskService.getAllCount() / limit);
    if (totalPages > 1) {
      List<Integer> pageNumbers =
          IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
      model.addAttribute("page_numbers", pageNumbers);
      model.addAttribute("current_page", page);
        }
        return "tasks";
    }
    // MENTOR We usually use PUT as the HTTP method for updates
    @PostMapping("/{id}")
    public String editTasks(Model model, @PathVariable Integer id, @RequestBody TaskInfo taskInfo){
        if(isNull(id) || id <= 0){
            throw new IllegalArgumentException("Task with id " + id + " not found");
        }
        Task editedTask = taskService.edit(id, taskInfo.getDescription(), taskInfo.getStatus());
        // MENTOR
        /*
          One thing to take into account here: because we are reusing another controller method,
          the transactional context of the loadTask operation will be different from the one for the update above.
          In this case, it is not an issue because we want to read the state (tasks) again once the update has
          been commited. The code is functionally correct in this case.
         */
        return loadTasks(model, 1, 10);
    }
    @PostMapping("/")
    public String createTasks(Model model, @RequestBody TaskInfo taskInfo){
        if (taskInfo == null || taskInfo.getDescription() == null || taskInfo.getStatus() == null) {
            throw new IllegalArgumentException("Task description and status cannot be null");
        }
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
