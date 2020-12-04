package com.atzhangkang.springcloud.service;


import com.atzhangkang.springcloud.utils.DeleteFilesUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * 测试定时删除任务
 * @author tule
 * @version 1.0
 * @date 2020/12/3
 */
@Slf4j
@Component
@ConditionalOnProperty(prefix = "timed.task", name = "open",havingValue = "true")
public class DeleteRegularlyTest {
    /**
     * @ConditionalOnProperty可以对@Component和@Configuration注解进行开关操作，
     * 当havingValue的值与配置一样时，@Component和@Configuration注解生效。否则不生效
     * 只删除目标路径下的文件，不删除目标目录下的文件夹
     * 秒 分 时 日 月 年， *表示每分钟，
     * 0 0 0 0 * ? *
     */
    @Scheduled(cron = "0 * 14 4 12 ?")
    public void deleteRegularly() {
        var dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm", Locale.CHINA);
        log.info("======定时清理文件任务开始于：{}", dateTimeFormatter.format(ZonedDateTime.now()));
        String filePath = "E:\\tmpTest\\tmp";
        // 删除filePath路径下，前10分钟以前的文件
        int delCount = DeleteFilesUtils.moveFileToReady(filePath, 0);
        if (delCount > 0) {
            log.info("======本次从：{}下清理{}份文件", filePath, delCount);
        } else {
            log.info("======暂时没有要清理的文件");
        }
        log.info("======定时清理文件任务结束于：{}", dateTimeFormatter.format(ZonedDateTime.now()));
    }
}

