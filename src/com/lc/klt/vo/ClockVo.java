package com.lc.klt.vo;


/**
 * 时钟
 */
public class ClockVo extends CommonVo{
    /**
     * 时钟类型（0：数字时钟；1：模拟时钟）
     */
    private String IsAnolog = "0" ;
    private String Offset ; //时间偏差(秒)
    private String Text ; //固定显示的文字
    private DigtalClock DigtalClock ; // 数字时钟
    private AnologClock AnologClock ; // 模拟时钟


    public  ClockVo(){
        this.Type = "9";
    }

    /**
     * 时钟类型（0：数字时钟；1：模拟时钟）
     * 当 IsAnolog = 0 ，Flags 不能为空
     * 当 IsAnolog = 1，Flags 和  Shape 都不能为空
     * @param IsAnolog
     */
    public ClockVo(String IsAnolog,String Flags,String Shape){
        this.Type = "9";
        this.IsAnolog = IsAnolog ;
        if("0".equals(IsAnolog)){
            DigtalClock = new DigtalClock(Flags);
        }else{
            AnologClock = new AnologClock(Shape,Flags);
        }
    }




    public ClockVo.DigtalClock getDigtalClock() {
        return DigtalClock;
    }

    public void setDigtalClock(ClockVo.DigtalClock digtalClock) {
        DigtalClock = digtalClock;
    }

    public ClockVo.AnologClock getAnologClock() {
        return AnologClock;
    }

    public void setAnologClock(ClockVo.AnologClock anologClock) {
        AnologClock = anologClock;
    }

    public String getIsAnolog() {
        return IsAnolog;
    }

    public void setIsAnolog(String isAnolog) {
        IsAnolog = isAnolog;
    }

    public String getOffset() {
        return Offset;
    }

    public void setOffset(String offset) {
        Offset = offset;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    /**
     * 数字时钟属性
     */
    public static class DigtalClock{
        /**
         * 数字时钟标记
         *  0x0001,		//显示年
         * 0x0002,		//显示月
         * 0x0004,	//显示日
         * 	0x0008,		//显示时
         * 	0x0010,		//显示分
         * 	0x0020,		//显示秒
         * 	0x0040,		//显示农历的年
         * 	0x0080,		//显示农历的月
         * 	0x0100,		//显示农历的日
         * 0x0200,		//显示星期几
         * 0x0400,		//显示半日     2619    2623   2615
         * 0x0800,		//按24小时显示时间，否则按12小时显示时间
         * 0x1000,		//年按个数字显示，否则按个数字显示
         * 0x2000,		//是否多行显示，否则按单行显示
         */
        private String Flags ; //风格标志
        private String ftSize ; //字号
        private String ftColor ; //字符颜色，DWORD值。如：4294967295
        private String Name ; //字体名称


        public DigtalClock(){

        }

        /**
         *
         * @param Flags 数字时钟标记
         *          *  0x0001,		//显示年
         *          * 0x0002,		//显示月
         *          * 0x0004,	//显示日
         *          * 	0x0008,		//显示时
         *          * 	0x0010,		//显示分
         *          * 	0x0020,		//显示秒
         *          * 	0x0040,		//显示农历的年
         *          * 	0x0080,		//显示农历的月
         *          * 	0x0100,		//显示农历的日
         *          * 0x0200,		//显示星期几
         *          * 0x0400,		//显示半日     2619    2623   2615
         *          * 0x0800,		//按24小时显示时间，否则按12小时显示时间
         *          * 0x1000,		//年按个数字显示，否则按个数字显示
         *          * 0x2000,		//是否多行显示，否则按单行显示
         */
        public DigtalClock(String Flags){
            this.Flags = Flags ;
        }
        public String getFlags() {
            return Flags;
        }

        public void setFlags(String flags) {
            Flags = flags;
        }

        public String getFtSize() {
            return ftSize;
        }

        public void setFtSize(String ftSize) {
            this.ftSize = ftSize;
        }

        public String getFtColor() {
            return ftColor;
        }

        public void setFtColor(String ftColor) {
            this.ftColor = ftColor;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }
    }

    /**
     * 模拟时钟属性
     */
    public static class AnologClock{
        private String Shape ; //形状 ：0：圆形 ；1：方形
        /**
         * 模拟时钟标记
         *  0x10000,	//显示日期
         * 0x20000,	//显示农历
         * 0x40000,	//显示星期几
         */
        private String Flags ; //风格标志
        private String ftSize ; //字号
        private String ftColor ; //字符颜色，DWORD值。如：4294967295
        private String Name ; //字体名称


        public AnologClock(){

        }

        /**
         *
         * @param Shape 形状 ：0：圆形 ；1：方形
         * @param Flags 模拟时钟标记
         *              0x10000,	//显示日期
         *              0x20000,	//显示农历
         *              0x40000,	//显示星期几
         */
        public AnologClock(String Shape,String Flags){
            this.Flags = Flags ;
            this.Shape = Shape ;
        }




        public String getShape() {
            return Shape;
        }

        public void setShape(String shape) {
            Shape = shape;
        }

        public String getFlags() {
            return Flags;
        }

        public void setFlags(String flags) {
            Flags = flags;
        }

        public String getFtSize() {
            return ftSize;
        }

        public void setFtSize(String ftSize) {
            this.ftSize = ftSize;
        }

        public String getFtColor() {
            return ftColor;
        }

        public void setFtColor(String ftColor) {
            this.ftColor = ftColor;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }
    }
}
