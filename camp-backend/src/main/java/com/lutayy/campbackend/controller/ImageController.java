package com.lutayy.campbackend.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;

@Controller
public class ImageController {
    @Value("${IMG_PATH}")
    private String IMG_PATH;

    @RequestMapping(value = "/serveImage/**", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] getPhoto(HttpServletRequest request) {
        String imgUrl=request.getRequestURI();
        File file = new File(IMG_PATH+imgUrl);
        byte[] bytes = null;
        try {
            FileInputStream inputStream = new FileInputStream(file);
            bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, inputStream.available());
        }catch (Exception e){
            System.out.println("--------获取图片文件过程出错--------");
        }
        return bytes;
    }
}
