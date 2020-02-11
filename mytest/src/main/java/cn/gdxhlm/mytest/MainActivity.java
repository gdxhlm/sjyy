package cn.gdxhlm.mytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private MyCustomReciver myCustomReciver;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findID();
        imageView.setBackgroundResource(R.drawable.dh);
        AnimationDrawable animationDrawable= (AnimationDrawable) imageView.getBackground();
        animationDrawable.start();

        myCustomReciver=new MyCustomReciver();
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(myCustomReciver,intentFilter);

    }

    private void findID() {
        imageView=findViewById(R.id.iv_1);
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(myCustomReciver);
        super.onDestroy();
    }
}
