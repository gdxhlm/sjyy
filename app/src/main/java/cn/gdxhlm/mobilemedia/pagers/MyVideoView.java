package cn.gdxhlm.mobilemedia.pagers;

import android.content.Context;
import android.media.AudioManager;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.VideoView;

public class MyVideoView extends VideoView {
    public MyVideoView(Context context) {
        this(context,null);
    }

    public MyVideoView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(widthMeasureSpec,heightMeasureSpec);
    }
    public   void setXY(int x,int y){

        ViewGroup.LayoutParams layoutParams=getLayoutParams();
        layoutParams.width=x;
        layoutParams.height=y;
        setLayoutParams(layoutParams);
    }
}
