package com.lc.klt.vo.program;
/**
 * 进场特效
 */
public class InEffect {
    /**
     * Type : 2
     * Time : 1500
     */

    private String Type; // 0:无特效,1:随机,... 50
    private String Time; //进场时间(毫秒)
    private String repeatX; //水平方向重复次数（整数）
    private String repeatY; //垂直方向重复次数（整数）
    private String IsTran; //是否首尾衔接（0：否；1：是）

    public InEffect() {
        super();
    }

    public InEffect(String type, String time, String repeatX, String repeatY, String isTran) {
        this.Type = type;
        this.Time = time;
        this.repeatX = repeatX;
        this.repeatY = repeatY;
        this.IsTran = isTran;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String Time) {
        this.Time = Time;
    }

    public String getRepeatX() {
        return repeatX;
    }

    public void setRepeatX(String repeatX) {
        this.repeatX = repeatX;
    }

    public String getRepeatY() {
        return repeatY;
    }

    public void setRepeatY(String repeatY) {
        this.repeatY = repeatY;
    }

    public String getIsTran() {
        return IsTran;
    }

    public void setIsTran(String isTran) {
        IsTran = isTran;
    }
}
