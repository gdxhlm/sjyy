package cn.gdxhlm.mobilemedia.pagers;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import cn.gdxhlm.mobilemedia.base.BasePager;
import cn.gdxhlm.mobilemedia.utils.LogUtil;

public class NetMusicPager extends BasePager {
    private TextView textView;
    public NetMusicPager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        textView=new TextView(context);
        textView.setTextSize(22);
        textView.setTextColor(Color.RED);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        textView.setText("网络音乐");
        LogUtil.d("网络音乐","初始化");
    }
}
