package com.hwwz.medicalhistorysupervisor.service.impl;

import org.bytedeco.javacpp.avcodec;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.hwwz.medicalhistorysupervisor.service.impl.VoiceFileConvertServiceImpl.convert;

/**
 * @author: HuShili
 * @date: 2017/12/26
 * @description: none
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class VoiceFileConverServiceImplTest {

    @Test
    public void convertFile() {
        //Example
        //convert("input.wav", "output.wav", avcodec.AV_CODEC_ID_PCM_S16LE, 8000, 16,1);
    }
}
