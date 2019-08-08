package com.lc.vo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextVo {

    /**
     * displayStyle : {"type":"PAGE_SWITCH","singleLine":false,"pageSwitchAttributes":{"inAnimation":{"type":0,"duration":1000},"remainDuration":5000},"scrollAttributes":{"effects":{"animation":"MARQUEE_UP","speed":1,"speedByPixelEnable":false,"isHeadTail":false,"headTailSpacing":"100"}}}
     * textAttributes : [{"key":1,"attributes":{"backgroundColor":"#00ff00","textColor":"#ff0000","font":{"family":["Times"],"style":"Bold","size":14,"isUnderline":false},"shadowEnable":false,"shadowRadius":10,"shadowDx":2,"shadowDy":2,"shadowColor":"#00ff00"}},{"key":2,"attributes":{}}]
     * autoPaging : true
     * paragraphs : [{"verticalAlignment":"TOP","horizontalAlignment":"LEFT","backgroundColor":"#00ff00","lineSpacing":2,"letterspacing":0,"lines":[{"segs":[{"attributeKey":1,"content":"南京梅花山是中国四大梅区之"},{"attributeKey":2,"content":"梅花山贫梅面积扩大了"}]}]}]
     * backgroundMusic : {"id":1,"name":"1.mp3","dataSource":"https://novacloud-test.oss-cn-hangzhou.aliyuncs.com/5d6e717884d05f28d4b8clc644e303e6.mp3","duration":1009,"isTextSync":true}
     */

    /**
     * 显示风格
     */
    private DisplayStyle displayStyle = new DisplayStyle();

    /**
     * 是否自动分页显示，false表示只显示能显示下的部分
     */
    private boolean autoPaging = true;

    /**
     * 文字背景音乐
     */
    private BackgroundMusic backgroundMusic = new BackgroundMusic();

    /**
     * 文本属性列表
     */
    private List<TextAttributes> textAttributes = new ArrayList<TextAttributes>();
    private List<Paragraphs> paragraphs = new ArrayList<Paragraphs>();

    public DisplayStyle getDisplayStyle() {
        return displayStyle;
    }

    public void setDisplayStyle(DisplayStyle displayStyle) {
        this.displayStyle = displayStyle;
    }

    public boolean isAutoPaging() {
        return autoPaging;
    }

    public void setAutoPaging(boolean autoPaging) {
        this.autoPaging = autoPaging;
    }

    public BackgroundMusic getBackgroundMusic() {
        return backgroundMusic;
    }

    public void setBackgroundMusic(BackgroundMusic backgroundMusic) {
        this.backgroundMusic = backgroundMusic;
    }

    public List<TextAttributes> getTextAttributes() {
        TextAttributes text = new TextAttributes();
        textAttributes.add(text);
        return textAttributes;
    }

    public void setTextAttributes(List<TextAttributes> textAttributes) {
        this.textAttributes = textAttributes;
    }

    public List<Paragraphs> getParagraphs() {
        Paragraphs para = new Paragraphs();
        paragraphs.add(para);
        return paragraphs;
    }

    public void setParagraphs(List<Paragraphs> paragraphs) {
        this.paragraphs = paragraphs;
    }

    /**
     * 显示风格
     */
    public static class DisplayStyle {
        /**
         * type : PAGE_SWITCH
         * singleLine : false
         * pageSwitchAttributes : {"inAnimation":{"type":0,"duration":1000},"remainDuration":5000}
         * scrollAttributes : {"effects":{"animation":"MARQUEE_UP","speed":1,"speedByPixelEnable":false,"isHeadTail":false,"headTailSpacing":"100"}}
         */

        /**
         * PAGE_SWITCH or SCROLL or STATIC 表示按照页切换、滚动模式、静止模式（若文字内容大于屏幕，仅显示第一屏）
         */
        private String type = "PAGE_SWITCH";
        /**
         * 文字是否单行显示
         */
        private boolean singleLine = false;
        /**
         * 按照页切换的属性
         */
        private PageSwitchAttributes pageSwitchAttributes = new PageSwitchAttributes();
        private ScrollAttributes scrollAttributes = new ScrollAttributes();

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public boolean isSingleLine() {
            return singleLine;
        }

        public void setSingleLine(boolean singleLine) {
            this.singleLine = singleLine;
        }

        public PageSwitchAttributes getPageSwitchAttributes() {
            return pageSwitchAttributes;
        }

        public void setPageSwitchAttributes(PageSwitchAttributes pageSwitchAttributes) {
            this.pageSwitchAttributes = pageSwitchAttributes;
        }

        public ScrollAttributes getScrollAttributes() {
            return scrollAttributes;
        }

        public void setScrollAttributes(ScrollAttributes scrollAttributes) {
            this.scrollAttributes = scrollAttributes;
        }

        /**
         * 按照页切换的属性
         */
        public static class PageSwitchAttributes {
            /**
             * inAnimation : {"type":0,"duration":1000}
             * remainDuration : 5000
             */

            /**
             * 入场动画
             */
            private InAnimation inAnimation = new InAnimation();

            /**
             * 保留时间，单位 毫秒
             */
            private int remainDuration = 5000;

            public InAnimation getInAnimation() {
                return inAnimation;
            }

            public void setInAnimation(InAnimation inAnimation) {
                this.inAnimation = inAnimation;
            }

            public int getRemainDuration() {
                return remainDuration;
            }

            public void setRemainDuration(int remainDuration) {
                this.remainDuration = remainDuration;
            }

            public static class InAnimation {
                /**
                 * type : 0
                 * duration : 1000
                 */

                /**
                 * 入场动画类型
                 */
                private int type = 0;

                /**
                 * 特效时间，单位毫秒
                 */
                private int duration = 1000;

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }

                public int getDuration() {
                    return duration;
                }

                public void setDuration(int duration) {
                    this.duration = duration;
                }
            }
        }

        /**
         * 滚动属性描述
         */
        public static class ScrollAttributes {
            /**
             * effects : {"animation":"MARQUEE_UP","speed":1,"speedByPixelEnable":false,"isHeadTail":false,"headTailSpacing":"100"}
             */

            /**
             * 滚动特效描述
             */
            private Effects effects = new Effects();

            public Effects getEffects() {
                return effects;
            }

            public void setEffects(Effects effects) {
                this.effects = effects;
            }

            /**
             * 滚动特效描述
             */
            public static class Effects {
                /**
                 * animation : MARQUEE_UP
                 * speed : 1
                 * speedByPixelEnable : false
                 * isHeadTail : false
                 * headTailSpacing : 100
                 */

                /**
                 * MARQUEE_LEFT or MARQUEE_UP
                 */
                private String animation ="MARQUEE_UP";

                /**
                 * 速度,单位像素/s或者档位（1-10）
                 */
                private int speed = 1;

                /**
                 * 如果为true则是像素每秒，false为档位
                 */
                private boolean speedByPixelEnable = false;

                /**
                 * 是否开启首尾相接
                 */
                private boolean isHeadTail = false;

                /**
                 * 首尾相接字间距，单位像素或者（屏幕百分比%）
                 */
                private String headTailSpacing = "100";

                public String getAnimation() {
                    return animation;
                }

                public void setAnimation(String animation) {
                    this.animation = animation;
                }

                public int getSpeed() {
                    return speed;
                }

                public void setSpeed(int speed) {
                    this.speed = speed;
                }

                public boolean isSpeedByPixelEnable() {
                    return speedByPixelEnable;
                }

                public void setSpeedByPixelEnable(boolean speedByPixelEnable) {
                    this.speedByPixelEnable = speedByPixelEnable;
                }

                public boolean isIsHeadTail() {
                    return isHeadTail;
                }

                public void setIsHeadTail(boolean isHeadTail) {
                    this.isHeadTail = isHeadTail;
                }

                public String getHeadTailSpacing() {
                    return headTailSpacing;
                }

                public void setHeadTailSpacing(String headTailSpacing) {
                    this.headTailSpacing = headTailSpacing;
                }
            }
        }
    }

    public static class BackgroundMusic {
        /**
         * id : 1
         * name : 1.mp3
         * dataSource : https://novacloud-test.oss-cn-hangzhou.aliyuncs.com/5d6e717884d05f28d4b8clc644e303e6.mp3
         * duration : 1009
         * isTextSync : true
         */

        /**
         *  音频id
         */
        private int id ;

        /**
         * 音频名称
         */
        private String name;

        /**
         * 音频来源
         */
        private String dataSource;

        /**
         * 音频时长
         */
        private int duration;

        /**
         * 音频与文字是否同步
         */
        private boolean isTextSync;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDataSource() {
            return dataSource;
        }

        public void setDataSource(String dataSource) {
            this.dataSource = dataSource;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public boolean isIsTextSync() {
            return isTextSync;
        }

        public void setIsTextSync(boolean isTextSync) {
            this.isTextSync = isTextSync;
        }
    }


    /**
     * 文本属性列表
     */
    public static class TextAttributes {
        /**
         * key : 1
         * attributes : {"backgroundColor":"#00ff00","textColor":"#ff0000","font":{"family":["Times"],"style":"Bold","size":14,"isUnderline":false},"shadowEnable":false,"shadowRadius":10,"shadowDx":2,"shadowDy":2,"shadowColor":"#00ff00"}
         */

        private int key = 1;
        private Attributes attributes = new Attributes();

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public Attributes getAttributes() {
            return attributes;
        }

        public void setAttributes(Attributes attributes) {
            this.attributes = attributes;
        }

        public static class Attributes {
            /**
             * backgroundColor : #00ff00
             * textColor :
             * font : {"family":["Times"],"style":"Bold","size":14,"isUnderline":false}
             * shadowEnable : false
             * shadowRadius : 10
             * shadowDx : 2
             * shadowDy : 2
             * shadowColor : #00ff00
             */

            private String backgroundColor ="#00ff00";
            private String textColor ="#ff0000";
            private Font font = new Font();
            private boolean shadowEnable = false;
            private int shadowRadius = 10;
            private int shadowDx = 2;
            private int shadowDy =2;
            private String shadowColor = "#00ff00";

            public String getBackgroundColor() {
                return backgroundColor;
            }

            public void setBackgroundColor(String backgroundColor) {
                this.backgroundColor = backgroundColor;
            }

            public String getTextColor() {
                return textColor;
            }

            public void setTextColor(String textColor) {
                this.textColor = textColor;
            }

            public Font getFont() {
                return font;
            }

            public void setFont(Font font) {
                this.font = font;
            }

            public boolean isShadowEnable() {
                return shadowEnable;
            }

            public void setShadowEnable(boolean shadowEnable) {
                this.shadowEnable = shadowEnable;
            }

            public int getShadowRadius() {
                return shadowRadius;
            }

            public void setShadowRadius(int shadowRadius) {
                this.shadowRadius = shadowRadius;
            }

            public int getShadowDx() {
                return shadowDx;
            }

            public void setShadowDx(int shadowDx) {
                this.shadowDx = shadowDx;
            }

            public int getShadowDy() {
                return shadowDy;
            }

            public void setShadowDy(int shadowDy) {
                this.shadowDy = shadowDy;
            }

            public String getShadowColor() {
                return shadowColor;
            }

            public void setShadowColor(String shadowColor) {
                this.shadowColor = shadowColor;
            }

            public static class Font {
                /**
                 * family : ["Times"]
                 * style : Bold
                 * size : 14
                 * isUnderline : false
                 */

                private String style = "Bold";
                private int size = 14;
                private boolean isUnderline = false;
                private List<String> family = Arrays.asList("Times");

                public String getStyle() {
                    return style;
                }

                public void setStyle(String style) {
                    this.style = style;
                }

                public int getSize() {
                    return size;
                }

                public void setSize(int size) {
                    this.size = size;
                }

                public boolean isIsUnderline() {
                    return isUnderline;
                }

                public void setIsUnderline(boolean isUnderline) {
                    this.isUnderline = isUnderline;
                }

                public List<String> getFamily() {
                    return family;
                }

                public void setFamily(List<String> family) {
                    this.family = family;
                }
            }
        }
    }

    /**
     * 段落描述
     */
    public static class Paragraphs {
        /**
         * verticalAlignment : TOP
         * horizontalAlignment : LEFT
         * backgroundColor :
         * lineSpacing : 2
         * letterspacing : 0
         * lines : [{"segs":[{"attributeKey":1,"content":"南京梅花山是中国四大梅区之"},{"attributeKey":2,"content":"梅花山贫梅面积扩大了"}]}]
         */


        /**
         *  垂直方向对齐方式，分别为：BOTTOM, CENTER, TOP
         */
        private String verticalAlignment ="TOP";

        /**
         *  水平方向对齐方式，分别为：LEFT, CENTER, RIGHT
         */
        private String horizontalAlignment ="LEFT";

        /**
         * 段背景色
         */
        private String backgroundColor ="#00ff00";

        /**
         * 段行间距，单位像素
         */
        private int lineSpacing = 2;

        /**
         * 段字间距，单位像素
         */
        private int letterSpacing = 0;

        /**
         * 行的描述，每一行是按照换行符分割
         */
        private List<Lines> lines = new ArrayList<Lines>();



        public String getVerticalAlignment() {
            return verticalAlignment;
        }

        public void setVerticalAlignment(String verticalAlignment) {
            this.verticalAlignment = verticalAlignment;
        }

        public String getHorizontalAlignment() {
            return horizontalAlignment;
        }

        public void setHorizontalAlignment(String horizontalAlignment) {
            this.horizontalAlignment = horizontalAlignment;
        }

        public String getBackgroundColor() {
            return backgroundColor;
        }

        public void setBackgroundColor(String backgroundColor) {
            this.backgroundColor = backgroundColor;
        }

        public int getLineSpacing() {
            return lineSpacing;
        }

        public void setLineSpacing(int lineSpacing) {
            this.lineSpacing = lineSpacing;
        }

        public int getLetterSpacing() {
            return letterSpacing;
        }

        public void setLetterSpacing(int letterSpacing) {
            this.letterSpacing = letterSpacing;
        }

        public List<Lines> getLines() {
            Lines line = new Lines();
            lines.add(line);
            return lines;
        }

        public void setLines(List<Lines> lines) {
            this.lines = lines;
        }


        /**
         *  行的描述，每一行是按照换行符分割
         */
        public static class Lines {
            /**
             *  片段描述，由于一行文字可能有多个属性，片段表示多个属性的集合
             */
            private List<Segs> segs = new ArrayList<Segs>();


            public List<Segs> getSegs() {
                Segs seg = new Segs();
                segs.add(seg);
                return segs;
            }

            public void setSegs(List<Segs> segs) {
                this.segs = segs;
            }


            /**
             *  片段描述，由于一行文字可能有多个属性，片段表示多个属性的集合
             */
            public static class Segs {
                /**
                 * attributeKey : 1
                 * content : 南京梅花山是中国四大梅区之
                 */

                /**
                 *  对应的文本属性key
                 */
                private int attributeKey = 1;

                /**
                 *  要显示的内容
                 */
                private String content = "南京梅花山是中国四大梅区之";

                public int getAttributeKey() {
                    return attributeKey;
                }

                public void setAttributeKey(int attributeKey) {
                    this.attributeKey = attributeKey;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }
            }
        }
    }
}
