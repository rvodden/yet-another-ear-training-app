package com.vodden.music.yeata.desktop;

import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.io.jvm.AudioDispatcherFactory;
import com.badlogic.gdx.Gdx;
import dagger.Module;
import dagger.Provides;

import javax.sound.sampled.LineUnavailableException;

@Module
class DesktopModule {
    private static final Integer SAMPLE_RATE = 44100;
    private static final Integer BUFFER_SIZE = 2048;
    private static final Integer SAMPLE_OVERLAP = 1536;

    @Provides
    AudioDispatcher provideAudioDispatcher() {
        try {
            return AudioDispatcherFactory.fromDefaultMicrophone(SAMPLE_RATE, BUFFER_SIZE, SAMPLE_OVERLAP);
        } catch (LineUnavailableException lue) {
            Gdx.app.log("yaeta", "Failed to get line." + lue.getMessage());
            System.exit(-1);
        }
        return null; /* This will never execute */
    }

}
