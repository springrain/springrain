package org.springrain.frame.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池管理
 *
 * @author springrain
 * @date 2011-2-12
 */
public class ThreadPoolManager {

    private final static Executor defaultExecutor=createThreadPool("springrain->");;
    /*
     * 将构造方法访问修饰符设为私有，禁止任意实例化。
     */
    private ThreadPoolManager() {
        throw new IllegalAccessError("工具类不能实例化");
    }

    public static Executor createThreadPool(String threadNamePrefix){

        //executor = new SimpleAsyncTaskExecutor();
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(100); // 默认线程数量
        threadPoolTaskExecutor.setQueueCapacity(5000); // 当线程大于corePoolSize个的时候,将线程放入queueCapacity大小的队列(队列只存在任务,不存在线)
        threadPoolTaskExecutor.setMaxPoolSize(5000); // 最大线程数,当queueCapacity队列已满,将会继续创建线程,直到线程数超过maxPoolSize的大小,将抛出异常
        threadPoolTaskExecutor.setKeepAliveSeconds(60); // 允许线程空闲时间(单位:默认为秒)

        if (StringUtils.isNotBlank(threadNamePrefix)){
            threadPoolTaskExecutor.setThreadNamePrefix(threadNamePrefix);
        }
        // 线程池对拒绝任务的处理策略
        // CallerRunsPolicy:由调用线程(提交任务的线程)处理该任务
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 初始化
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }

    /*
     * 向线程池中添加任务方法
     */
    public static void addThread(Runnable task) {
        if (task != null) {
            defaultExecutor.execute(task);
        }
    }

}