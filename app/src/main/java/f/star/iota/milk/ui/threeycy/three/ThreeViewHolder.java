package f.star.iota.milk.ui.threeycy.three;


import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import f.star.iota.milk.Menus;
import f.star.iota.milk.R;
import f.star.iota.milk.base.BaseViewHolder;
import f.star.iota.milk.base.PCBean;
import f.star.iota.milk.fresco.FrescoLoader;

public class ThreeViewHolder extends BaseViewHolder<ThreeBean> {
    @BindView(R.id.card_view)
    CardView mCardView;
    @BindView(R.id.simple_drawee_view_image)
    SimpleDraweeView mSimpleDraweeView;

    public ThreeViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindView(final List<ThreeBean> beans) {
        final ThreeBean bean = beans.get(getAdapterPosition());
        HashMap<String, String> headers = bean.getHeaders();
        headers.put("Accept", "image/webp,image/*,*/*;q=0.8");
        bean.setHeaders(headers);
        FrescoLoader.load(mSimpleDraweeView, bean.getUrl(),bean.getHeaders());
        mCardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new AlertDialog.Builder(mContext)
                        .setTitle("是否下载")
                        .setNeutralButton("复制地址", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                copy(bean.getUrl());
                            }
                        })
                        .setNegativeButton("浏览器打开", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                open(bean.getUrl());
                            }
                        })
                        .setPositiveButton("下载", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                download(bean.getUrl(), bean.getUrl(),
                                        Menus.MENU_MMCOOLS, null);
                            }
                        })
                        .show();
                return true;
            }
        });
        mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<PCBean> imgs = new ArrayList<>();
                for (ThreeBean bean : beans) {
                    imgs.add(new PCBean(bean.getUrl(), bean.getUrl(), Menus.MENU_MMCOOLS,
                            "下载地址：" + bean.getUrl()));
                }
                show(imgs);
            }
        });
    }
}