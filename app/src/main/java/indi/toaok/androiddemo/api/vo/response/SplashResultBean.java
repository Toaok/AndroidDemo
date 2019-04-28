package indi.toaok.androiddemo.api.vo.response;

import java.util.List;

import indi.toaok.androiddemo.http.rx.response.BaseResponseBean;

/**
 * @author Toaok
 * @version 1.0  2019/4/25.
 */
public class SplashResultBean extends BaseResponseBean<List<SplashResultBean.ResultBean>> {

    public static class ResultBean {
        /**
         * id : 666
         * time : 2018-12-14 04:00:01
         * img : https://ws1.sinaimg.cn/large/0065oQSqgy1fy58bi1wlgj30sg10hguu.jpg
         */

        private int id;
        private String time;
        private String img;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        @Override
        public String toString() {
            return "ResultBean{" +
                    "id=" + id +
                    ", time='" + time + '\'' +
                    ", img='" + img + '\'' +
                    '}';
        }
    }
}
