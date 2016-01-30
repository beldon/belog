package belog.admin;


import belog.context.AppContext;
import belog.pojo.Msg;
import belog.pojo.PluginConfig;
import belog.pojo.PluginContent;
import belog.pojo.vo.ConfigVo;
import belog.pojo.vo.PluginVo;
import belog.service.PluginService;
import belog.utils.MsgUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Beldon
 */
@Controller("adminPluginController")
@RequestMapping("/admin/plugin")
public class PluginController extends AdminBaseController {

    @Autowired
    private AppContext appContext;

    @Autowired
    private PluginService pluginService;

    /**
     * 插件管理，插件列表
     *
     * @return
     */
    @RequestMapping("/list")
    public String list(Model model) {
        List<PluginVo> list = pluginService.list();
        model.addAttribute("list", list);
        return getTemplatePath("plugin/list");
    }

    /**
     * 插件开启/关闭
     *
     * @param pluginId 插件ID
     * @return
     */
    @RequestMapping("/able.json")
    @ResponseBody
    public Msg active(@RequestParam("pluginId") String pluginId) {
//        int i  = 0;
//        while (i < 10000) {
//            i++;
//            System.out.println("----::"+i);
//            pluginService.enable("me.beldon.test.plugin");
//        }
        if (!StringUtils.isEmpty(pluginId)) {
            pluginService.enable(pluginId);
        }
        return MsgUtils.success();
    }


    /**
     * 插件配置页面
     *
     * @param pluginId 插件ID
     * @param model
     * @return
     */
    @RequestMapping({"/plugin"})
    public String plugin(@RequestParam(value = "pluginId",required = true) String pluginId, Model model) {
        if (org.springframework.util.StringUtils.hasText(pluginId)) {
            PluginContent pluginContent = pluginService.getPluginById(pluginId.trim());

            PluginConfig config = pluginContent.getConfig();
            if (config != null) {
                List<PluginConfig.Element> elements = config.getElements();
                if (elements != null && elements.size() > 0) {
                    for (PluginConfig.Element element : elements) {
                        element.setValue(pluginService.getValue(element.getKey()));
                    }
                }
            }

            model.addAttribute("plugin", pluginContent);
        }
        return getTemplatePath("plugin/plugin");
    }

    /**
     * 插件配置
     *
     * @param request
     * @return
     */
    @RequestMapping("/ajaxEdit.json")
    @ResponseBody
    public Msg ajaxEdit(HttpServletRequest request) {
        Map<String, String[]> map = request.getParameterMap();
        String pluginId = request.getParameter("pluginId");
        List<ConfigVo> configVoList = new ArrayList<ConfigVo>();
        Iterator<Map.Entry<String, String[]>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String[]> con = it.next();
            if (!"pluginId".equals(con.getKey().trim())) {
                ConfigVo configVo = new ConfigVo();
                configVo.setName(con.getKey());
                configVo.setValue(con.getValue()[0]);
                configVo.setAutoLoad("no");
                configVo.setType("plugins_" + pluginId);
                configVoList.add(configVo);
            }
        }
        pluginService.saveOrUpdate(configVoList);
        return MsgUtils.success();
    }
}
