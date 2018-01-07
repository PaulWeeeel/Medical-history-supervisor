package com.hwwz.medicalhistorysupervisor.service.impl;

import com.hwwz.medicalhistorysupervisor.service.FaceRecognizeService;
import com.megvii.cloud.http.CommonOperate;
import com.megvii.cloud.http.FaceSetOperate;
import com.megvii.cloud.http.Response;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

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
@Service("FaceRecognizeService")
public class FaceRecognizeServiceImpl implements FaceRecognizeService {
    /* API key */
    private final String key = "PfCvIGWrsgybKHUDb_K0h-KJL7Saxphk";
    /* API secret */
    private final String secret = "YdWwMZZtXoDzVQH3Fh7NTONRPboZhWGf";

    /* local face set */
    private static ArrayList<String> faceToken = new ArrayList<String>();
    /* string used in request */
    private static String faceSetToken;
    /* API operation on face img */
    private CommonOperate commonOperate;
    /* API operation on face set */
    private FaceSetOperate FaceSet;

    public FaceRecognizeServiceImpl(){
        commonOperate = new CommonOperate(key, secret, false);
        FaceSet = new FaceSetOperate(key, secret, false);

        if(faceToken.isEmpty()){
            createFaceSet();
        }
    }

    @Override
    public String doRecognize(File file) {
        String result = searchFace(file);

        //new user input
        //if(result == null) {
        //    String token = getFace(file);
        //    addFace(token);
        //    return token;
        //}
        //else{
        //return result;
        //}

        return result;
    }

    @Override
    public String addNewFace(File file){
        String token = getFace(file);
        addFace(token);

        return token;
    }

    @Override
    public boolean resetFaceSet(ArrayList<String> faceTokens){
        clearFaceSet();
        for (String token: faceTokens) {
            faceToken.add(token);
            System.out.println(addFace(token));
        }
        return true;
    }

    /**
     * Delete the face set
     * @return true if success
     */
    private boolean clearFaceSet(){
        boolean success = true;
        try {
            Response response = FaceSet.deleteFaceSetByToken(faceSetToken, 0);
            String result = new String(response.getContent());

            if(result.contains("CONCURRENCY_LIMIT_EXCEEDED")) {
                success = false;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        if(!success){
            clearFaceSet();
        }

        faceToken = new ArrayList<String>();
        System.out.println("FaceSet removed!");
        createFaceSet();
        return true;
    }

    /**
     * Create a new face set with no content
     * @return
     */
    private boolean createFaceSet(){
        boolean success = true;
        try {
            //create faceSet and add face
            String faceTokens = createFaceTokens(faceToken);
            Response faceset = FaceSet.createFaceSet(null, "test", null, faceTokens, null, 1);
            String faceSetResult = new String(faceset.getContent());

            try {
                faceSetToken = faceSetResult.substring(19, 19 + 32);
            }catch (StringIndexOutOfBoundsException e) {
                success = false;
            }

            System.out.println("faceSetResult:" + faceSetResult);
            if (faceset.getStatus() == 200) {
                System.out.println("\nfaceSet creat success");
                System.out.println("\ncreate result: ");
                System.out.println(faceSetResult);
                return true;
            } else {
                System.out.println("\nfaceSet creat failed");
                System.out.println("\ncreate result: ");
                System.out.println(faceSetResult);
                success = false;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        if(!success) {
            createFaceSet();
        }
        return true;
    }

    /**
     * Get the token of a img file
     * @param file
     * @return token of the face
     */
    private String getFace(File file){
        String token = new String();
        boolean  success = true;

        try {
            //detect first face by local file
            Response response = commonOperate.detectByte(getBitSet(file), 0, null);
            token = getFaceToken(response);

            System.out.println("get result:");
            System.out.println("faceToken: " + token);

            if(token.contains("CONCURRENCY_LIMIT_EXCEEDED")) {
                success = false;
            }
            else {
                faceToken.add(token);
                return token;
            }
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("get result: No face.");
            return null;
        }

        if(!success) {
            token = getFace(file);
        }

        return token;
    }

    /**
     * Search the face from the input file among the existing face set
     * @param file
     * @return face token, if new, return a new token
     */
    private String searchFace(File file){
        String result = new String();

        String first = new String();
        String first_confidence = new String();
        String first_token = new String();
        String threshold = new String();

        String best_result = new String();
        boolean success = true;
        boolean retry = false;

        try {
            //use search API to find face
            Response res = commonOperate.searchByFaceSetToken(null, null, getBitSet(file), faceSetToken, 1);
            result = new String(res.getContent());
            System.out.println("search result: ");
            System.out.println(result);

            if(result.contains("EMPTY_FACESET")) {
                addFace(getFace(file));
                success = false;
            }
            else if(result.contains("CONCURRENCY_LIMIT_EXCEEDED")) {
                success = false;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        if(!success) {
            best_result = searchFace(file);
            retry = true;
        }
        else if(!retry){
            //do analysis
            if(!result.contains("confidence"))
                return null;
            first = result.substring(result.indexOf("results") + 11);
            first = first.substring(0, first.indexOf("}") + 1);
            threshold = result.substring(result.indexOf("1e-5") + 6);
            threshold = threshold.substring(0, 7);

            first_confidence = first.substring(first.indexOf("confidence") + 13, first.indexOf(","));
            first_token = first.substring(first.indexOf("face_token") + 14, first.indexOf("}") - 1);

            System.out.println("\nfirst token: " + first_token);
            System.out.println("\nfisrt confidence: " + first_confidence);
            System.out.println("\nthreshold" + threshold);

            if(Double.parseDouble(first_confidence) > Double.parseDouble(threshold)) {
                best_result = first_token;
            }
            else {
                best_result = null;
            }
        }

        return best_result;
    }

    /**
     * Add a face to the face set
     * @param token
     * @return true if success
     */
    private boolean addFace(String token){
        String result = new String();
        boolean success = true;

        try {
            Response res = FaceSet.addFaceByFaceToken(token, faceSetToken);
            result = new String(res.getContent());
            System.out.println("add result: ");
            System.out.println(result);

            if(result.contains("CONCURRENCY_LIMIT_EXCEEDED")) {
                success = false;
            }
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        if(!success) {
            addFace(token);
        }
        return true;
    }

    /**
     * Create a token string from the token array
     * @param faceTokens
     * @return token string
     */
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

    /**
     * Get the byte file form a img file
     * @param file
     * @return byte array
     */
    private byte[] getBitSet(File file) throws IOException{
        BufferedImage bi= ImageIO.read(file);//read image by ImageIO
        int h=bi.getHeight();//get height
        int w=bi.getWidth();//get width
        int rgb=bi.getRGB(0, 0);//get (0,0) rgb
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

    /**
     * Get face token from the json in the response
     * @param response
     * @return face token
     */
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
        //or new
        Color c=new Color(rgb);
        r=c.getRed();
        g=c.getGreen();
        b=c.getBlue();
        int top=(r+g+b)/3;
        return (int)(top);
    }

    /**
     * Get average grey level
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

