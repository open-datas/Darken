package com.hone.elk.server.controller;

import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>数据库操作日志</p>
 * @Author hourz
 * @since 2019-07-01
 */
@RestController
@RequestMapping(value = "/dataBase",produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "业务处理日志",description = "业务处理日志")
public class DataBaseController {
}
