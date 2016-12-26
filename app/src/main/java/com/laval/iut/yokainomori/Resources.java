package com.laval.iut.yokainomori;

import android.media.MediaPlayer;
import android.os.AsyncTask;

/**
 * Created by lione on 26/12/2016.
 */

public class Resources {

    private static Resources resources = null;


    public boolean soundEffect = true;

    private MediaPlayer switchClick;
    private MediaPlayer click;

    private BackgroundMusic backgroundMusic;

    private MainActivity mainActivity;


    private Resources() {
    }

    private Resources(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        //switchClick = MediaPlayer.create(mainActivity, R.raw.switchclick);
        //click = MediaPlayer.create(mainActivity, R.raw.click);

        backgroundMusic = new BackgroundMusic();
        backgroundMusic.execute();
    }

    public static Resources getInstance(MainActivity mainActivity) {
        if (resources == null) {
            resources = new Resources(mainActivity);
        }
        return resources;
    }

    public static Resources getInstance() {
        if (resources == null) {
            resources = new Resources();
        }
        return resources;
    }

    public void playSwitchSound() {
        if (soundEffect)
            switchClick.start();
    }
    public void playClickSound() {
        if (soundEffect)
            click.start();
    }

    public void setVolume() {
        backgroundMusic.setVolume();
    }

    public void playBackgroundMusic(boolean b) {
        if (backgroundMusic != null) {
            if (b) {
                backgroundMusic.start();
            } else {
                backgroundMusic.cancel(true);
                backgroundMusic.stop();
            }
        }
    }

     class BackgroundMusic extends AsyncTask<Void, Void, Void> {

        private MediaPlayer player;

        public BackgroundMusic() {
            super();
            //player = MediaPlayer.create(mainActivity.getApplicationContext(), R.raw.music);
            player.setLooping(true);
            player.setVolume(0f, 0f);
        }

        @Override
        protected Void doInBackground(Void... params) {
            return null;
        }

        public void setVolume() {
            player.setVolume(1.0f, 1.0f);
        }

        public void start() {
            player.start();
        }

        public void stop(){
            player.pause();
        }
    }
}
