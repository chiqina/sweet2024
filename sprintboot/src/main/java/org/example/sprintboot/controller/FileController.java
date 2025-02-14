package org.example.sprintboot.controller;

import cn.hutool.core.io.FileUtil;
import org.example.sprintboot.common.AuthAccess;
import org.example.sprintboot.common.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/file")
public class FileController {
    @Value("${ip:localhost}")
    String ip;
    @Value("${server.port}")
    String port;
    private final static  String ROOT_PATH = System.getProperty("user.dir")+File.separator+"files";
    @PostMapping("/upload")
    //RequestParam("file") 指明了接收MultipartFile类型的参数file
    public Result upload(@RequestParam("file") MultipartFile file) {
        //获取文件名称
        String originalFilename = file.getOriginalFilename();
        //获取.前面的名称
        String mainName = FileUtil.mainName(originalFilename);
        //获取后缀名称
        String extName = FileUtil.extName(originalFilename);
        //获取当前路径
        String projectPath = System.getProperty("user.dir");
        //完整的文件路径
        String fileUrl = ROOT_PATH + File.separator+originalFilename;
        //判断文件父目录是否存在
        if(FileUtil.exist(ROOT_PATH)){
            FileUtil.mkdir(ROOT_PATH);
        }

        if(FileUtil.exist(fileUrl)){
            originalFilename = System.currentTimeMillis() +"_"+ mainName+"."+extName;
            fileUrl = ROOT_PATH + File.separator+originalFilename;
        }
        File saveFile = new File(fileUrl);
        String url;
        try {
            file.transferTo(saveFile);
            //生成文件存放的绝对路径
            url = "http://"+ip+":"+port+"/file/download/"+originalFilename;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Result.success(url);
    }

    @GetMapping("/download/{fileName}")
    @AuthAccess
    //PathVariable("fileName") 指明了接收String类型的参数fileName
    public void download(@PathVariable String fileName, HttpServletResponse response) throws IOException{
        //拼接一个完整的url路径
        String  url = ROOT_PATH + File.separator + fileName;
        //判断文件是否存在
        if(!FileUtil.exist(url)){
            return;
        }
        //如果文件存在则读取文件
        byte[] bytes = FileUtil.readBytes(url);
        //生成一个读取文件流
        ServletOutputStream  outputStream = response.getOutputStream();
        //将文件写入流离
        outputStream.write(bytes);
        //刷新流
        outputStream.flush();
        //关闭流
        outputStream.close();
    }
}
