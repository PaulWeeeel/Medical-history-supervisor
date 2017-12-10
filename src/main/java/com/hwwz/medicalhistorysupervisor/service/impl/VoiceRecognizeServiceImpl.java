package com.hwwz.medicalhistorysupervisor.service.impl;

import com.iflytek.cloud.speech.*;
import org.springframework.stereotype.Service;
import com.hwwz.medicalhistorysupervisor.service.VoiceRecognizeService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author: HuShili
 * @date: 2017/12/7
 * @description: none
 */

@Service
public class VoiceRecognizeServiceImpl implements VoiceRecognizeService{

    private static final String appid = "5a238678";

    private StringBuffer mResult = new StringBuffer();

    /** max waiting time*/
    private int maxWaitTime = 500;
    /** per check time */
    private int perWaitTime = 100;
    /** max request time */
    private int maxQueueTimes = 3;
    /** voice file */
    private File file = null;
    /** user words format */
    private String user_word = "{\"userword\":[{\"name\":\"MHS words\",\"words\":[ \"MHS\"";


    static {
        Setting.setShowLog( false );
        SpeechUtility.createUtility(SpeechConstant.APPID + "=" + appid);
    }

    // *************************************Voice file transform*************************************

    /**
     * do recognize
     * @return
     * @throws InterruptedException
     */
    @Override
    public String doRecognize(File file) throws InterruptedException {
        maxWaitTime = 500;
        maxQueueTimes = 3;
        this.file = file;
        return ParseAttempt();
    }

    private String ParseAttempt() throws InterruptedException{
        if (maxQueueTimes <= 0) {
            mResult.setLength(0);
            mResult.append("Voice parse error!");
            return mResult.toString();
        }

        if (SpeechRecognizer.getRecognizer() == null)
            SpeechRecognizer.createRecognizer();
        return RecognizePcmfileByte();
    }
    /**
     * original voice file needed
     * @throws InterruptedException
     */
    private String RecognizePcmfileByte() throws InterruptedException {
        //file to buffer
        FileInputStream fis = null;
        byte[] voiceBuffer = null;
        try {
            fis = new FileInputStream(file);
            voiceBuffer = new byte[fis.available()];
            fis.read(voiceBuffer);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fis) {
                    fis.close();
                    fis = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // buffer checked
        if (0 == voiceBuffer.length) {
            mResult.append("no audio avaible!");
        } else {
            //reset result
            mResult.setLength(0);
            SpeechRecognizer recognizer = SpeechRecognizer.getRecognizer();
            recognizer.setParameter(SpeechConstant.DOMAIN, "iat");
            recognizer.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
            recognizer.setParameter(SpeechConstant.AUDIO_SOURCE, "-1");
            recognizer.setParameter(SpeechConstant.ACCENT, "mandarin");
            recognizer.setParameter( SpeechConstant.RESULT_TYPE, "plain" );
            recognizer.startListening(recListener);
            ArrayList<byte[]> buffers = splitBuffer(voiceBuffer,
                    voiceBuffer.length, 4800);
            for (int i = 0; i < buffers.size(); i++) {
                // 每次写入msc数据4.8K,相当150ms录音数据
                recognizer.writeAudio(buffers.get(i), 0, buffers.get(i).length);
                try {
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            recognizer.stopListening();

            // prevent analysis from stopped by the next request and check finished
            while(recognizer.isListening()) {
                if(maxWaitTime < 0) {
                    mResult.setLength(0);
                    mResult.append("Parse out of time!");
                    break;
                }
                Thread.sleep(perWaitTime);
                maxWaitTime -= perWaitTime;
            }
        }
        return mResult.toString();
    }

    /**
     * user-defined voice file buffer
     *
     * @param buffer
     * @param length
     * @param spsize size of every block
     */
    private ArrayList<byte[]> splitBuffer(byte[] buffer, int length, int spsize) {
        ArrayList<byte[]> array = new ArrayList<byte[]>();
        if (spsize <= 0 || length <= 0 || buffer == null
                || buffer.length < length)
            return array;
        int size = 0;
        while (size < length) {
            int left = length - size;
            if (spsize < left) {
                byte[] sdata = new byte[spsize];
                System.arraycopy(buffer, size, sdata, 0, spsize);
                array.add(sdata);
                size += spsize;
            } else {
                byte[] sdata = new byte[left];
                System.arraycopy(buffer, size, sdata, 0, left);
                array.add(sdata);
                size += left;
            }
        }
        return array;
    }

    /**
     * 听写监听器
     */
    private RecognizerListener recListener = new RecognizerListener() {

        public void onBeginOfSpeech() {
            System.out.println( "onBeginOfSpeech enter" );
            System.out.println("*************开始录音*************");
        }

        public void onEndOfSpeech() {
            System.out.println( "onEndOfSpeech enter" );
        }

        public void onVolumeChanged(int volume) {
            System.out.println( "onVolumeChanged enter" );
            if (volume > 0)
                System.out.println("*************音量值:" + volume + "*************");

        }

        public void onResult(RecognizerResult result, boolean islast) {
            System.out.println( "onResult enter" );
            mResult.append(result.getResultString());

            if( islast ){
                System.out.println("识别结果为:" + mResult.toString());
                mResult.delete(0, mResult.length());
            }
        }

        public void onError(SpeechError error) {
            System.out.println("*************" + error.getErrorCode()
                    + "*************");
        }

        public void onEvent(int eventType, int arg1, int agr2, String msg) {
            System.out.println( "onEvent enter" );
        }

    };

    // *************************************User words upload*************************************

    @Override
    public boolean doUpload(String string){
        user_word += ",\"" + string + "\"";
        SpeechRecognizer recognizer = SpeechRecognizer.getRecognizer();
        if ( recognizer == null) {
            recognizer = SpeechRecognizer.createRecognizer();

            if( null == recognizer ){
                System.out.println( "获取识别实例实败！" );
                return false;
            }
        }

        UserWords userwords = new UserWords(user_word + " ]}]}");
        recognizer.setParameter( SpeechConstant.DATA_TYPE, "userword" );
        recognizer.updateLexicon("userwords",
                userwords.toString(),
                lexiconListener);
        return true;
    }

    /**
     * 词表上传监听器
     */
    LexiconListener lexiconListener = new LexiconListener() {
        @Override
        public void onLexiconUpdated(String lexiconId, SpeechError error) {
            if (error == null)
                System.out.println("*************上传成功*************");
            else
                System.out.println("*************" + error.getErrorCode()
                        + "*************");
        }

    };
}
