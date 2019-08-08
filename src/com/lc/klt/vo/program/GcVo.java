package com.lc.klt.vo.program;

/**
 * 工程属性
 */
public class GcVo {

    /**
     * Programs : {"Program":{"Information":{"Width":"256","Height":"256"},"Pages":[{"Name":"program 1","AppointDuration":"3600000","LoopType":"1","Regions":[{"Name":"file 1","type":"1","Layer":1,"Rect":{"X":"0","Y":"0","Width":"128","Height":"128","BorderWidth":"0","BorderColor":"0xFFFFFF00"},"Items":[{"Type":"2","FileSource":{"IsRelative":"1","FilePath":".\\two_pics.files\\12638.jpg"},"Duration":"2500","inEffect":{"Type":"2","Time":"1500"}},{"Type":"2","Duration":"2500","inEffect":{"Type":"2","Time":"1500"},"FileSource":{"IsRelative":"1","FilePath":".\\two_pics.files\\13254.png"}}]}],"BgColor":"0xFF000000","BgFile":{"IsRelative":"0"}},{"Regions":[{"Layer":1,"Rect":{"X":"0","Y":"0","Width":"128","Height":"64"},"Items":[{"Type":"5","Text":"hello","IsScroll":"1","LogFont":{"lfHeight":"36","lfWidth":"0"}}]},{"Layer":2,"Rect":{"X":"0","Y":"64","Width":"128","Height":"64"},"Items":[{"Type":"5","Text":"Multi lines.","LogFont":{"lfHeight":"36","lfWidth":"0"}}]}]},{"AppointDuration":"3600000","LoopType":"1","BgColor":"0xFF000000","BgFile":{"IsRelative":"0"},"Regions":[{"Layer":"1","Rect":{"X":"0","Y":"0","Width":"64","Height":"32","BorderWidth":"0","BorderColor":"0xFFFF0080"},"Items":[{"Name":"Multiple line text.","Type":"5","Version":"0x00000001","BackColor":"black","Alpha":"1.000000","Duration":"94480","BeGlaring":"0","MultiPicInfo":{"OnePicDuration":"6000"},"TextColor":"0xFFFFFFFF","LogFont":{"lfHeight":"40","lfWeight":"0","lfItalic":"0","lfUnderline":"0","lfStrikeOut":"0","lfFaceName":"宋体"},"RepeatCount":"1","Speed":"60.000000","IsHeadConnectTail":"1","IsScroll":"0","PlayLenth":"300000","lineSpacingExtra":0,"lineSpacingMultiplier":0.85,"VerticalAlign":0,"CenteralAlign":0,"Text":"12345崔"}]}]},{"Regions":[{"Layer":1,"Rect":{"X":"0","Y":"0","Width":"256","Height":"256","BorderWidth":"0","BorderColor":"0xFFFFFF00"},"Items":[{"Type":"3","Volume":"1.000000","FileSource":{"IsRelative":"1","FilePath":".\\video.files\\1.mp4"},"ReserveAS":"0"}]}]}]}}
     */

    private Program Program;

    public GcVo(){

    }
    public GcVo(Program pro){
        this.Program = pro ;
    }
    public Program getPrograms() {
        return Program;
    }
    public void setPrograms(Program Program) {
        this.Program = Program;
    }

}
