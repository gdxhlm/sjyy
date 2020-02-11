package cn.gdxhlm.mobilemedia.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import cn.gdxhlm.mobilemedia.R;

public class TitleBar extends LinearLayout implements View.OnClickListener {
    private ImageView imageViewxm;
    private TextView textViewqwss;
    private Context context;
    public TitleBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        LayoutInflater.from(context).inflate(R.layout.titlebar,this);
        imageViewxm=findViewById(R.id.iv_xm);
        textViewqwss=findViewById(R.id.tv_qwss);

        imageViewxm.setOnClickListener(this);
        textViewqwss.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_xm :
                Toast.makeText(context, "点击了熊猫", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_qwss :
                Toast.makeText(context, "点击了全网搜索", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
