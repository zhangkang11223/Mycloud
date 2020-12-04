package com.atzhangkang.springcloud.log;

import java.sql.SQLException;

/**
 * @author tule
 * @version 1.0
 * @date 2020/12/4
 */
public interface LogService {

    /**
     * 增加日志
     */
    public boolean addLog(String log) throws SQLException;
}
