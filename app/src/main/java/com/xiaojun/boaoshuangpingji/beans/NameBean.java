package com.xiaojun.boaoshuangpingji.beans;

import java.util.List;

/**
 * Created by Administrator on 2018/6/22.
 */

public class NameBean {

    /**
     * createTime : 1529657686455
     * dtoResult : 0
     * modifyTime : 1529657686455
     * objects : [{"accountId":10000065,"assemblyId":"n53","channel":0,"city":"广州","come_from":"21世纪经济报道","companyName":"10000025","contact":"李云飞","contactWay":"133-0101-0203","createTime":1529391063000,"department":"SVIP","dtoResult":0,"gender":0,"id":10412725,"identity":"","inChengFlightEnd":1532150400000,"inChengFlightNo":"南航CZ6742广州三亚","inChengFlightStart":1532144700000,"jobStatus":0,"location":"{\"a22\":\"0,宴会厅B5\",\"a23\":\"2号桌02-宴会厅B5,2号桌02\"}","meetingId":0,"modifyTime":1529466834000,"name":"杨云飞","num":0,"outChengFlightNo":"南航CZ393三亚广州","outChengFlightStart":1532408700000,"pageNum":0,"pageSize":0,"phone":"131-0101-0203","photo_ids":"20180620/1529466800712_247.jpg","remark":"","roomType":"大床","sexs":"男","sid":0,"sourceMeeting":"n235","status":1,"subject_type":0,"title":"总经理"},{"accountId":10000065,"assemblyId":"n53","channel":0,"city":"广州","come_from":"21世纪经济报道","companyName":"10000025","contact":"李云飞","contactWay":"133-0101-0202","createTime":1529391059000,"department":"SVIP","dtoResult":0,"gender":0,"id":10412724,"identity":"","inChengFlightEnd":1532236800000,"inChengFlightNo":"南航CZ6742广州三亚","inChengFlightStart":1532231100000,"jobStatus":0,"location":"{\"a22\":\"0,宴会厅A1\",\"a23\":\"主桌01-宴会厅A1,主桌01\"}","meetingId":0,"modifyTime":1529463899000,"name":"王义军","num":0,"outChengFlightNo":"南航CZ359广州三亚","outChengFlightStart":1532329200000,"pageNum":0,"pageSize":0,"phone":"131-0101-0202","photo_ids":"20180620/1529463848002_78.jpg","remark":"","roomType":"套房","sexs":"男","sid":0,"sourceMeeting":"n235","status":1,"subject_type":0,"title":"党委书记"}]
     * pageIndex : 0
     * pageNum : 1
     * pageSize : 2
     * sid : 0
     * totalNum : 119
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
         * accountId : 10000065
         * assemblyId : n53
         * channel : 0
         * city : 广州
         * come_from : 21世纪经济报道
         * companyName : 10000025
         * contact : 李云飞
         * contactWay : 133-0101-0203
         * createTime : 1529391063000
         * department : SVIP
         * dtoResult : 0
         * gender : 0
         * id : 10412725
         * identity :
         * inChengFlightEnd : 1532150400000
         * inChengFlightNo : 南航CZ6742广州三亚
         * inChengFlightStart : 1532144700000
         * jobStatus : 0
         * location : {"a22":"0,宴会厅B5","a23":"2号桌02-宴会厅B5,2号桌02"}
         * meetingId : 0
         * modifyTime : 1529466834000
         * name : 杨云飞
         * num : 0
         * outChengFlightNo : 南航CZ393三亚广州
         * outChengFlightStart : 1532408700000
         * pageNum : 0
         * pageSize : 0
         * phone : 131-0101-0203
         * photo_ids : 20180620/1529466800712_247.jpg
         * remark :
         * roomType : 大床
         * sexs : 男
         * sid : 0
         * sourceMeeting : n235
         * status : 1
         * subject_type : 0
         * title : 总经理
         */

        private int accountId;
        private String assemblyId;
        private int channel;
        private String city;
        private String come_from;
        private String companyName;
        private String contact;
        private String contactWay;
        private long createTime;
        private String department;
        private int dtoResult;
        private int gender;
        private int id;
        private String identity;
        private long inChengFlightEnd;
        private String inChengFlightNo;
        private long inChengFlightStart;
        private int jobStatus;
        private String location;
        private int meetingId;
        private long modifyTime;
        private String name;
        private int num;
        private String outChengFlightNo;
        private long outChengFlightStart;
        private int pageNum;
        private int pageSize;
        private String phone;
        private String photo_ids;
        private String remark;
        private String roomType;
        private String sexs;
        private int sid;
        private String sourceMeeting;
        private int status;
        private int subject_type;
        private String title;
        private String interviewee;
        private String roomNumber;
        private String industry;

        public String getIndustry() {
            return industry;
        }

        public void setIndustry(String industry) {
            this.industry = industry;
        }

        public String getRoomNumber() {
            return roomNumber;
        }

        public void setRoomNumber(String roomNumber) {
            this.roomNumber = roomNumber;
        }

        public String getInterviewee() {
            return interviewee;
        }

        public void setInterviewee(String interviewee) {
            this.interviewee = interviewee;
        }

        public int getAccountId() {
            return accountId;
        }

        public void setAccountId(int accountId) {
            this.accountId = accountId;
        }

        public String getAssemblyId() {
            return assemblyId;
        }

        public void setAssemblyId(String assemblyId) {
            this.assemblyId = assemblyId;
        }

        public int getChannel() {
            return channel;
        }

        public void setChannel(int channel) {
            this.channel = channel;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCome_from() {
            return come_from;
        }

        public void setCome_from(String come_from) {
            this.come_from = come_from;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

        public String getContactWay() {
            return contactWay;
        }

        public void setContactWay(String contactWay) {
            this.contactWay = contactWay;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public int getDtoResult() {
            return dtoResult;
        }

        public void setDtoResult(int dtoResult) {
            this.dtoResult = dtoResult;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getIdentity() {
            return identity;
        }

        public void setIdentity(String identity) {
            this.identity = identity;
        }

        public long getInChengFlightEnd() {
            return inChengFlightEnd;
        }

        public void setInChengFlightEnd(long inChengFlightEnd) {
            this.inChengFlightEnd = inChengFlightEnd;
        }

        public String getInChengFlightNo() {
            return inChengFlightNo;
        }

        public void setInChengFlightNo(String inChengFlightNo) {
            this.inChengFlightNo = inChengFlightNo;
        }

        public long getInChengFlightStart() {
            return inChengFlightStart;
        }

        public void setInChengFlightStart(long inChengFlightStart) {
            this.inChengFlightStart = inChengFlightStart;
        }

        public int getJobStatus() {
            return jobStatus;
        }

        public void setJobStatus(int jobStatus) {
            this.jobStatus = jobStatus;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public int getMeetingId() {
            return meetingId;
        }

        public void setMeetingId(int meetingId) {
            this.meetingId = meetingId;
        }

        public long getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(long modifyTime) {
            this.modifyTime = modifyTime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getOutChengFlightNo() {
            return outChengFlightNo;
        }

        public void setOutChengFlightNo(String outChengFlightNo) {
            this.outChengFlightNo = outChengFlightNo;
        }

        public long getOutChengFlightStart() {
            return outChengFlightStart;
        }

        public void setOutChengFlightStart(long outChengFlightStart) {
            this.outChengFlightStart = outChengFlightStart;
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

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPhoto_ids() {
            return photo_ids;
        }

        public void setPhoto_ids(String photo_ids) {
            this.photo_ids = photo_ids;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getRoomType() {
            return roomType;
        }

        public void setRoomType(String roomType) {
            this.roomType = roomType;
        }

        public String getSexs() {
            return sexs;
        }

        public void setSexs(String sexs) {
            this.sexs = sexs;
        }

        public int getSid() {
            return sid;
        }

        public void setSid(int sid) {
            this.sid = sid;
        }

        public String getSourceMeeting() {
            return sourceMeeting;
        }

        public void setSourceMeeting(String sourceMeeting) {
            this.sourceMeeting = sourceMeeting;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getSubject_type() {
            return subject_type;
        }

        public void setSubject_type(int subject_type) {
            this.subject_type = subject_type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
