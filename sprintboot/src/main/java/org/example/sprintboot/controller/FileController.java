package org.example.sprintboot.controller;

import cn.hutool.core.io.FileUtil;
import org.example.sprintboot.common.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileController {
    @PostMapping("/upload/")
    //RequestParam("file") 指明了接收MultipartFile类型的参数file
    public Result upload(@RequestParam("file") MultipartFile file) {
        //获取文件名称
        String originalFilename = file.getOriginalFilename();
        //获取.前面的名称
        String mainName = FileUtil.mainName(originalFilename);
        //获取后缀名称
        String extName = FileUtil.extName("文件后缀");

        return Result.success("");
    }
}
