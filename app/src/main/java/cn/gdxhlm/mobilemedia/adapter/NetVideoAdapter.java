package cn.gdxhlm.mobilemedia.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.x;

import java.util.ArrayList;

import cn.gdxhlm.mobilemedia.R;
import cn.gdxhlm.mobilemedia.domain.NetVideoItem;
import cn.gdxhlm.mobilemedia.utils.MyApplication;

public class NetVideoAdapter extends BaseAdapter {
    private ArrayList<NetVideoItem> netVideoItems;
    private Context context;


    public  NetVideoAdapter(Context context,ArrayList<NetVideoItem> netVideoItems){
        this.netVideoItems=netVideoItems;
        this.context=context;
    }

    @Override
    public int getCount() {
        return netVideoItems.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        NetVideoItem netVideoItem=netVideoItems.get(i);
        ViewHolder viewHolder=new ViewHolder();
        if(view==null){
            view=View.inflate(context, R.layout.adpter_netvideo,null);
            viewHolder.imageView=view.findViewById(R.id.iv_netvideo);
            viewHolder.textView_title=view.findViewById(R.id.tv_name_netvideo);
            viewHolder.textView_jj=view.findViewById(R.id.tv_jj_netvideo);

            view.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) view.getTag();
        }
        x.image().bind(viewHolder.imageView,netVideoItem.getCoverImg());
        viewHolder.textView_title.setText(netVideoItem.getMovieName());
        viewHolder.textView_jj.setText(netVideoItem.getVideoTitle());


        return view;
    }
    static class ViewHolder{
        ImageView imageView;
        TextView textView_title;
        TextView textView_jj;

    }
}
