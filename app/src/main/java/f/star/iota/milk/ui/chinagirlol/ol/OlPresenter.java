package f.star.iota.milk.ui.chinagirlol.ol;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import f.star.iota.milk.Url;
import f.star.iota.milk.base.PVContract;
import f.star.iota.milk.base.StringPresenter;


public class OlPresenter extends StringPresenter<List<OlBean>> {


    public OlPresenter(PVContract.View view) {
        super(view);
    }

    @Override
    protected List<OlBean> dealResponse(String s) {
        List<OlBean> list = new ArrayList<>();
        Elements select = Jsoup.parse(s).select("#postlist div table tbody > tr > td.plc > div.pct > div.pcb div > ignore_js_op img");
        for (Element element : select) {
            OlBean bean = new OlBean();
            String url = Url.CHINAGIRLOL_BASE + element.attr("file");
            bean.setUrl(url);
            list.add(bean);
        }
        return list;
    }
}
