// package f.star.iota.milk.ui.bcy.selected;
//
//
// import org.jsoup.Jsoup;
// import org.jsoup.nodes.Element;
// import org.jsoup.select.Elements;
//
// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.List;
//
// import f.star.iota.milk.base.PVContract;
// import f.star.iota.milk.base.StringPresenter;
//
// public class BCYSelectedPresenter extends StringPresenter<List<BCYSelectedBean>> {
//
//
//     public BCYSelectedPresenter(PVContract.View view) {
//         super(view);
//     }
//
//     @Override
//     protected List<BCYSelectedBean> dealResponse(String s, HashMap<String, String> headers) {
//         List<BCYSelectedBean> list = new ArrayList<>();
//         Elements select = Jsoup.parse(s).select("#discover_index_list > li.disc_one");
//         for (Element e : select) {
//             BCYSelectedBean bean = new BCYSelectedBean();
//             String des = e.select("a > div > p > span:nth-child(2)").text();
//             bean.setDescription(des);
//             String preview = e.select("a > div > div > img").attr("src");
//             bean.setPreview(preview);
//             String url = e.select("a").attr("href");
//             bean.setUrl(url);
//             bean.setHeaders(headers);
//             list.add(bean);
//         }
//         return list;
//     }
// }
