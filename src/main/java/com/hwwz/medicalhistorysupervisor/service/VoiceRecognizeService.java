package com.hwwz.medicalhistorysupervisor.service;

import java.io.File;

public interface VoiceRecognizeService {
    public String doRecognize(File file) throws InterruptedException;
}
