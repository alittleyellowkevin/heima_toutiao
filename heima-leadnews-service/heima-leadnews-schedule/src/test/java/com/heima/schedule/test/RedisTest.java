package com.heima.schedule.test;


import com.heima.common.redis.CacheService;
import com.heima.schedule.ScheduleApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;


@SpringBootTest(classes = ScheduleApplication.class)
@RunWith(SpringRunner.class)
public class RedisTest {

    @Autowired
    private CacheService cacheService;

    @Test
    public void testList(){

        //在list的左边添加元素
//        cacheService.lLeftPush("list_001","hello,redis");

        //在list的右package com.heima.schedule.service.impl;
        //
        //import com.alibaba.fastjson.JSON;
        //import com.heima.common.constants.ScheduleConstants;
        //import com.heima.common.redis.CacheService;
        //import com.heima.model.schedule.dtos.Task;
        //import com.heima.model.schedule.pojos.Taskinfo;
        //import com.heima.model.schedule.pojos.TaskinfoLogs;
        //import com.heima.schedule.mapper.TaskinfoLogsMapper;
        //import com.heima.schedule.mapper.TaskinfoMapper;
        //import com.heima.schedule.service.TaskService;
        //import lombok.extern.slf4j.Slf4j;
        //import org.springframework.beans.BeanUtils;
        //import org.springframework.beans.factory.annotation.Autowired;
        //import org.springframework.stereotype.Service;
        //import org.springframework.transaction.annotation.Transactional;
        //
        //import java.util.Calendar;
        //import java.util.Date;
        //
        //@Service
        //@Transactional
        //@Slf4j
        //public class TaskServiceImpl implements TaskService {
        //    /**
        //     * 添加延迟任务
        //     *
        //     * @param task
        //     * @return
        //     */
        //    @Override
        //    public long addTask(Task task) {
        //        //1.添加任务到数据库中
        //
        //        boolean success = addTaskToDb(task);
        //
        //        if (success) {
        //            //2.添加任务到redis
        //            addTaskToCache(task);
        //        }
        //
        //
        //        return task.getTaskId();
        //    }
        //
        //    @Autowired
        //    private CacheService cacheService;
        //
        //    /**
        //     * 把任务添加到redis中
        //     *
        //     * @param task
        //     */
        //    private void addTaskToCache(Task task) {
        //
        //        String key = task.getTaskType() + "_" + task.getPriority();
        //
        //        //获取5分钟之后的时间  毫秒值
        //        Calendar calendar = Calendar.getInstance();
        //        calendar.add(Calendar.MINUTE, 5);
        //        long nextScheduleTime = calendar.getTimeInMillis();
        //
        //        //2.1 如果任务的执行时间小于等于当前时间，存入list
        //        if (task.getExecuteTime() <= System.currentTimeMillis()) {
        //            cacheService.lLeftPush(ScheduleConstants.TOPIC + key, JSON.toJSONString(task));
        //        } else if (task.getExecuteTime() <= nextScheduleTime) {
        //            //2.2 如果任务的执行时间大于当前时间 && 小于等于预设时间（未来5分钟） 存入zset中
        //            cacheService.zAdd(ScheduleConstants.FUTURE + key, JSON.toJSONString(task), task.getExecuteTime());
        //        }
        //
        //
        //    }
        //
        //    @Autowired
        //    private TaskinfoMapper taskinfoMapper;
        //
        //    @Autowired
        //    private TaskinfoLogsMapper taskinfoLogsMapper;
        //
        //    /**
        //     * 添加任务到数据库中
        //     *
        //     * @param task
        //     * @return
        //     */
        //    private boolean addTaskToDb(Task task) {
        //
        //        boolean flag = false;
        //
        //        try {
        //            //保存任务表
        //            Taskinfo taskinfo = new Taskinfo();
        //            BeanUtils.copyProperties(task, taskinfo);
        //            taskinfo.setExecuteTime(new Date(task.getExecuteTime()));
        //            taskinfoMapper.insert(taskinfo);
        //
        //            //设置taskID
        //            task.setTaskId(taskinfo.getTaskId());
        //
        //            //保存任务日志数据
        //            TaskinfoLogs taskinfoLogs = new TaskinfoLogs();
        //            BeanUtils.copyProperties(taskinfo, taskinfoLogs);
        //            taskinfoLogs.setVersion(1);
        //            taskinfoLogs.setStatus(ScheduleConstants.SCHEDULED);
        //            taskinfoLogsMapper.insert(taskinfoLogs);
        //
        //            flag = true;
        //        } catch (Exception e) {
        //            e.printStackTrace();
        //        }
        //
        //        return flag;
        //    }
        //}边获取元素，并删除
//        String list_001 = cacheService.lRightPop("list_001");
//        System.out.println(list_001);
    }

    @Test
    public void testZset(){
        //添加数据到zset中  分值
        cacheService.zAdd("zset_key_001","hello zset 001",1000);
        cacheService.zAdd("zset_key_001","hello zset 002",8888);
        cacheService.zAdd("zset_key_001","hello zset 003",7777);
        cacheService.zAdd("zset_key_001","hello zset 004",999999);

        //按照分值获取数据
        Set<String> zset_key_001 = cacheService.zRangeByScore("zset_key_001", 0, 8888);
        System.out.println(zset_key_001);


    }
}