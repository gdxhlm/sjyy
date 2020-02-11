package cn.gdxhlm.mobilemedia;

import androidx.appcompat.app.AppCompatActivity;
import cn.gdxhlm.mobilemedia.activity.MainActivity;
import cn.gdxhlm.mobilemedia.utils.LogUtil;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;

public class SplashActivity extends AppCompatActivity {
   // private static final String TAG = SplashActivity.class.getSimpleName();当活动名称修改时，跟随修改
   private static final String TAG = "SplashActivity";
   private Handler handler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startInten();
                LogUtil.d(TAG, "当前线程名称 "+Thread.currentThread().getName());
            }
        },2000);
    }

    private void startInten() {
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        startInten();
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
