package com.hwwz.medicalhistorysupervisor.service.impl;

import com.hwwz.medicalhistorysupervisor.configuration.GlobalMed;
import com.hwwz.medicalhistorysupervisor.service.DataUrlConvertService;
import com.hwwz.medicalhistorysupervisor.utils.ResJsonTemplate;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Date;

/**
 * @author: HuShili
 * @date: 2017/12/30
 * @description: none
 */
@Service
public class DataUrlConvertServiceImpl implements DataUrlConvertService{

    @Override
    public String saveDataUrlToFile(String dataUrl){
        BASE64Decoder decoder = new BASE64Decoder();

        String fileName = new String();
        String filePath;
        String fileType = dataUrl.substring(dataUrl.indexOf('/') + 1, dataUrl.indexOf(';'));
        dataUrl = dataUrl.substring(dataUrl.indexOf(',') + 1);

        try {
            //Base64解码
            byte[] b = decoder.decodeBuffer(dataUrl);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {//调整异常数据
                    b[i] += 256;
                }
            }

            fileName = "recognize_cache." + fileType;
            filePath = GlobalMed.getPhoto_path() + fileName;

            File dest = new File(filePath);
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }

            if (dest.exists()) {
                dest.delete();
            }

            OutputStream out = new FileOutputStream(filePath);
            out.write(b);
            out.flush();
            out.close();

            //BufferedImage bufferedImage;

            //try {

            //read image file
            //bufferedImage = ImageIO.read(new File(ffilePath));
            //fileName = filePath + "recognize_cache.jpg";

            // create a blank, RGB, same width and height, and a white background
            //BufferedImage newBufferedImage = new BufferedImage(bufferedImage.getWidth(),
            //        bufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
            //TYPE_INT_RGB:创建一个RBG图像，24位深度，成功将32位图转化成24位
            //newBufferedImage.createGraphics().drawImage(bufferedImage, 0, 0, Color.WHITE, null);

            // write to jpeg file
            //ImageIO.write(newBufferedImage, "jpg", new File(filePath));

            //System.out.println("Done");

            //} catch (IOException e) {

            //e.printStackTrace();

            //}
        }catch (IOException e) {
            return null;
        }

        return fileName;
    }

    @Override
    public String convertFileToDataUrl (String fileName){
        String dataUrl = new String();
        String filePath = GlobalMed.getPhoto_path() + fileName;
        File file = new File(filePath);

        try {
            //Base64编码
            FileInputStream inputFile = new FileInputStream(file);
            byte[] buffer = new byte[(int) file.length()];
            inputFile.read(buffer);
            inputFile.close();

            dataUrl = "data:image/png;base64," + new BASE64Encoder().encode(buffer);

        }catch (IOException e){
            return null;
        }

        return dataUrl;
    }
}
