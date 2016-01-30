package belog.admin;

import belog.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Beldon
 */
@Controller("adminUploadController")
@RequestMapping("/admin/upload")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @RequestMapping(value = "/uploadImg.json",method= RequestMethod.POST)
    public void uploadImg(@RequestParam(value = "upload") MultipartFile file,@RequestParam(required = false) String type ,HttpServletRequest request,HttpServletResponse response) {
        String CKEditorFuncNum = request.getParameter("CKEditorFuncNum");
        String imagePath;
        String res;
        if (StringUtils.isEmpty(type)) {
            imagePath = uploadService.saveImage(file);
            res = "<script type=\"text/javascript\">window.parent.CKEDITOR.tools.callFunction("+CKEditorFuncNum+", '"+imagePath+"');</script>";
        }else {
            res = uploadService.saveThumbs(file);
        }

        try {
            PrintWriter out = response.getWriter();
            out.print(res);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
