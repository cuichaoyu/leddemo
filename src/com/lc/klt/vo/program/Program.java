package com.lc.klt.vo.program;

import com.lc.klt.vo.CommonVo;

import java.util.ArrayList;
import java.util.List;

/**
 * 节目属性
 */
public class Program {
    private Information Information;
    private List<Pages> Pages;

    public Program(){

    }
    public Program(Pages page){
        if(Information == null){
            Information = new Information();
        }
        if(Pages == null){
            Pages = new ArrayList<>();
        }
        Pages.add(page);
    }
    public Program(List<Pages> pages){
        this.Pages = pages ;
    }

    public Program.Information getInformation() {
        return Information;
    }

    public void setInformation(Program.Information information) {
        Information = information;
    }

    public List<Program.Pages> getPages() {
        return Pages;
    }

    public void setPages(List<Program.Pages> pages) {
        Pages = pages;
    }

    /**
     * 节目详细信息
     */
    public static class Information {
        /**
         * Width : 256
         * Height : 256
         */

        private String Width ; // 屏宽（像素点数）
        private String Height ; // 屏高
        private String Duration; //播放持续时间(毫秒)

        public Information() {
            super();
        }

        public Information(String width, String height, String duration) {
            Width = width;
            Height = height;
            Duration = duration;
        }

        public String getWidth() {
            return Width;
        }

        public void setWidth(String Width) {
            this.Width = Width;
        }

        public String getHeight() {
            return Height;
        }

        public void setHeight(String Height) {
            this.Height = Height;
        }

        public String getDuration() {
            return Duration;
        }

        public void setDuration(String duration) {
            Duration = duration;
        }
    }


    /**
     * 节目页
     */
    public static class Pages {
        /**
         * Name : program 1
         * AppointDuration : 3600000
         * LoopType : 1
         * Regions : [{"Name":"file 1","type":"1","Layer":1,"Rect":{"X":"0","Y":"0","Width":"128","Height":"128","BorderWidth":"0","BorderColor":"0xFFFFFF00"},"Items":[{"Type":"2","FileSource":{"IsRelative":"1","FilePath":".\\two_pics.files\\12638.jpg"}},{"Type":"2","Duration":"2500","inEffect":{"Type":"2","Time":"1500"},"FileSource":{"IsRelative":"1","FilePath":".\\two_pics.files\\13254.png"}}]}]
         * BgColor : 0xFF000000
         * BgFile : {"IsRelative":"0"}
         */

        private String Name; //节目页显示名称
        private String Visibale; //节目页是否显示(0:隐藏1:显示)
        private String AppointDuration;//如果下面的LoopType=0，就用这个参数。节目页播放时长(毫秒) 播放指定时长
        private String LoopType = "1"; //节目页 循环类型(0:播放指定时长 1:等待本节目页播完)
        private String BgColor; //节目页背景颜色
        private List<Regions> Regions;

        public Pages(){

        }

        public Pages(Regions regions){
            if(Regions == null){
                Regions = new ArrayList<>();
            }
            Regions.add(regions);
        }
        public Pages(List<Regions> regions){
            this.Regions = regions ;
        }


        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public String getVisibale() {
            return Visibale;
        }

        public void setVisibale(String visibale) {
            Visibale = visibale;
        }

        public String getAppointDuration() {
            return AppointDuration;
        }

        public void setAppointDuration(String appointDuration) {
            AppointDuration = appointDuration;
        }

        public String getLoopType() {
            return LoopType;
        }

        public void setLoopType(String loopType) {
            LoopType = loopType;
        }

        public String getBgColor() {
            return BgColor;
        }

        public void setBgColor(String bgColor) {
            BgColor = bgColor;
        }

        public List<Program.Pages.Regions> getRegions() {
            return Regions;
        }

        public void setRegions(List<Program.Pages.Regions> regions) {
            Regions = regions;
        }


        public static class Regions {
            /**
             * 0,//无效区域
             * 1,		//文件窗
             * 2,		//多行文本
             * 3,		//单行文本
             * 4,		//表格
             * 5,		//时钟
             * 6,		//计时
             * 7,		//外部视频
             * 8,		//数据库
             * 9,		//天气预报
             * 	10,		//外部程序窗
             */
            private String type ; //区域区域类型
            private String Name; //区域显示名称
            private int Layer = 1; //区域1显示层次（1为顶层，各区域依次向下罗列显示）
            private Rect Rect;
            private List<CommonVo> Items;

            public Regions(){

            }

            public Regions(CommonVo obj){
                if(Items == null){
                    Items = new ArrayList();
                }
                if(Rect == null){
                    Rect = new Rect();
                }
                Items.add(obj);
            }

            public Regions(Rect rect,CommonVo obj){
                if(Items == null){
                    Items = new ArrayList();
                }
                this.Rect = rect;
                Items.add(obj);
            }

            public Regions(Rect rect,List<CommonVo> objs){
                this.Rect = rect ;
                this.Items = objs;
            }
            public Regions(List<CommonVo> objs){
                if(Rect == null){
                    Rect = new Rect();
                }
                this.Items = objs;
            }
            public String getName() {
                return Name;
            }

            public void setName(String name) {
                Name = name;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public int getLayer() {
                return Layer;
            }

            public void setLayer(int layer) {
                Layer = layer;
            }

            public Rect getRect() {
                return Rect;
            }

            public void setRect(Rect rect) {
                Rect = rect;
            }

            public List getItems() {
                return Items;
            }

            public void setItems(List items) {
                Items = items;
            }
        }

    }

}
