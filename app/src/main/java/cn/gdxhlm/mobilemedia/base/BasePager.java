package cn.gdxhlm.mobilemedia.base;

import android.app.Activity;
import android.content.Context;
import android.view.View;

public abstract class BasePager extends Activity{
    public final Context context;
    public View view;
    public BasePager(Context context){
        this.context=context;
        view=initView();

    }

    public abstract View initView();
    public void initData(){

    };

}
