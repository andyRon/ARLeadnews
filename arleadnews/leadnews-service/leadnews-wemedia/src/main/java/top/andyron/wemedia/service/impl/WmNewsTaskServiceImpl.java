package top.andyron.wemedia.service.impl;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import top.andyron.apis.schedule.IScheduleClient;
import top.andyron.model.common.dtos.ResponseResult;
import top.andyron.model.common.enums.TaskTypeEnum;
import top.andyron.model.schedule.dtos.Task;
import top.andyron.model.wemedia.pojos.WmNews;
import top.andyron.utils.common.ProtostuffUtil;
import top.andyron.wemedia.service.WmNewsAutoScanService;
import top.andyron.wemedia.service.WmNewsTaskService;

import java.util.Date;

/**
 * @author andyron
 **/
@Service
@Slf4j
public class WmNewsTaskServiceImpl implements WmNewsTaskService {

    @Autowired
    private IScheduleClient scheduleClient;

    /**
     * 添加任务到延迟队列中
     *
     * @param id          文章的id
     * @param publishTime 发布的时间 可以作为任务的执行时间
     */
    @Override
    @Async
    public void addNewsToTask(Integer id, Date publishTime) {
        log.info("添加任务到延迟队列中-----begin");

        Task task = new Task();
        task.setExecuteTime(publishTime.getTime());
        task.setTaskType(TaskTypeEnum.NEWS_SCAN_TIME.getTaskType());
        task.setPriority(TaskTypeEnum.NEWS_SCAN_TIME.getPriority());
        WmNews wmNews = new WmNews();
        wmNews.setId(id);
        task.setParameters(ProtostuffUtil.serialize(wmNews));

        scheduleClient.addTask(task);

        log.info("添加任务到延迟队列中-----end");
    }

    @Autowired
    private WmNewsAutoScanService wmNewsAutoScanService;

    /**
     * 消费任务，审核文章
     * 每1秒拉取任务
     */
    @Scheduled(fixedRate = 1000)
    @Override
    public void scanNewsByTask() {
        log.info("消费任务，审核文章");
        ResponseResult res = scheduleClient.poll(TaskTypeEnum.NEWS_SCAN_TIME.getTaskType(), TaskTypeEnum.NEWS_SCAN_TIME.getPriority());
        if (res.getCode().equals(200) && res.getData() != null) {
            Task task = JSON.parseObject(JSON.toJSONString(res.getData()), Task.class);
            WmNews wmNews = ProtostuffUtil.deserialize(task.getParameters(), WmNews.class);
            wmNewsAutoScanService.autoScanWmNews(wmNews.getId());
        }
    }
}
