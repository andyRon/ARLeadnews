package top.andyron.schedule.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.andyron.model.schedule.dtos.Task;
import top.andyron.schedule.ScheduleApplication;
import top.andyron.schedule.service.TaskService;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author andyron
 **/
@SpringBootTest(classes = ScheduleApplication.class)
@RunWith(SpringRunner.class)
public class TaskServiceImplTest {
    @Autowired
    private TaskService taskService;

    @Test
    public void addTask() {
        Task task = new Task();
        task.setTaskType(100);
        task.setPriority(50);
        task.setParameters("task test".getBytes()); // TODO
        task.setExecuteTime(new Date().getTime());
//        task.setExecuteTime(new Date().getTime() + 500);
//        task.setExecuteTime(new Date().getTime() + 50000);

        long taskId = taskService.addTask(task);
        System.out.println(taskId);
    }

    @Test
    public void cancelTask() {
        taskService.cancelTask(1740957399259226113L);
    }

    @Test
    public void poll() {
        Task task = taskService.poll(100, 50);
        System.out.println(task);
    }

    @Test
    public void addTask2() {
        for (int i = 0; i < 5; i++) {
            Task task = new Task();
            task.setTaskType(100 + i);
            task.setPriority(50);
            task.setParameters("task test".getBytes());
            task.setExecuteTime(new Date().getTime() + 500 * i);

            long taskId = taskService.addTask(task);
            System.out.println(taskId);
        }
    }
}
