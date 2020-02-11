package cn.gdxhlm.mytest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyCustomReciver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, String.valueOf(intent.getIntExtra("level",0)), Toast.LENGTH_SHORT).show();
    }
}
