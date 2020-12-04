package com.atzhangkang.springcloud.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author tule
 * @version 1.0
 * @date 2020/12/3
 */
@Slf4j
@Service
public class SpringTestService {

    public void doSomeThing() {
        log.info("Just do it!");
    }
}
