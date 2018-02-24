package org.netzd.videosource;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements SurfaceHolder.Callback, MediaPlayer.OnPreparedListener{

    private MediaPlayer mediaPlayer = null;
    private SurfaceHolder videoHolder = null;
    private SurfaceView videoSurfaceView = null;

    private String urlVideo = "https://archive.org/download/ksnn_compilation_master_the_internet/ksnn_compilation_master_the_internet_512kb.mp4";

    public MainActivityFragment() {
    }

    public static MainActivityFragment newInstance() {
        MainActivityFragment fragment = new MainActivityFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        videoSurfaceView = (SurfaceView) getActivity().findViewById(R.id.videoSurfaceView);
        videoHolder = videoSurfaceView.getHolder();
        //[] Agregamos callback para que trabaje con sourface
        videoHolder.addCallback(this);
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mediaPlayer.start();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try{
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDisplay(videoHolder);
            mediaPlayer.setDataSource(urlVideo);
            mediaPlayer.prepare();
            mediaPlayer.setOnPreparedListener(this);
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        }catch (Exception e){
            //Log.e("[Error]", e.getMessage());
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        return;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        return;
    }
}
