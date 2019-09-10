package com.hone.comp.server.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hone.common.json.CResult;

/**
 * TestController
 *
 * @Author hourz
 * @since 2019-07-19
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Value("${title}")
    private String title;

    @RequestMapping("/list")
    public CResult<String> list(){
        return new CResult<String>(true, "0", title);
    }


}
