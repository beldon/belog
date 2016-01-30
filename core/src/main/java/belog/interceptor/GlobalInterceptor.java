/*
 *	Copyright © 2013 Changsha Shishuo Network Technology Co., Ltd. All rights reserved.
 *	长沙市师说网络科技有限公司 版权所有
 *	http://www.shishuo.com
 */

package belog.interceptor;

import belog.service.ConfigService;
import belog.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


/**
 * @author Beldon
 */
@Component
public class GlobalInterceptor implements HandlerInterceptor {

    @Autowired
    private ThemeService themeService;

    @Autowired
    private ConfigService configService;


    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        if (null == modelAndView) {
            return;
        }
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
        //网站basePath
        modelAndView.addObject("BASE_PATH", basePath);

        String themePath = basePath + "/static/theme/" + themeService.getThemeName();
        //主题模板themePath
        modelAndView.addObject("THEME_PATH", themePath);
        Map<String,String> configs = configService.getConfigs();
        modelAndView.addAllObjects(configs);
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // TODO Auto-generated method stub
    }

}
