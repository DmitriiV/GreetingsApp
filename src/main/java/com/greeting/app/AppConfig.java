package com.greeting.app;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class AppConfig {
    @Value("${task.executor.core.pool.size}")
    private int corePoolSize;
    @Value("${task.executor.max.pool.size}")
    private int maxPoolSize;
    @Value("${task.executor.queue.capacity}")
    private int queueCapacity;
    @Value("${task.executor.allowed.thread.timeout}")
    private Boolean allowedTreadTimeout;
    @Value("${task.executor.keep.alive.seconds}")
    private int keepAliveSeconds;

    @Bean(name = "taskExecutor")
    public ThreadPoolTaskExecutor taskExecutor () {
        var t = new ThreadPoolTaskExecutor();
        t.setCorePoolSize(corePoolSize);
        t.setMaxPoolSize(maxPoolSize);
        t.setQueueCapacity(queueCapacity);
        t.setAllowCoreThreadTimeOut(allowedTreadTimeout);
        t.setKeepAliveSeconds(keepAliveSeconds);
        return t;
    }

}
