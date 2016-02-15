package belog.admin;


import belog.context.AppContext;
import belog.pojo.Msg;
import belog.pojo.vo.ThemeVo;
import belog.service.ThemeService;
import belog.utils.MsgUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * 主题管理
 * Created by Beldon
 */
@Controller("adminThemeController")
@RequestMapping("/admin/theme")
public class ThemeController extends AdminBaseController {

    @Autowired
    private ThemeService themeService;

    @Autowired
    private AppContext appContext;

    /**
     * 主题列表
     *
     * @param model
     * @return
     */
    @RequestMapping("/list")
    public String list(Model model) {
        List<ThemeVo> themes = themeService.getThemes();
        String currentTheme = themeService.getThemeName();
        model.addAttribute("currentTheme", currentTheme);
        model.addAttribute("themes", themes);
        return getTemplatePath("theme/list");
    }

    /**
     * 主题详情
     *
     * @param theme
     * @param model
     * @return
     */
    @RequestMapping("/details")
    public String detail(@RequestParam("theme") String theme, Model model) {
        ThemeVo themeVo = themeService.getThemeByDir(theme);
        String currentTheme = themeService.getThemeName();
        model.addAttribute("currentTheme", currentTheme);
        model.addAttribute("theme", themeVo);
        return getTemplatePath("theme/details");
    }

    /**
     * 编辑主题，默认是当前主题
     *
     * @param theme
     * @param model
     * @return
     */
    @RequestMapping("/edit")
    public String edit(@RequestParam(value = "theme", required = false) String theme, Model model) {
        if (theme == null) {
            theme = themeService.getThemeName();
        }
        model.addAttribute("theme", theme);
        return getTemplatePath("theme/edit");
    }

    @RequestMapping("/show")
    public String show(Model model) {
        String theme = themeService.getThemeName();
        model.addAttribute("theme", theme);
        return getTemplatePath("theme/show");
    }

    /**
     * 修改当前主题
     *
     * @param theme
     * @return
     */
    @RequestMapping("/addOrUpdate.json")
    @ResponseBody
    public Msg addOrUpdate(@RequestParam("theme") String theme) {
        themeService.setTheme(theme);
        return MsgUtils.success();
    }


    /**
     * 文件列表
     *
     * @param dir
     * @param response
     */
    @RequestMapping(value = "/fileTree.html", method = RequestMethod.POST)
    public void fileTree(@RequestParam(value = "dir", required = true) String dir, HttpServletResponse response) {
        String templateDir = appContext.getThemeRoot() + dir;

        try {
            dir = java.net.URLDecoder.decode(dir, "UTF-8");
            PrintWriter out = response.getWriter();

            File dirFile = new File(templateDir);

            if (dirFile.exists()) {
                String[] files = dirFile.list(new FilenameFilter() {
                    public boolean accept(File dir, String name) {
                        return name.charAt(0) != '.'
                                && !name.endsWith(".xml")
                                && !name.endsWith(".png")
                                && !name.endsWith(".ico")
                                && !name.endsWith(".jpg") ;
                    }
                });
                Arrays.sort(files, String.CASE_INSENSITIVE_ORDER);
                out.print("<ul class=\"jqueryFileTree\" style=\"display: none;\">");
                // All dirs
                for (String file : files) {
                    if (new File(templateDir, file).isDirectory()) {
                        out.print("<li class=\"directory collapsed\"><a href=\"#\" rel=\"" + dir + file + "/\">"
                                + file + "</a></li>");
                    }
                }
                // All files
                for (String file : files) {
                    if (!new File(templateDir, file).isDirectory()) {
                        int dotIndex = file.lastIndexOf('.');
                        String ext = dotIndex > 0 ? file.substring(dotIndex + 1) : "";
                        out.print("<li class=\"file ext_" + ext + "\"><a href=\"#\" rel=\"" + dir + file + "\">"
                                + file + "</a></li>");
                    }
                }
                out.print("</ul>");
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 列出文件内容
     *
     * @param path     文件路径
     * @param response
     */
    @RequestMapping(value = "/fileContent.html", method = RequestMethod.GET)
    public void fileContent(@RequestParam(value = "path", required = true) String path, HttpServletResponse response) {
        String templateDir = appContext.getThemeRoot() + path;
        try {
            PrintWriter out = response.getWriter();
            File targetFile = new File(templateDir);
            if (targetFile.exists()) {
                out.print(FileUtils.readFileToString(new File(templateDir), "UTF-8"));
            } else {
                out.print("未找到目标文件");
            }
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存文件
     *
     * @param fileName
     * @param content
     * @return
     */
    @RequestMapping(value = "/saveFile.json", method = RequestMethod.POST)
    @ResponseBody
    public Msg saveFile(@RequestParam(value = "fileName") String fileName, @RequestParam(value = "content") String content) {
        Msg msg = new Msg();
        String templateDir = appContext.getThemeRoot() + fileName;
        File targetFile = new File(templateDir);
        if (targetFile.exists()) {
            try {
                FileUtils.write(targetFile, content,"UTF-8");
                msg.setErrCode(0);
                msg.setErrMsg("success");
                msg.setStatus(true);
            } catch (IOException e) {
                e.printStackTrace();
                msg.setStatus(false);
                msg.setErrCode(-1);
                msg.setErrMsg(e.getMessage());
            }
        }else{
            msg.setStatus(false);
            msg.setErrCode(-1);
            msg.setErrMsg("未找到相应文件");
        }
//        FileUtils.
        return msg;
    }
}
