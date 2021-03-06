package com.hwwz.medicalhistorysupervisor.utils;

import com.hwwz.medicalhistorysupervisor.configuration.GlobalMed;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class fileReception {
    public static String receiveSingle(MultipartFile file,String path)//transorted file,absolute location
    {
        if (file!=null&&!file.isEmpty())
        {
            // 获取文件名
            String fileName = file.getOriginalFilename();
            // 获取文件的后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            // 文件上传后的路径
            //new file name:
            fileName= UUID.randomUUID()+suffixName;
            // fileName = UUID.randomUUID() + suffixName;
            File dest = new File(path + fileName);
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {
                file.transferTo(dest);
            }
            catch (Exception e) {
                e.printStackTrace();
                return "";
            }
            return fileName;
        }
        return "";
    }

    public static List<String> receiveMultiple(MultipartFile[] files,String path)
    {
        List<String> nameList=new ArrayList<>();
        if(files!=null)
        {
            String fileName;
            int i=0;
            for(MultipartFile file:files)
            {
                fileName= fileReception.receiveSingle(file,path);
                ++i;
                if(fileName.equals(""))
                {
                    throw new NullPointerException("add Photo Set failed at "+i+"th photo");
                }
                nameList.add(fileName);
            }
        }
        return nameList;
    }
}
