package com.hwwz.medicalhistorysupervisor.service.impl;

import com.hwwz.medicalhistorysupervisor.service.FaceRecognizeService;
import com.megvii.cloud.http.CommonOperate;
import com.megvii.cloud.http.FaceOperate;
import com.megvii.cloud.http.FaceSetOperate;
import com.megvii.cloud.http.Response;

import org.json.JSONException;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author: HuShili
 * @date: 2017/12/8
 * @description: none
 */
public class FaceRecognizeServiceImpl implements FaceRecognizeService {
    private final String key = "PfCvIGWrsgybKHUDb_K0h-KJL7Saxphk";
    private final String secret = "YdWwMZZtXoDzVQH3Fh7NTONRPboZhWGf";

    private static ArrayList<String> faceToken = new ArrayList<String>();
    private static String faceSetToken;
    private CommonOperate commonOperate;
    private FaceSetOperate FaceSet;

    public FaceRecognizeServiceImpl(){
        commonOperate = new CommonOperate(key, secret, false);
        FaceSet = new FaceSetOperate(key, secret, false);
    }

    @Override
    public String doRecognize(File file) {
        String result = searchFace(file);

        //need to make a credible threshold here to replace the "result == null" statement
        if(result == null) {
            String token = getFace(file);
            addFace(token);
            return null;
        }
        else
            return result;
    }

    private boolean createFaceSet(){
        try {
            //创建人脸库，并往里加人脸
            //create faceSet and add face
            String faceTokens = createFaceTokens(faceToken);
            Response faceset = FaceSet.createFaceSet(null, "test", null, faceTokens, null, 1);
            String faceSetResult = new String(faceset.getContent());

            faceSetToken = faceSetResult;

            System.out.println("faceSetResult:" + faceSetResult);
            if (faceset.getStatus() == 200) {
                System.out.println("\nfaceSet creat success");
                System.out.println("\ncreate result: ");
                System.out.println(faceSetResult);
                return true;
            } else {
                System.out.println("\nfaceSet creat faile");
                System.out.println("\ncreate result: ");
                System.out.println(faceSetResult);
                return false;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    private String getFace(File file){
        String token = new String();

        try {
            //检测第一个人脸，传的是本地图片文件
            //detect first face by local file
            Response response = commonOperate.detectByte(getBitSet(file), 0, null);
            token = getFaceToken(response);
            faceToken.add(token);
            System.out.println("faceToken: ");
            System.out.println(token);
        }catch (Exception e) {
            e.printStackTrace();
        }

        return token;
    }

    private String searchFace(File file){
        String result = new String();

        try {
            //调用搜索API，得到结果
            //use search API to find face
            Response res = commonOperate.searchByFaceSetToken(null, null, getBitSet(file), faceSetToken, 1);
            result = new String(res.getContent());
            System.out.println("search result: ");
            System.out.println(result);
        }catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    private boolean addFace(String token){
        try {
            Response res = FaceSet.addFaceByFaceToken(token, faceSetToken);
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private String createFaceTokens(ArrayList<String> faceTokens){
        if(faceTokens == null || faceTokens.size() == 0){
            return "";
        }
        StringBuffer face = new StringBuffer();
        for (int i = 0; i < faceTokens.size(); i++){
            if(i == 0){
                face.append(faceTokens.get(i));
            }else{
                face.append(",");
                face.append(faceTokens.get(i));
            }
        }
        return face.toString();
    }

    private byte[] getBitSet(File file) throws IOException{
        BufferedImage bi= ImageIO.read(file);//通过imageio将图像载入
        int h=bi.getHeight();//获取图像的高
        int w=bi.getWidth();//获取图像的宽
        int rgb=bi.getRGB(0, 0);//获取指定坐标的ARGB的像素值
        int[][] gray=new int[w][h];
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                gray[x][y]=getGray(bi.getRGB(x, y));
            }
        }

        BufferedImage nbi=new BufferedImage(w,h,BufferedImage.TYPE_BYTE_BINARY);
        int SW=160;
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                if(getAverageColor(gray, x, y, w, h)>SW){
                    int max=new Color(255,255,255).getRGB();
                    nbi.setRGB(x, y, max);
                }else{
                    int min=new Color(0,0,0).getRGB();
                    nbi.setRGB(x, y, min);
                }
            }
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(nbi, "jpg", out);
        return out.toByteArray();
    }

    private String getFaceToken(Response response) throws JSONException {
        if(response.getStatus() != 200){
            return new String(response.getContent());
        }
        String res = new String(response.getContent());
        System.out.println("response:" + res);
        JSONObject json = new JSONObject(res);
        String faceToken = json.optJSONArray("faces").optJSONObject(0).optString("face_token");
        return faceToken;
    }

    public static int getGray(int rgb){
        String str=Integer.toHexString(rgb);
        int r=Integer.parseInt(str.substring(2,4),16);
        int g=Integer.parseInt(str.substring(4,6),16);
        int b=Integer.parseInt(str.substring(6,8),16);
        //or 直接new个color对象
        Color c=new Color(rgb);
        r=c.getRed();
        g=c.getGreen();
        b=c.getBlue();
        int top=(r+g+b)/3;
        return (int)(top);
    }

    /**
     * 自己加周围8个灰度值再除以9，算出其相对灰度值
     * @param gray
     * @param x
     * @param y
     * @param w
     * @param h
     * @return
     */
    public static int  getAverageColor(int[][] gray, int x, int y, int w, int h)
    {
        int rs = gray[x][y]
                + (x == 0 ? 255 : gray[x - 1][y])
                + (x == 0 || y == 0 ? 255 : gray[x - 1][y - 1])
                + (x == 0 || y == h - 1 ? 255 : gray[x - 1][y + 1])
                + (y == 0 ? 255 : gray[x][y - 1])
                + (y == h - 1 ? 255 : gray[x][y + 1])
                + (x == w - 1 ? 255 : gray[x + 1][ y])
                + (x == w - 1 || y == 0 ? 255 : gray[x + 1][y - 1])
                + (x == w - 1 || y == h - 1 ? 255 : gray[x + 1][y + 1]);
        return rs / 9;
    }
}
