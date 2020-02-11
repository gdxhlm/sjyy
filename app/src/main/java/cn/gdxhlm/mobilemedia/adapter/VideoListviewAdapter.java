package cn.gdxhlm.mobilemedia.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import cn.gdxhlm.mobilemedia.R;
import cn.gdxhlm.mobilemedia.domain.MediaItem;
import cn.gdxhlm.mobilemedia.pagers.VideoPager;

public class VideoListviewAdapter extends BaseAdapter {
    private Context context;
    private    ArrayList<MediaItem> mediaItems;
    public VideoListviewAdapter(Context context,ArrayList<MediaItem> mediaItems){
        this.mediaItems=mediaItems;
        this.context=context;
    }

    @Override
    public int getCount() {
        return VideoPager.mediaItems.size();
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
        ViewHolder viewHolder=new ViewHolder();
        MediaItem mediaItem= (MediaItem) getItem(i);
        if(view==null){
            view=View.inflate(context, R.layout.item_lvadapter,null);
            viewHolder.iv_adapter=view.findViewById(R.id.iv_adapter);
            viewHolder.tv_adapter_name=view.findViewById(R.id.tv_adapter_name);
            viewHolder.tv_adapter_time=view.findViewById(R.id.tv_adapter_time);
            viewHolder.tv_adapter_size=view.findViewById(R.id.tv_adapter_size);
            view.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) view.getTag();

        }
        viewHolder.tv_adapter_name.setText(mediaItems.get(i).getName());
        viewHolder.tv_adapter_time.setText(mediaItems.get(i).getSc());
        viewHolder.tv_adapter_size.setText(mediaItems.get(i).getSize());
        return view;
    }
    static class ViewHolder{
        ImageView iv_adapter;
        TextView tv_adapter_name;
        TextView tv_adapter_time;
        TextView tv_adapter_size;
    }
}
