package cn.pzhu.springbootsecurity.util;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 文件上传下载类
 */
@Component
public class FileIO {
    public Map fileUpload(String filepath,MultipartFile multipartFile){
        Map<String,Object> rerultMap = new HashMap();
        String path =filepath;
        boolean flag = false;
        File file = new File(path);
        //判断目录是否存在
        if(!file.exists()){
            file.mkdir();
        }
        //获取文件名
        String name = multipartFile.getOriginalFilename();
        //获取文件后缀
        String suffixName = name.substring(name.lastIndexOf("."));
        String prefixName = name.substring(0,name.lastIndexOf("."));
        String uudi = UUID.randomUUID().toString().replace("-", "");
        name = prefixName+"_"+ uudi+suffixName;
        path = path+ name;
        try {
            multipartFile.transferTo(new File(path));
            flag = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        rerultMap.put("flag",flag);
        rerultMap.put("name",name);
        return rerultMap;
    }
}
