package com.bawei.chosexx.Find.Bean;

import java.util.List;

/**
 * Created by 王帅彪 on 2017/12/13.
 */

public class FindBean {
    private String msg;
    private RetBean ret;
    private String code;
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public RetBean getRet() {
        return ret;
    }
    public void setRet(RetBean ret) {
        this.ret = ret;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public static class RetBean {
        private AdvBean adv;
        private int pnum;
        private int totalRecords;
        private int records;
        private int totalPnum;
        private List<?> bannerList;
        private List<ListBean> list;
        public AdvBean getAdv() {
            return adv;
        }
        public void setAdv(AdvBean adv) {
            this.adv = adv;
        }
        public int getPnum() {
            return pnum;
        }
        public void setPnum(int pnum) {
            this.pnum = pnum;
        }
        public int getTotalRecords() {
            return totalRecords;
        }
        public void setTotalRecords(int totalRecords) {
            this.totalRecords = totalRecords;
        }
        public int getRecords() {
            return records;
        }
        public void setRecords(int records) {
            this.records = records;
        }
        public int getTotalPnum() {
            return totalPnum;
        }
        public void setTotalPnum(int totalPnum) {
            this.totalPnum = totalPnum;
        }
        public List<?> getBannerList() {
            return bannerList;
        }
        public void setBannerList(List<?> bannerList) {
            this.bannerList = bannerList;
        }
        public List<ListBean> getList() {
            return list;
        }
        public void setList(List<ListBean> list) {
            this.list = list;
        }
        public static class AdvBean {
            private String imgURL;
            private String dataId;
            private String htmlURL;
            private String shareURL;
            private String title;
            public String getImgURL() {
                return imgURL;
            }
            public void setImgURL(String imgURL) {
                this.imgURL = imgURL;
            }
            public String getDataId() {
                return dataId;
            }
            public void setDataId(String dataId) {
                this.dataId = dataId;
            }
            public String getHtmlURL() {
                return htmlURL;
            }
            public void setHtmlURL(String htmlURL) {
                this.htmlURL = htmlURL;
            }
            public String getShareURL() {
                return shareURL;
            }
            public void setShareURL(String shareURL) {
                this.shareURL = shareURL;
            }
            public String getTitle() {
                return title;
            }
            public void setTitle(String title) {
                this.title = title;
            }
        }
        public static class ListBean {
            private int airTime;
            private String duration;
            private String loadtype;
            private int score;
            private String angleIcon;
            private String dataId;
            private String description;
            private String loadURL;
            private String shareURL;
            private String pic;
            private String title;
            private String roomId;
            public int getAirTime() {
                return airTime;
            }
            public void setAirTime(int airTime) {
                this.airTime = airTime;
            }
            public String getDuration() {
                return duration;
            }
            public void setDuration(String duration) {
                this.duration = duration;
            }
            public String getLoadtype() {
                return loadtype;
            }
            public void setLoadtype(String loadtype) {
                this.loadtype = loadtype;
            }
            public int getScore() {
                return score;
            }
            public void setScore(int score) {
                this.score = score;
            }
            public String getAngleIcon() {
                return angleIcon;
            }
            public void setAngleIcon(String angleIcon) {
                this.angleIcon = angleIcon;
            }
            public String getDataId() {
                return dataId;
            }
            public void setDataId(String dataId) {
                this.dataId = dataId;
            }
            public String getDescription() {
                return description;
            }
            public void setDescription(String description) {
                this.description = description;
            }
            public String getLoadURL() {
                return loadURL;
            }
            public void setLoadURL(String loadURL) {
                this.loadURL = loadURL;
            }
            public String getShareURL() {
                return shareURL;
            }
            public void setShareURL(String shareURL) {
                this.shareURL = shareURL;
            }
            public String getPic() {
                return pic;
            }
            public void setPic(String pic) {
                this.pic = pic;
            }
            public String getTitle() {
                return title;
            }
            public void setTitle(String title) {
                this.title = title;
            }
            public String getRoomId() {
                return roomId;
            }
            public void setRoomId(String roomId) {
                this.roomId = roomId;
            }
        }
    }
}