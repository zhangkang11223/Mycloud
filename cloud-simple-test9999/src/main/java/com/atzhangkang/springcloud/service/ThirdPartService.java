package com.atzhangkang.springcloud.service;

import com.alibaba.fastjson.JSONObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

/**
 * @author tule
 * @version 1.0
 * @date 2021/1/13
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ThirdPartService {

    @Value("${portal.ip}")
    private String portalIp;

    @Value("${portal.port}")
    private String portalPort;

    @Value("${portal.appId}")
    private String appId;

    private final RestTemplate restTemplate;

    private static final String USER_INFO_URL = "%s:%s/uumsapi/api/v2/user?name=%s";

    private static final String BUSINESS_USERS_URL = "%s:%s/uumsapi/api/v2/appuser/%s/role/%s";

    /**
     * 根据token和userName查询第三方GET接口
     * @param portalToken 第三方token
     * @param userName 用户名
     */
    public Integer sendGetRequest(String portalToken, String userName) throws Exception {
        String url = String.format(USER_INFO_URL, portalIp, portalPort, userName);
        String userId = "";
        HttpHeaders headers = new HttpHeaders();
        // 设置token
        headers.set("Authorization", portalToken);
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        if (200 == exchange.getStatusCodeValue()) {
            throw new Exception("Failed to get user list");
        }
        String body = exchange.getBody();
        String content = JSONObject.parseObject(body).getString("content");
        if (!StringUtils.isEmpty(content)) {
            userId = JSONObject.parseObject(content).getString("id");
        }
        return Integer.parseInt(userId);
    }

    /**
     * 根据token, roleId, pageSize查询第三方POST接口
     * @param roleId 角色Id
     * @param portalToken 第三方服务的token
     * @param pageSize 分页大小
     */
    public String sendPostRequest(String roleId, String portalToken, Integer pageSize) throws Exception {
        // format: json格式的请求体body
        String format = String.format("{\"include\": true, \"pg\": {\"a\": true,\"i\": 1,\"n\": %s}}", pageSize);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", portalToken);
        headers.setContentType(MediaType.valueOf("application/json;UTF-8"));
        HttpEntity<String> httpEntity = new HttpEntity<>(format, headers);
        String url = String.format(BUSINESS_USERS_URL, portalIp, portalPort, appId, roleId);
        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
        if (200 == exchange.getStatusCodeValue()) {
            throw new Exception("Failed to get user list");
        }
        return exchange.getBody();
    }
}
