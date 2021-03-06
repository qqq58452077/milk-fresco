package f.star.iota.milk.ui.pixivic;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import f.star.iota.milk.R;
import f.star.iota.milk.base.BaseAdapter;


public class PixivicAdapter extends BaseAdapter<PixivicViewHolder, PixivicBean.ResultsBean> {

    @Override
    public PixivicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PixivicViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_description, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((PixivicViewHolder) holder).bindView(mBeans);
    }
}
