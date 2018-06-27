package com.xiaojun.boaoshuangpingji.beans;

import java.util.List;

/**
 * Created by Administrator on 2018/6/27.
 */

public class ZhanhuiBean {

    /**
     * createTime : 1530079989894
     * dtoDesc : 查询成功
     * dtoResult : 0
     * modifyTime : 1530079989894
     * objects : [{"createTime":1530079990377,"dtoResult":0,"endTime":1527670980000,"id":10000039,"modifyTime":1530079990377,"pageNum":0,"pageSize":0,"sid":0,"startTime":1527670980000,"subConferenceCode":"n235","subConferenceName":"hjdj"},{"createTime":1530079990411,"dtoResult":0,"endTime":1527840180000,"id":10000040,"modifyTime":1530079990411,"pageNum":0,"pageSize":0,"sid":0,"startTime":1527840180000,"subConferenceCode":"d45","subConferenceName":"gss"},{"createTime":1530079990413,"dtoResult":0,"endTime":1532514360000,"id":10000044,"modifyTime":1530079990413,"pageNum":0,"pageSize":0,"sid":0,"startTime":1531823160000,"subConferenceCode":"ss","subConferenceName":"ss"},{"createTime":1530079990415,"dtoResult":0,"endTime":1529465280000,"id":10000060,"modifyTime":1530079990415,"pageNum":0,"pageSize":0,"sid":0,"startTime":1528946880000,"subConferenceCode":"w1","subConferenceName":"消灭奥特曼"},{"createTime":1530079990417,"dtoResult":0,"endTime":1528527900000,"id":10000061,"modifyTime":1530079990417,"pageNum":0,"pageSize":0,"sid":0,"startTime":1528527900000,"subConferenceCode":"AQA","subConferenceName":"你有freestyle吗"},{"createTime":1530079990420,"dtoResult":0,"endTime":1528527960000,"id":10000073,"modifyTime":1530079990420,"pageNum":0,"pageSize":0,"sid":0,"startTime":1528527960000,"subConferenceCode":"sa","subConferenceName":"sa"},{"createTime":1530079990421,"dtoResult":0,"endTime":1528528080000,"id":10000074,"modifyTime":1530079990421,"pageNum":0,"pageSize":0,"sid":0,"startTime":1528528080000,"subConferenceCode":"sd","subConferenceName":"22"}]
     * pageNum : 0
     * pageSize : 0
     * sid : 0
     */

    private long createTime;
    private String dtoDesc;
    private int dtoResult;
    private long modifyTime;
    private int pageNum;
    private int pageSize;
    private int sid;
    private List<ObjectsBean> objects;

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getDtoDesc() {
        return dtoDesc;
    }

    public void setDtoDesc(String dtoDesc) {
        this.dtoDesc = dtoDesc;
    }

    public int getDtoResult() {
        return dtoResult;
    }

    public void setDtoResult(int dtoResult) {
        this.dtoResult = dtoResult;
    }

    public long getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(long modifyTime) {
        this.modifyTime = modifyTime;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public List<ObjectsBean> getObjects() {
        return objects;
    }

    public void setObjects(List<ObjectsBean> objects) {
        this.objects = objects;
    }

    public static class ObjectsBean {
        /**
         * createTime : 1530079990377
         * dtoResult : 0
         * endTime : 1527670980000
         * id : 10000039
         * modifyTime : 1530079990377
         * pageNum : 0
         * pageSize : 0
         * sid : 0
         * startTime : 1527670980000
         * subConferenceCode : n235
         * subConferenceName : hjdj
         */

        private long createTime;
        private int dtoResult;
        private long endTime;
        private int id;
        private long modifyTime;
        private int pageNum;
        private int pageSize;
        private int sid;
        private long startTime;
        private String subConferenceCode;
        private String subConferenceName;
        private boolean isCheck=true;

        public boolean isCheck() {
            return isCheck;
        }

        public void setCheck(boolean check) {
            isCheck = check;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getDtoResult() {
            return dtoResult;
        }

        public void setDtoResult(int dtoResult) {
            this.dtoResult = dtoResult;
        }

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public long getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(long modifyTime) {
            this.modifyTime = modifyTime;
        }

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getSid() {
            return sid;
        }

        public void setSid(int sid) {
            this.sid = sid;
        }

        public long getStartTime() {
            return startTime;
        }

        public void setStartTime(long startTime) {
            this.startTime = startTime;
        }

        public String getSubConferenceCode() {
            return subConferenceCode;
        }

        public void setSubConferenceCode(String subConferenceCode) {
            this.subConferenceCode = subConferenceCode;
        }

        public String getSubConferenceName() {
            return subConferenceName;
        }

        public void setSubConferenceName(String subConferenceName) {
            this.subConferenceName = subConferenceName;
        }
    }
}
