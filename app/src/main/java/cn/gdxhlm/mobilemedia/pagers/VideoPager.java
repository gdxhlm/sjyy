package cn.gdxhlm.mobilemedia.pagers;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.ArrayList;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import cn.gdxhlm.mobilemedia.R;
import cn.gdxhlm.mobilemedia.adapter.VideoListviewAdapter;
import cn.gdxhlm.mobilemedia.base.BasePager;
import cn.gdxhlm.mobilemedia.domain.MediaItem;
import cn.gdxhlm.mobilemedia.utils.LogUtil;
import cn.gdxhlm.mobilemedia.utils.MyApplication;

public class VideoPager extends BasePager {
    private ListView listView;
    private TextView textView;
    private ProgressBar progressBar;
    public static ArrayList<MediaItem> mediaItems;
    private Cursor cursor;

    public VideoPager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        View view= View.inflate(context,R.layout.video,null);
        listView=view.findViewById(R.id.lv_video);
        textView=view.findViewById(R.id.tv_video);
        progressBar=view.findViewById(R.id.pb_vedio);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MediaItem mediaItem=mediaItems.get(i);
                // Toast.makeText(MyApplication.getContext(), mediaItem.toString(), Toast.LENGTH_SHORT).show();
                Toast.makeText(MyApplication.getContext(), mediaItem.getData(), Toast.LENGTH_SHORT).show();
              /*  Intent intent=new Intent();
                intent.setDataAndType(Uri.parse(mediaItem.getData()),"video/*");
                context.startActivity(intent);*/
                Intent intent=new Intent(MyApplication.getContext(),VitamioVideoActivity.class);
                //intent.putExtra("uri",mediaItem.getData());
                intent.putExtra("duixiang",mediaItems);
                intent.putExtra("posion",i);
                context.startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void initData() {
        super.initData();

        mediaItems=new ArrayList<MediaItem>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                ContentResolver contentResolver=context.getContentResolver();
                Uri uri= MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                String[] sz={
                        MediaStore.Video.Media.DISPLAY_NAME,
                        MediaStore.Video.Media.DURATION,
                        MediaStore.Video.Media.SIZE,
                        MediaStore.Video.Media.DATA,
                        MediaStore.Video.Media.ARTIST
                };
                //权限判断


                cursor=contentResolver.query(uri,sz,null,null,null);
                if(cursor!=null){
                    while (cursor.moveToNext()){
                        MediaItem mediaItem=new MediaItem();
                        String name=cursor.getString(0);
                        mediaItem.setName(name);
                        String sc=cursor.getString(1);
                        mediaItem.setSc(sc);
                        String size=cursor.getString(2);
                        mediaItem.setSize(size);
                        String data=cursor.getString(3);
                        mediaItem.setData(data);
                        String artist=cursor.getString(4);
                        mediaItem.setArtist(artist);

                        mediaItems.add(mediaItem);
                    }
                    cursor.close();
                }else {
                    LogUtil.d("子线程","没有数据");
                }
            }
        }).start();
        VideoListviewAdapter videoListviewAdapter=new VideoListviewAdapter(context,mediaItems);

        listView.setAdapter(videoListviewAdapter);

        LogUtil.d("本地视频","初始化");
    }
}
