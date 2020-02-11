package cn.gdxhlm.mobilemedia.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import cn.gdxhlm.mobilemedia.R;
import cn.gdxhlm.mobilemedia.base.BasePager;
import cn.gdxhlm.mobilemedia.pagers.MusicPager;
import cn.gdxhlm.mobilemedia.pagers.NetMusicPager;
import cn.gdxhlm.mobilemedia.pagers.NetVideoPager;
import cn.gdxhlm.mobilemedia.pagers.VideoPager;
import cn.gdxhlm.mobilemedia.utils.LogUtil;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {
    private static final String TAG = "MainActivity";
    private RadioGroup radioGroup;
    private ArrayList<BasePager> pagers;
    private FrameLayout frameLayout;
    private int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radioGroup=findViewById(R.id.rg);
        frameLayout=findViewById(R.id.framelayout);

        pagers=new ArrayList<BasePager>();
        pagers.add(new VideoPager(this));
        pagers.add(new MusicPager(this));
        pagers.add(new NetVideoPager(this));
        pagers.add(new NetMusicPager(this));


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    default:
                        position=0;
                        break;
                    case R.id.rb_bdyy:
                        position=1;
                        break;
                    case R.id.rb_wlsp:
                        position=2;
                        break;
                    case R.id.rb_wlyy:
                        position=3;
                        break;

                }
                LogUtil.d("测试",String.valueOf(position));
                setFragment();
            }
        });
        radioGroup.check(R.id.rb_bdsp);
    }

    private void setFragment() {
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayout,new Fragment(){
            @Nullable
            @Override
            public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
                BasePager basePager=pagers.get(position);
                basePager.initData();
                return basePager.view;
            }
        });
        fragmentTransaction.commit();

    }


}
