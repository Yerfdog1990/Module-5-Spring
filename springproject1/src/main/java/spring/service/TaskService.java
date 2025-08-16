package spring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.dao.TaskDao;
import spring.entity.Status;
import spring.entity.Task;

import java.util.List;

import static java.util.Objects.isNull;

@Service
public class TaskService {
    private final TaskDao taskDao;
    public TaskService(TaskDao taskDao) {
        this.taskDao = taskDao;
    }
    public List<Task> getAllTasks(int offset, int limit){
        return taskDao.getAllTasks(offset, limit);
    }
    public int getAllCount(){
        return taskDao.getAllCount();
    }
    @Transactional
    public Task edit(int id, String description, Status status){
        Task task = taskDao.getTaskById(id);
        if(isNull(task)){
            throw new IllegalArgumentException("Task with id " + id + " not found");
        }
        task.setDescription(description);
        task.setStatus(status);
        // MENTOR This line is not incorrect, but it is not really necessary
        // The task instance is in persistent state
        // Changes in it will be automatically synced with the database when the transaction is commited
        taskDao.saveOrUpdate(task);
        return task;
    }
    public Task create(String description, Status status){
        Task task = new Task();
        task.setDescription(description);
        task.setStatus(status);
        taskDao.saveOrUpdate(task);
        return task;
    }
    @Transactional
    public void delete(int id){
        Task task = taskDao.getTaskById(id);
        if(isNull(task)){
            throw new IllegalArgumentException("Task with id " + id + " not found");
        }
        taskDao.delete(task);
    }
}
