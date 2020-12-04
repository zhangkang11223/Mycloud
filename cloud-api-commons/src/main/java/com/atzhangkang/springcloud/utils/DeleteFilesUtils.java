package com.atzhangkang.springcloud.utils;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

/**
 * 定时删除任务，默认删除
 * @author tule
 * @version 1.0
 * @date 2020/12/3
 */
public class DeleteFilesUtils {
    public static Integer moveFileToReady(String fromDir,int howMinites ) {
        File srcDir = new File(fromDir);
        if (!srcDir.exists()) {
            return 0;
        }
        File[] files = srcDir.listFiles();
        if (files == null || files.length <= 0) {
            return 0;
        }
        // 删除文件总数
        int delTotal = 0;
        Date today = new Date();
        for (File file : files) {
            if (file.isFile()) {
                try {
                    long time = file.lastModified();
                    Calendar cal = Calendar.getInstance();
                    cal.setTimeInMillis(time);
                    Date lastModified = cal.getTime();
                    double minits = getDistDates(today, lastModified);
                    // 删除多少分钟前文件
                    if (minits >= howMinites) {
                        file.delete();
                        delTotal++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return delTotal;
    }


    public static int getDistDates(Date date1, Date date2) {
        return (int)((date1.getTime() - date2.getTime())/ (60 * 1000));
    }
}
