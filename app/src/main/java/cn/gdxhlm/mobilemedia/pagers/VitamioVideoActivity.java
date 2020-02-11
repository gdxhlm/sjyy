package cn.gdxhlm.mobilemedia.pagers;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import cn.gdxhlm.mobilemedia.R;
import cn.gdxhlm.mobilemedia.broadcast.MyReciver;
import cn.gdxhlm.mobilemedia.domain.MediaItem;
import cn.gdxhlm.mobilemedia.utils.MyApplication;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;

public class VitamioVideoActivity extends Activity implements View.OnClickListener {
    private Button btn_pause;
    private SeekBar seekBar_sp;
    private MyReciver myReciver;
    private TextView tv_dl;

    private ArrayList<MediaItem> mediaItems;
    private String uri;
    private int posion;
    private GestureDetector gestureDetector;
    private io.vov.vitamio.widget.VideoView myVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Vitamio.isInitialized(this);
        setContentView(R.layout.activity_video_vitamio);
        tv_dl=findViewById(R.id.dl);
        myVideoView=findViewById(R.id.vv_vitamio);
        btn_pause=findViewById(R.id.btn_pause);
        btn_pause.setOnClickListener(this);
        seekBar_sp=findViewById(R.id.skb_sp);
        seekBar_sp.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(b){
                    myVideoView.seekTo(i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        getDate();
        setDate();

       // videoView.setVideoPath("http://devimages.apple.com.edgekey.net/streaming/examples/bipbop_4x3/gear2/prog_index.m3u8");
        myVideoView.setOnPreparedListener((io.vov.vitamio.MediaPlayer.OnPreparedListener) new MyOnPreparedListener());
        myVideoView.setMediaController(new MediaController(this));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            myVideoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
                @Override
                public boolean onInfo(MediaPlayer mediaPlayer, int i, int i1) {
                    switch (i){
                        case MediaPlayer.MEDIA_INFO_BUFFERING_START:

                            Toast.makeText(VitamioVideoActivity.this, "卡顿开始了", Toast.LENGTH_SHORT).show();
                            break;
                        case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                            Toast.makeText(VitamioVideoActivity.this, "卡顿结束", Toast.LENGTH_SHORT).show();
                             break;
                    }

                    return false;
                }
            });
        }
        myReciver=new MyReciver();
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(myReciver,intentFilter);

        gestureDetector= new GestureDetector(MyApplication.getContext(), new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent motionEvent) {
                if(myVideoView.isPlaying()){
                    myVideoView.pause();
                }else {
                    myVideoView.start();
                }
                return false;
            }

            @Override
            public void onShowPress(MotionEvent motionEvent) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                return false;
            }

            @Override
            public void onLongPress(MotionEvent motionEvent) {

            }

            @Override
            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                return false;
            }
        });

    }

    private void setDate() {
        //myVideoView.setVideoPath("http://192.168.1.107/vd/2.mp4");

        if(mediaItems!=null&&mediaItems.size()>0){
            myVideoView.setVideoURI(Uri.parse(mediaItems.get(posion).getData()));
        }else if(uri!=null){
            myVideoView.setVideoURI(Uri.parse(uri));
        }


    }

    private void getDate() {
        Intent intent=getIntent();
        uri=intent.getStringExtra("uri");
         mediaItems= (ArrayList<MediaItem>) intent.getSerializableExtra("duixiang");
         posion=intent.getIntExtra("posion",0);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_pause:
                if(myVideoView.isPlaying()){
                    myVideoView.pause();
                }else {
                    myVideoView.start();
                }
                break;
        }
    }
    private Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what){
                case 1:
                    seekBar_sp.setProgress((int) myVideoView.getCurrentPosition());
                    handler.removeMessages(1);
                    handler.sendEmptyMessageDelayed(1,1000);
                    Intent intent=new Intent("cn.gdxhlm");


            }
            return false;
        }
    });

    class MyOnPreparedListener implements MediaPlayer.OnPreparedListener {

        @Override
        public void onPrepared(MediaPlayer mediaPlayer) {

            seekBar_sp.setMax((int) myVideoView.getDuration());
            handler.sendEmptyMessage(1);
            //myVideoView.setXY(200,222);
            mediaPlayer.setPlaybackSpeed(2.0f);
            myVideoView.start();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(myReciver);
        myReciver=null;
        super.onDestroy();

    }
}
