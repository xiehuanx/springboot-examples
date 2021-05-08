package xyz.xiehuan.quartz.service.impl;


import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.xiehuan.quartz.entity.TaskEntity;
import xyz.xiehuan.quartz.exception.QuartzException;
import xyz.xiehuan.quartz.service.ITaskService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * @author Lensen
 * @desc
 * @since 2018/8/8 13:44
 */
@Service
public class TaskServiceImpl implements ITaskService {



    @Autowired
    private Scheduler scheduler;


    /**
     * @param info
     * @return
     * @// TODO: 2018/6/8 保存定时任务
     */
    @SuppressWarnings("unchecked")
    @Override
    public Boolean addTask(TaskEntity info ) {
                //取出定时任务名称，分组名称，cron,定时任务描述
        String jobName = info.getJobName(),
                jobGroup = info.getJobGroup(),
                cronExpression = info.getCronExpression(),
                jobDescription = info.getJobDescription();
        try {
            //检查定时任务是否存在（通过任务名称，分组名称检测）
            if (checkExists(jobName, jobGroup)) {
                throw new QuartzException(String.format("Job已经存在, jobName:{%s},jobGroup:{%s}", jobName, jobGroup));
            }

            //通过name+group获取唯一的triggerKey
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
            //通过name+group获取唯一的jobKey
            JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
            //表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression).withMisfireHandlingInstructionDoNothing();
            //按新的cronExpression表达式重新构建trigger
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).withDescription(jobDescription).withSchedule(scheduleBuilder).build();

            JobDataMap jobDataMap = new JobDataMap();
            jobDataMap.put("1","1");

            //根据传入的类路径得到类
            Class<? extends Job> clazz = (Class<? extends Job>) Class.forName(info.getJobClass());
            //重新根据传入的类名构建jobdetail
            JobDetail jobDetail = JobBuilder.newJob(clazz)
                    .withIdentity(jobKey)
                    .usingJobData(jobDataMap)
                    .withDescription(jobDescription)
                    .build();
            //按新的trigger重新设置job执行
            scheduler.scheduleJob(jobDetail, trigger);
            return true;
        } catch (SchedulerException | ClassNotFoundException e) {
            throw new QuartzException("类名不存在或执行表达式错误");
        }
    }

    /**
     * @param taskEntity
     * @return
     * @// TODO: 2018/6/8 开始定时任务
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean resumeTask(TaskEntity taskEntity) {
        try {
            scheduler.resumeJob(JobKey.jobKey(taskEntity.getJobName(), taskEntity.getJobGroup()));
            return true;
        } catch (Exception e) {
            throw new QuartzException("FAILED");
        }
    }

    /**
     * @return
     * @// TODO: 2018/6/5  查询job
     */
    @Override
    @Transactional(readOnly = true)
    public List<TaskEntity> findTaskList(TaskEntity taskEntity) {
        return null;
    }

    /**
     * 修改定时任务
     *
     * @param info
     */
    @Override
    public Boolean updateTask(TaskEntity info) {

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time= sdf.format( new Date());
        String jobName = info.getJobName(),
                jobGroup = info.getJobGroup(),
                cronExpression = info.getCronExpression(),
                jobDescription = info.getJobDescription(),
                createTime = time;
        //DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
        try {
            if (!checkExists(jobName, jobGroup)) {
                throw new QuartzException(String.format("Job不存在, jobName:{%s},jobGroup:{%s}", jobName, jobGroup));
            }
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
            JobKey jobKey = new JobKey(jobName, jobGroup);
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression).withMisfireHandlingInstructionDoNothing();
            CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).withDescription(createTime).withSchedule(cronScheduleBuilder).build();

            JobDetail jobDetail = scheduler.getJobDetail(jobKey);
            jobDetail.getJobBuilder().withDescription(jobDescription);
            HashSet<Trigger> triggerSet = new HashSet<>();
            triggerSet.add(cronTrigger);

            scheduler.scheduleJob(jobDetail, triggerSet, true);
            return true;
        } catch (SchedulerException e) {
            throw new QuartzException("类名不存在或执行表达式错误");
        }
    }

    /**
     * @param taskEntity
     * @// TODO: 2018/6/1 停止任务
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean pauseTask(TaskEntity taskEntity) {
        TriggerKey triggerKey = TriggerKey.triggerKey(taskEntity.getJobName(), taskEntity.getJobGroup());
        try {
            if (checkExists(taskEntity.getJobName(), taskEntity.getJobGroup())) {
                scheduler.pauseTrigger(triggerKey); //停止触发器
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new QuartzException(e.getMessage());
        }
    }

    /**
     * @param taskEntity
     * @return
     * @// TODO: 2018/6/7 删除任务
     */
    @Override
    public Boolean deleteTask(TaskEntity taskEntity) {
        TriggerKey triggerKey = TriggerKey.triggerKey(taskEntity.getJobName(), taskEntity.getJobGroup());
        try {
            if (checkExists(taskEntity.getJobName(), taskEntity.getJobGroup())) {
                scheduler.pauseTrigger(triggerKey); //停止触发器
                scheduler.unscheduleJob(triggerKey); //移除触发器
                return true;
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
            throw new QuartzException(e.getMessage());
        }
        return false;
    }

    /**
     * 验证是否存在
     *
     * @param jobName
     * @param jobGroup
     * @throws SchedulerException
     */
    private boolean checkExists(String jobName, String jobGroup) throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
        return scheduler.checkExists(triggerKey);
    }
}
