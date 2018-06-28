package com.xiaojun.boaoshuangpingji.beans;

import java.util.List;

/**
 * Created by Administrator on 2018/6/28.
 */

public class BuMenBeans {

    /**
     * createTime : 1530158363444
     * dtoResult : 0
     * modifyTime : 1530158363444
     * objects : [{"answer":"观众","answerCode":"A0","createTime":1527498846000,"dtoResult":0,"id":10000187,"modifyTime":1527498846000,"pageNum":0,"pageSize":0,"precent":0,"questionId":10000088,"sid":0,"status":0},{"answer":"媒体","answerCode":"A1","createTime":1527498846000,"dtoResult":0,"id":10000188,"modifyTime":1527498846000,"pageNum":0,"pageSize":0,"precent":0,"questionId":10000088,"sid":0,"status":0},{"answer":"员工","answerCode":"A2","createTime":1527498846000,"dtoResult":0,"id":10000189,"modifyTime":1527498846000,"pageNum":0,"pageSize":0,"precent":0,"questionId":10000088,"sid":0,"status":0},{"answer":"工作人员","answerCode":"A3","createTime":1527498846000,"dtoResult":0,"id":10000190,"modifyTime":1527498846000,"pageNum":0,"pageSize":0,"precent":0,"questionId":10000088,"sid":0,"status":0},{"accountId":10000065,"answer":"领导","answerCode":"b06","createTime":1527500343000,"dtoResult":0,"id":10000191,"modifyTime":1527500343000,"pageNum":0,"pageSize":0,"precent":0,"questionId":10000088,"sid":0,"status":0}]
     * pageIndex : 0
     * pageNum : 1
     * pageSize : 15
     * sid : 0
     * totalNum : 5
     */

    private long createTime;
    private int dtoResult;
    private long modifyTime;
    private int pageIndex;
    private int pageNum;
    private int pageSize;
    private int sid;
    private int totalNum;
    private List<ObjectsBean> objects;

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

    public long getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(long modifyTime) {
        this.modifyTime = modifyTime;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
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

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public List<ObjectsBean> getObjects() {
        return objects;
    }

    public void setObjects(List<ObjectsBean> objects) {
        this.objects = objects;
    }

    public static class ObjectsBean {
        /**
         * answer : 观众
         * answerCode : A0
         * createTime : 1527498846000
         * dtoResult : 0
         * id : 10000187
         * modifyTime : 1527498846000
         * pageNum : 0
         * pageSize : 0
         * precent : 0
         * questionId : 10000088
         * sid : 0
         * status : 0
         * accountId : 10000065
         */

        private String answer;
        private String answerCode;
        private long createTime;
        private int dtoResult;
        private int id;
        private long modifyTime;
        private int pageNum;
        private int pageSize;
        private int precent;
        private int questionId;
        private int sid;
        private int status;
        private int accountId;

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        public String getAnswerCode() {
            return answerCode;
        }

        public void setAnswerCode(String answerCode) {
            this.answerCode = answerCode;
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

        public int getPrecent() {
            return precent;
        }

        public void setPrecent(int precent) {
            this.precent = precent;
        }

        public int getQuestionId() {
            return questionId;
        }

        public void setQuestionId(int questionId) {
            this.questionId = questionId;
        }

        public int getSid() {
            return sid;
        }

        public void setSid(int sid) {
            this.sid = sid;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getAccountId() {
            return accountId;
        }

        public void setAccountId(int accountId) {
            this.accountId = accountId;
        }
    }
}
