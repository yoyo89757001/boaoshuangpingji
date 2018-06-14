package com.xiaojun.boaoshuangpingji.beans;

import java.util.List;

/**
 * Created by Administrator on 2018/6/12.
 */

public class TeZhengBean {


    /**
     * image_id : ws5ftvdlbUxknODz3IBVPQ==
     * request_id : 1528789609,c3a7cfc0-6516-433d-8f64-7ec43f18ad86
     * time_used : 225
     * faces : [{"attributes":{"emotion":{"sadness":9.468,"neutral":90.448,"disgust":0.001,"anger":0.001,"surprise":0.012,"fear":0.001,"happiness":0.069},"gender":{"value":"Male"},"age":{"value":38},"eyestatus":{"left_eye_status":{"normal_glass_eye_open":0.09,"no_glass_eye_close":0.113,"occlusion":0.061,"no_glass_eye_open":99.731,"normal_glass_eye_close":0.003,"dark_glasses":0},"right_eye_status":{"normal_glass_eye_open":0.032,"no_glass_eye_close":0.113,"occlusion":0.038,"no_glass_eye_open":99.805,"normal_glass_eye_close":0.011,"dark_glasses":0.001}},"glass":{"value":"None"},"ethnicity":{"value":"ASIAN"}},"face_rectangle":{"width":268,"top":0,"left":-80,"height":268},"face_token":"a16897c39a96660f7551bfb241f4cf25"}]
     */

    private String image_id;
    private String request_id;
    private int time_used;
    private List<FacesBean> faces;

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public int getTime_used() {
        return time_used;
    }

    public void setTime_used(int time_used) {
        this.time_used = time_used;
    }

    public List<FacesBean> getFaces() {
        return faces;
    }

    public void setFaces(List<FacesBean> faces) {
        this.faces = faces;
    }

    public static class FacesBean {
        /**
         * attributes : {"emotion":{"sadness":9.468,"neutral":90.448,"disgust":0.001,"anger":0.001,"surprise":0.012,"fear":0.001,"happiness":0.069},"gender":{"value":"Male"},"age":{"value":38},"eyestatus":{"left_eye_status":{"normal_glass_eye_open":0.09,"no_glass_eye_close":0.113,"occlusion":0.061,"no_glass_eye_open":99.731,"normal_glass_eye_close":0.003,"dark_glasses":0},"right_eye_status":{"normal_glass_eye_open":0.032,"no_glass_eye_close":0.113,"occlusion":0.038,"no_glass_eye_open":99.805,"normal_glass_eye_close":0.011,"dark_glasses":0.001}},"glass":{"value":"None"},"ethnicity":{"value":"ASIAN"}}
         * face_rectangle : {"width":268,"top":0,"left":-80,"height":268}
         * face_token : a16897c39a96660f7551bfb241f4cf25
         */

        private AttributesBean attributes;
        private FaceRectangleBean face_rectangle;
        private String face_token;

        public AttributesBean getAttributes() {
            return attributes;
        }

        public void setAttributes(AttributesBean attributes) {
            this.attributes = attributes;
        }

        public FaceRectangleBean getFace_rectangle() {
            return face_rectangle;
        }

        public void setFace_rectangle(FaceRectangleBean face_rectangle) {
            this.face_rectangle = face_rectangle;
        }

        public String getFace_token() {
            return face_token;
        }

        public void setFace_token(String face_token) {
            this.face_token = face_token;
        }

        public static class AttributesBean {
            /**
             * emotion : {"sadness":9.468,"neutral":90.448,"disgust":0.001,"anger":0.001,"surprise":0.012,"fear":0.001,"happiness":0.069}
             * gender : {"value":"Male"}
             * age : {"value":38}
             * eyestatus : {"left_eye_status":{"normal_glass_eye_open":0.09,"no_glass_eye_close":0.113,"occlusion":0.061,"no_glass_eye_open":99.731,"normal_glass_eye_close":0.003,"dark_glasses":0},"right_eye_status":{"normal_glass_eye_open":0.032,"no_glass_eye_close":0.113,"occlusion":0.038,"no_glass_eye_open":99.805,"normal_glass_eye_close":0.011,"dark_glasses":0.001}}
             * glass : {"value":"None"}
             * ethnicity : {"value":"ASIAN"}
             */

            private EmotionBean emotion;
            private GenderBean gender;
            private AgeBean age;
            private EyestatusBean eyestatus;
            private GlassBean glass;
            private EthnicityBean ethnicity;

            public EmotionBean getEmotion() {
                return emotion;
            }

            public void setEmotion(EmotionBean emotion) {
                this.emotion = emotion;
            }

            public GenderBean getGender() {
                return gender;
            }

            public void setGender(GenderBean gender) {
                this.gender = gender;
            }

            public AgeBean getAge() {
                return age;
            }

            public void setAge(AgeBean age) {
                this.age = age;
            }

            public EyestatusBean getEyestatus() {
                return eyestatus;
            }

            public void setEyestatus(EyestatusBean eyestatus) {
                this.eyestatus = eyestatus;
            }

            public GlassBean getGlass() {
                return glass;
            }

            public void setGlass(GlassBean glass) {
                this.glass = glass;
            }

            public EthnicityBean getEthnicity() {
                return ethnicity;
            }

            public void setEthnicity(EthnicityBean ethnicity) {
                this.ethnicity = ethnicity;
            }

            public static class EmotionBean {
                /**
                 * sadness : 9.468
                 * neutral : 90.448
                 * disgust : 0.001
                 * anger : 0.001
                 * surprise : 0.012
                 * fear : 0.001
                 * happiness : 0.069
                 */

                private double sadness;
                private double neutral;
                private double disgust;
                private double anger;
                private double surprise;
                private double fear;
                private double happiness;

                public double getSadness() {
                    return sadness;
                }

                public void setSadness(double sadness) {
                    this.sadness = sadness;
                }

                public double getNeutral() {
                    return neutral;
                }

                public void setNeutral(double neutral) {
                    this.neutral = neutral;
                }

                public double getDisgust() {
                    return disgust;
                }

                public void setDisgust(double disgust) {
                    this.disgust = disgust;
                }

                public double getAnger() {
                    return anger;
                }

                public void setAnger(double anger) {
                    this.anger = anger;
                }

                public double getSurprise() {
                    return surprise;
                }

                public void setSurprise(double surprise) {
                    this.surprise = surprise;
                }

                public double getFear() {
                    return fear;
                }

                public void setFear(double fear) {
                    this.fear = fear;
                }

                public double getHappiness() {
                    return happiness;
                }

                public void setHappiness(double happiness) {
                    this.happiness = happiness;
                }
            }

            public static class GenderBean {
                /**
                 * value : Male
                 */

                private String value;

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }
            }

            public static class AgeBean {
                /**
                 * value : 38
                 */

                private int value;

                public int getValue() {
                    return value;
                }

                public void setValue(int value) {
                    this.value = value;
                }
            }

            public static class EyestatusBean {
                /**
                 * left_eye_status : {"normal_glass_eye_open":0.09,"no_glass_eye_close":0.113,"occlusion":0.061,"no_glass_eye_open":99.731,"normal_glass_eye_close":0.003,"dark_glasses":0}
                 * right_eye_status : {"normal_glass_eye_open":0.032,"no_glass_eye_close":0.113,"occlusion":0.038,"no_glass_eye_open":99.805,"normal_glass_eye_close":0.011,"dark_glasses":0.001}
                 */

                private LeftEyeStatusBean left_eye_status;
                private RightEyeStatusBean right_eye_status;

                public LeftEyeStatusBean getLeft_eye_status() {
                    return left_eye_status;
                }

                public void setLeft_eye_status(LeftEyeStatusBean left_eye_status) {
                    this.left_eye_status = left_eye_status;
                }

                public RightEyeStatusBean getRight_eye_status() {
                    return right_eye_status;
                }

                public void setRight_eye_status(RightEyeStatusBean right_eye_status) {
                    this.right_eye_status = right_eye_status;
                }

                public static class LeftEyeStatusBean {
                    /**
                     * normal_glass_eye_open : 0.09
                     * no_glass_eye_close : 0.113
                     * occlusion : 0.061
                     * no_glass_eye_open : 99.731
                     * normal_glass_eye_close : 0.003
                     * dark_glasses : 0.0
                     */

                    private double normal_glass_eye_open;
                    private double no_glass_eye_close;
                    private double occlusion;
                    private double no_glass_eye_open;
                    private double normal_glass_eye_close;
                    private double dark_glasses;

                    public double getNormal_glass_eye_open() {
                        return normal_glass_eye_open;
                    }

                    public void setNormal_glass_eye_open(double normal_glass_eye_open) {
                        this.normal_glass_eye_open = normal_glass_eye_open;
                    }

                    public double getNo_glass_eye_close() {
                        return no_glass_eye_close;
                    }

                    public void setNo_glass_eye_close(double no_glass_eye_close) {
                        this.no_glass_eye_close = no_glass_eye_close;
                    }

                    public double getOcclusion() {
                        return occlusion;
                    }

                    public void setOcclusion(double occlusion) {
                        this.occlusion = occlusion;
                    }

                    public double getNo_glass_eye_open() {
                        return no_glass_eye_open;
                    }

                    public void setNo_glass_eye_open(double no_glass_eye_open) {
                        this.no_glass_eye_open = no_glass_eye_open;
                    }

                    public double getNormal_glass_eye_close() {
                        return normal_glass_eye_close;
                    }

                    public void setNormal_glass_eye_close(double normal_glass_eye_close) {
                        this.normal_glass_eye_close = normal_glass_eye_close;
                    }

                    public double getDark_glasses() {
                        return dark_glasses;
                    }

                    public void setDark_glasses(double dark_glasses) {
                        this.dark_glasses = dark_glasses;
                    }
                }

                public static class RightEyeStatusBean {
                    /**
                     * normal_glass_eye_open : 0.032
                     * no_glass_eye_close : 0.113
                     * occlusion : 0.038
                     * no_glass_eye_open : 99.805
                     * normal_glass_eye_close : 0.011
                     * dark_glasses : 0.001
                     */

                    private double normal_glass_eye_open;
                    private double no_glass_eye_close;
                    private double occlusion;
                    private double no_glass_eye_open;
                    private double normal_glass_eye_close;
                    private double dark_glasses;

                    public double getNormal_glass_eye_open() {
                        return normal_glass_eye_open;
                    }

                    public void setNormal_glass_eye_open(double normal_glass_eye_open) {
                        this.normal_glass_eye_open = normal_glass_eye_open;
                    }

                    public double getNo_glass_eye_close() {
                        return no_glass_eye_close;
                    }

                    public void setNo_glass_eye_close(double no_glass_eye_close) {
                        this.no_glass_eye_close = no_glass_eye_close;
                    }

                    public double getOcclusion() {
                        return occlusion;
                    }

                    public void setOcclusion(double occlusion) {
                        this.occlusion = occlusion;
                    }

                    public double getNo_glass_eye_open() {
                        return no_glass_eye_open;
                    }

                    public void setNo_glass_eye_open(double no_glass_eye_open) {
                        this.no_glass_eye_open = no_glass_eye_open;
                    }

                    public double getNormal_glass_eye_close() {
                        return normal_glass_eye_close;
                    }

                    public void setNormal_glass_eye_close(double normal_glass_eye_close) {
                        this.normal_glass_eye_close = normal_glass_eye_close;
                    }

                    public double getDark_glasses() {
                        return dark_glasses;
                    }

                    public void setDark_glasses(double dark_glasses) {
                        this.dark_glasses = dark_glasses;
                    }
                }
            }

            public static class GlassBean {
                /**
                 * value : None
                 */

                private String value;

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }
            }

            public static class EthnicityBean {
                /**
                 * value : ASIAN
                 */

                private String value;

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }
            }
        }

        public static class FaceRectangleBean {
            /**
             * width : 268
             * top : 0
             * left : -80
             * height : 268
             */

            private int width;
            private int top;
            private int left;
            private int height;

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public int getTop() {
                return top;
            }

            public void setTop(int top) {
                this.top = top;
            }

            public int getLeft() {
                return left;
            }

            public void setLeft(int left) {
                this.left = left;
            }

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }
        }
    }
}
