package belog.admin;


import belog.pojo.Msg;
import belog.pojo.vo.ConfigVo;
import belog.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Beldon
 */
@Controller("adminConfigController")
@RequestMapping("/admin/config")
public class ConfigController extends AdminBaseController {

    @Autowired
    private ConfigService configService;

    @RequestMapping("/global")
    public String global(Model model) {
        List<ConfigVo> list = configService.findAll();
        for (ConfigVo configVo : list) {
            model.addAttribute(configVo.getName(), configVo.getValue());
        }
        return getTemplatePath("config/global");
    }

    @RequestMapping("/ajaxEdit.json")
    @ResponseBody
    public Msg ajaxEdit(HttpServletRequest request) {
        Map<String, String[]> map = request.getParameterMap();
        Msg msg = new Msg();
        List<ConfigVo> configVoList = new ArrayList<ConfigVo>();
        Iterator<Map.Entry<String, String[]>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            ConfigVo configVo = new ConfigVo();
            Map.Entry<String, String[]> con = it.next();
            configVo.setName(con.getKey());
            configVo.setValue(con.getValue()[0]);
            configVoList.add(configVo);
        }
        configService.saveOrUpdate(configVoList);
        msg.setErrCode(0);
        msg.setErrMsg("success");
        msg.setStatus(true);
        return msg;
    }
}
