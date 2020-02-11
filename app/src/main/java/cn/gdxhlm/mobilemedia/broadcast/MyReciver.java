package cn.gdxhlm.mobilemedia.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

public class MyReciver extends BroadcastReceiver {

     public int level;
    @Override
    public void onReceive(Context context, Intent intent) {
        level=intent.getIntExtra("level",0);
        //Toast.makeText(context, String.valueOf(level), Toast.LENGTH_SHORT).show();

    }

}
