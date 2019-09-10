package com.hone.elk.server.controller;

import com.github.pagehelper.PageInfo;
import com.hone.common.json.CResult;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>业务处理日志</p>
 * @Author hourz
 * @since 2019-06-29
 */
@RestController
@RequestMapping(value = "/business",produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "业务处理日志",description = "业务处理日志")
public class BusinessController {

    public CResult<PageInfo> list(){
        return null;
    }

    public CResult<PageInfo> save(){
        return null;
    }

}
