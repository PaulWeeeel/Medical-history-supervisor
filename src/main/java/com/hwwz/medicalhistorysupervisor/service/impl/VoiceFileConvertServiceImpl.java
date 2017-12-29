package com.hwwz.medicalhistorysupervisor.service.impl;

import com.hwwz.medicalhistorysupervisor.service.VoiceFileConvertService;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.FrameRecorder;
import org.bytedeco.javacv.FrameRecorder.Exception;
import org.springframework.stereotype.Service;


/**
 * @author: HuShili
 * @date: 2017/12/26
 * @description: none
 */
@Service
public class VoiceFileConvertServiceImpl implements VoiceFileConvertService {
    /**
     * Audio convert
     *
     * @param inputFile
     * @param outputFile
     * @param audioCodec
     * @param sampleRate
     * @param audioBitrate
     */
    @Override
    public void convert(String inputFile, String outputFile, int audioCodec, int sampleRate, int audioBitrate,
                               int audioChannels) {
        Frame audioSamples = null;
        // recorder
        FFmpegFrameRecorder recorder = null;
        // grabber
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(inputFile);

        // start grabber
        if (start(grabber)) {
            recorder = new FFmpegFrameRecorder(outputFile, audioChannels);
            recorder.setAudioOption("crf", "0");
            recorder.setAudioCodec(audioCodec);
            recorder.setAudioBitrate(audioBitrate);
            recorder.setAudioChannels(audioChannels);
            recorder.setSampleRate(sampleRate);
            recorder.setAudioQuality(0);
            recorder.setAudioOption("aq", "10");
            // start recorder
            if (start(recorder)) {
                try {
                    // grab the audio
                    while ((audioSamples = grabber.grab()) != null) {
                        recorder.setTimestamp(grabber.getTimestamp());
                        recorder.record(audioSamples);
                    }

                } catch (org.bytedeco.javacv.FrameGrabber.Exception e1) {
                    System.err.println("Grab Failed!");
                } catch (Exception e) {
                    System.err.println("Record Failed!");
                }
                stop(grabber);
                stop(recorder);
            }
        }

    }

    public boolean start(FrameGrabber grabber) {
        try {
            grabber.start();
            return true;
        } catch (org.bytedeco.javacv.FrameGrabber.Exception e2) {
            try {
                System.err.println("Start Recorder Failed, Restarting...");
                grabber.restart();
                return true;
            } catch (org.bytedeco.javacv.FrameGrabber.Exception e) {
                try {
                    System.err.println("Restart Recorder Failed, Stopping...");
                    grabber.stop();
                } catch (org.bytedeco.javacv.FrameGrabber.Exception e1) {
                    System.err.println("Close Recorder Failed!");
                }
            }

        }
        return false;
    }

    public boolean start(FrameRecorder recorder) {
        try {
            recorder.start();
            return true;
        } catch (Exception e2) {
            try {
                System.err.println("Start Recorder Failed, Restarting...");
                recorder.stop();
                recorder.start();
                return true;
            } catch (Exception e) {
                try {
                    System.err.println("Restart Recorder Failed, Stopping...");
                    recorder.stop();
                } catch (Exception e1) {
                    System.err.println("Close Recorder Failed!");
                }
            }
        }
        return false;
    }

    public boolean stop(FrameGrabber grabber) {
        try {
            grabber.flush();
            grabber.stop();
            return true;
        } catch (org.bytedeco.javacv.FrameGrabber.Exception e) {
            return false;
        } finally {
            try {
                grabber.stop();
            } catch (org.bytedeco.javacv.FrameGrabber.Exception e) {
                System.err.println("Close Grabber Failed!");
            }
        }
    }

    public boolean stop(FrameRecorder recorder) {
        try {
            recorder.stop();
            recorder.release();
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            try {
                recorder.stop();
            } catch (Exception e) {

            }
        }
    }
}
