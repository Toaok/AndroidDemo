package indi.toaok.androiddemo.http.rx.api.splash;

/**
 * @author Toaok
 * @version 1.0  2019/4/25.
 */
public class SplashRequestBean {
    private String page="1";
    private String count="10";


    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
