package com.hone.zuul.server.config;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static com.netflix.zuul.constants.ZuulHeaders.X_FORWARDED_FOR;

/**
 * ZuulFilter
 *
 * @Author hourz
 * @since 2019-07-12
 */
@Component
public class ZuulConfig extends ZuulFilter {

    private static Logger logger = LoggerFactory.getLogger(ZuulConfig.class);

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String remoteAddr = request.getRemoteAddr();
        logger.info(" ---> " + remoteAddr);
        String realIp = request.getHeader("x-real-ip");
        logger.info(" 获取请求的真实 IP 地址 ---> " + realIp);
        String forwardedFor = request.getHeader("x-forwarded-for");
        String[] ips = forwardedFor.split(",");
        logger.info("");
        // 判断是否是正向代理
        if (realIp.equals(ips[2])) {
            ctx.getZuulRequestHeaders().put("x-real-ip", ips[0]);
        } else {
            ctx.addZuulRequestHeader("real-ip",request.getHeader("x-real-ip"));
            ctx.getZuulRequestHeaders().put(X_FORWARDED_FOR, realIp);
        }
        ctx.addZuulRequestHeader("Authorization","Basic bWFzdGVyOm1vZGVsX21hc3Rlcg==");
        return null;
    }

    /**
     *
     * @param curl
     * @return
     */
    public boolean checkAuth(String curl){

        return true;
    }

}
