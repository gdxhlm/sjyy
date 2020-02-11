package cn.gdxhlm.mobilemedia.pagers;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;

import cn.gdxhlm.mobilemedia.R;
import cn.gdxhlm.mobilemedia.adapter.NetVideoAdapter;
import cn.gdxhlm.mobilemedia.base.BasePager;
import cn.gdxhlm.mobilemedia.domain.NetVideoItem;
import cn.gdxhlm.mobilemedia.utils.CacheUtil;
import cn.gdxhlm.mobilemedia.utils.LogUtil;
import cn.gdxhlm.mobilemedia.utils.MyApplication;

public class NetVideoPager extends BasePager {
    private ArrayList<NetVideoItem> netVideoItems;
    private ListView listView;


    public NetVideoPager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        View view=  View.inflate(context,R.layout.netvideo,null);
        listView=view.findViewById(R.id.lv_netvideo);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                NetVideoItem netVideoItem=netVideoItems.get(i);


                Intent intent=new Intent(MyApplication.getContext(),VitamioVideoActivity.class);
                intent.putExtra("uri",netVideoItem.getData());

                context.startActivity(intent);
            }
        });
        x.view().inject(this,view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        RequestParams params = new RequestParams("http://api.m.mtime.cn/PageSubArea/TrailerList.api");
        x.http().get(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                CacheUtil.save(context,"key",result);
                parseJson(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                if(CacheUtil.get(context,"key")!=null){
                    parseJson(CacheUtil.get(context,"key"));
                }

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

               Toast.makeText(context, "加载结束", Toast.LENGTH_SHORT).show();
            }
        });
        LogUtil.d("网络视频","初始化");
    }

    private void parseJson(String s) {
        netVideoItems=new ArrayList<NetVideoItem>();
        try {
            JSONObject jsonObject=new JSONObject(s);
            JSONArray jsonArray=jsonObject.optJSONArray("trailers");
            for(int i=0;i<jsonArray.length();i++){
                NetVideoItem netVideoItem=new NetVideoItem();

                JSONObject jsonObject1=jsonArray.optJSONObject(i);

                    String movieName=jsonObject1.optString("movieName");
                    netVideoItem.setMovieName(movieName);
                Log.d("网络", "movieName: "+movieName);

                    String coverImg=jsonObject1.optString("coverImg");
                    netVideoItem.setCoverImg(coverImg);
                Log.d("网络", "coverImg: "+coverImg);

                    String data=jsonObject1.optString("hightUrl");
                    netVideoItem.setData(data);
                Log.d("网络", "data: "+data);

                    String videoTitle=jsonObject1.optString("videoTitle");
                    netVideoItem.setVideoTitle(videoTitle);
                Log.d("网络", "videoTitle: "+videoTitle);

                    netVideoItems.add(netVideoItem);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //adpter
        NetVideoAdapter netVideoAdapter=new NetVideoAdapter(context,netVideoItems);
        listView.setAdapter(netVideoAdapter);
    }
}
