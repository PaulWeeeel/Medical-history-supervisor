package com.hwwz.medicalhistorysupervisor.service;


import com.iflytek.cloud.speech.*;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

@Service
public class VoiceRecognizeServiceImpl implements VoiceRecognizeService{

    private static final String APPID = "5a238678";

    private StringBuffer mResult = new StringBuffer();

    /** 最大等待时间， 单位ms */
    private int maxWaitTime = 500;
    /** 每次等待时间 */
    private int perWaitTime = 100;
    /** 出现异常时最多重复次数 */
    private int maxQueueTimes = 3;
    /** 音频文件 */
    private File file = null;

    // *************************************音频文件听写*************************************

    /**
     * 听写
     * @return
     * @throws InterruptedException
     */
    public String doRecognize(File file) throws InterruptedException {
        maxWaitTime = 500;
        maxQueueTimes = 3;

        if(maxQueueTimes <= 0) {
            mResult.setLength(0);
            mResult.append("解析异常！");
            return mResult.toString();
        }
        this.file = file;

        if (SpeechRecognizer.getRecognizer() == null)
            SpeechRecognizer.createRecognizer();
        return RecognizePcmfileByte();
    }

    /**
     * 自动化测试注意要点 如果直接从音频文件识别，需要模拟真实的音速，防止音频队列的堵塞
     * @throws InterruptedException
     */
    private String RecognizePcmfileByte() throws InterruptedException {
        // 1、读取音频文件
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
        // 2、音频流听写
        if (0 == voiceBuffer.length) {
            mResult.append("no audio avaible!");
        } else {
            //解析之前将存出结果置为空
            mResult.setLength(0);
            SpeechRecognizer recognizer = SpeechRecognizer.getRecognizer();
            recognizer.setParameter(SpeechConstant.DOMAIN, "iat");
            recognizer.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
            recognizer.setParameter(SpeechConstant.AUDIO_SOURCE, "-1");
            //写音频流时，文件是应用层已有的，不必再保存
//          recognizer.setParameter(SpeechConstant.ASR_AUDIO_PATH,
//                  "./iflytek.pcm");
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

            // 在原有的代码基础上主要添加了这个while代码等待音频解析完成，recognizer.isListening()返回true，说明解析工作还在进行
            while(recognizer.isListening()) {
                if(maxWaitTime < 0) {
                    mResult.setLength(0);
                    mResult.append("解析超时！");
                    break;
                }
                Thread.sleep(perWaitTime);
                maxWaitTime -= perWaitTime;
            }
        }
        return mResult.toString();
    }

    /**
     * 将字节缓冲区按照固定大小进行分割成数组
     *
     * @param buffer
     *            缓冲区
     * @param length
     *            缓冲区大小
     * @param spsize
     *            切割块大小
     * @return
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

        public void onBeginOfSpeech() { }

        public void onEndOfSpeech() { }

        public void onVolumeChanged(int volume) { }

        public void onResult(RecognizerResult result, boolean islast) {
            mResult.append(result.getResultString());
        }

        public void onError(SpeechError error) {
            try {
                doRecognize(file);
                maxQueueTimes--;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        }

        public void onEvent(int eventType, int arg1, int agr2, String msg) { }

    };

}
