package top.andyron.schedule.feign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.andyron.apis.schedule.IScheduleClient;
import top.andyron.model.common.dtos.ResponseResult;
import top.andyron.model.schedule.dtos.Task;
import top.andyron.schedule.service.TaskService;

@RestController
public class ScheduleClient implements IScheduleClient {

    @Autowired
    private TaskService taskService;
    /**
     * 添加延迟任务
     *
     * @param task
     * @return
     */
    @PostMapping("/api/v1/task/add")
    @Override
    public ResponseResult addTask(@RequestBody Task task) {
        return ResponseResult.okResult(taskService.addTask(task));
    }

    /**
     * 取消任务
     *
     * @param taskId
     * @return
     */
    @GetMapping("/api/v1/task/{taskId}")
    @Override
    public ResponseResult cancelTask(@PathVariable("taskId") long taskId){
        return ResponseResult.okResult(taskService.cancelTask(taskId));
    }

    /**
     * 按照类型和优先级拉取任务
     *
     * @param type
     * @param priority
     * @return
     */
    @GetMapping("/api/v1/task/{type}/{priority}")
    @Override
    public ResponseResult poll(@PathVariable("type") int type,@PathVariable("priority") int priority) {
        return ResponseResult.okResult(taskService.poll(type,priority));
    }
}
