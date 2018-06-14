package com.xiaojun.boaoshuangpingji.beans;

import java.util.List;

/**
 * Created by Administrator on 2018/6/13.
 */

public class TeZheng2 {


    /**
     * faces : [{"rect":{"left":63,"top":106,"width":138,"height":138},"attrs":{"age":28.66101,"pose":{"roll":-4.596921,"yaw":54.927704,"pitch":8.616277},"gender":{"male":0.97947836,"female":0.020521712},"face_quality":{"blurness":0.89164686},"acceptable":0.12068877,"gender_type":0},"quality":0.12068877,"confidence":0.9999318}]
     * image_rect : {"left":0,"top":0,"width":284,"height":218}
     * raw_rect : {"left":0,"top":0,"width":284,"height":218}
     */

    private ImageRectBean image_rect;
    private RawRectBean raw_rect;
    private List<FacesBean> faces;
    private String bitbit;

    public String getBitbit() {
        return bitbit;
    }

    public void setBitbit(String bitbit) {
        this.bitbit = bitbit;
    }

    public ImageRectBean getImage_rect() {
        return image_rect;
    }

    public void setImage_rect(ImageRectBean image_rect) {
        this.image_rect = image_rect;
    }

    public RawRectBean getRaw_rect() {
        return raw_rect;
    }

    public void setRaw_rect(RawRectBean raw_rect) {
        this.raw_rect = raw_rect;
    }

    public List<FacesBean> getFaces() {
        return faces;
    }

    public void setFaces(List<FacesBean> faces) {
        this.faces = faces;
    }

    public static class ImageRectBean {
        /**
         * left : 0
         * top : 0
         * width : 284
         * height : 218
         */

        private int left;
        private int top;
        private int width;
        private int height;

        public int getLeft() {
            return left;
        }

        public void setLeft(int left) {
            this.left = left;
        }

        public int getTop() {
            return top;
        }

        public void setTop(int top) {
            this.top = top;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }

    public static class RawRectBean {
        /**
         * left : 0
         * top : 0
         * width : 284
         * height : 218
         */

        private int left;
        private int top;
        private int width;
        private int height;

        public int getLeft() {
            return left;
        }

        public void setLeft(int left) {
            this.left = left;
        }

        public int getTop() {
            return top;
        }

        public void setTop(int top) {
            this.top = top;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }

    public static class FacesBean {
        /**
         * rect : {"left":63,"top":106,"width":138,"height":138}
         * attrs : {"age":28.66101,"pose":{"roll":-4.596921,"yaw":54.927704,"pitch":8.616277},"gender":{"male":0.97947836,"female":0.020521712},"face_quality":{"blurness":0.89164686},"acceptable":0.12068877,"gender_type":0}
         * quality : 0.12068877
         * confidence : 0.9999318
         */

        private RectBean rect;
        private AttrsBean attrs;
        private double quality;
        private double confidence;

        public RectBean getRect() {
            return rect;
        }

        public void setRect(RectBean rect) {
            this.rect = rect;
        }

        public AttrsBean getAttrs() {
            return attrs;
        }

        public void setAttrs(AttrsBean attrs) {
            this.attrs = attrs;
        }

        public double getQuality() {
            return quality;
        }

        public void setQuality(double quality) {
            this.quality = quality;
        }

        public double getConfidence() {
            return confidence;
        }

        public void setConfidence(double confidence) {
            this.confidence = confidence;
        }

        public static class RectBean {
            /**
             * left : 63
             * top : 106
             * width : 138
             * height : 138
             */

            private int left;
            private int top;
            private int width;
            private int height;

            public int getLeft() {
                return left;
            }

            public void setLeft(int left) {
                this.left = left;
            }

            public int getTop() {
                return top;
            }

            public void setTop(int top) {
                this.top = top;
            }

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }
        }

        public static class AttrsBean {
            /**
             * age : 28.66101
             * pose : {"roll":-4.596921,"yaw":54.927704,"pitch":8.616277}
             * gender : {"male":0.97947836,"female":0.020521712}
             * face_quality : {"blurness":0.89164686}
             * acceptable : 0.12068877
             * gender_type : 0
             */

            private double age;
            private PoseBean pose;
            private GenderBean gender;
            private FaceQualityBean face_quality;
            private double acceptable;
            private int gender_type;

            public double getAge() {
                return age;
            }

            public void setAge(double age) {
                this.age = age;
            }

            public PoseBean getPose() {
                return pose;
            }

            public void setPose(PoseBean pose) {
                this.pose = pose;
            }

            public GenderBean getGender() {
                return gender;
            }

            public void setGender(GenderBean gender) {
                this.gender = gender;
            }

            public FaceQualityBean getFace_quality() {
                return face_quality;
            }

            public void setFace_quality(FaceQualityBean face_quality) {
                this.face_quality = face_quality;
            }

            public double getAcceptable() {
                return acceptable;
            }

            public void setAcceptable(double acceptable) {
                this.acceptable = acceptable;
            }

            public int getGender_type() {
                return gender_type;
            }

            public void setGender_type(int gender_type) {
                this.gender_type = gender_type;
            }

            public static class PoseBean {
                /**
                 * roll : -4.596921
                 * yaw : 54.927704
                 * pitch : 8.616277
                 */

                private double roll;
                private double yaw;
                private double pitch;

                public double getRoll() {
                    return roll;
                }

                public void setRoll(double roll) {
                    this.roll = roll;
                }

                public double getYaw() {
                    return yaw;
                }

                public void setYaw(double yaw) {
                    this.yaw = yaw;
                }

                public double getPitch() {
                    return pitch;
                }

                public void setPitch(double pitch) {
                    this.pitch = pitch;
                }
            }

            public static class GenderBean {
                /**
                 * male : 0.97947836
                 * female : 0.020521712
                 */

                private double male;
                private double female;

                public double getMale() {
                    return male;
                }

                public void setMale(double male) {
                    this.male = male;
                }

                public double getFemale() {
                    return female;
                }

                public void setFemale(double female) {
                    this.female = female;
                }
            }

            public static class FaceQualityBean {
                /**
                 * blurness : 0.89164686
                 */

                private double blurness;

                public double getBlurness() {
                    return blurness;
                }

                public void setBlurness(double blurness) {
                    this.blurness = blurness;
                }
            }
        }
    }
}
