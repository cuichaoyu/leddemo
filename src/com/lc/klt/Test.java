package com.lc.klt;

import com.lc.vo.LedVo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {
        List<LedVo> list = new ArrayList<>();
        list.add(new LedVo("192.168.100.1",100,100));
        list.add(new LedVo("192.168.100.2",200,200));
        Map<String, LedVo> map = list.stream().collect(Collectors.toMap(LedVo::getIp, a -> a,(k1, k2)->k1));
        System.out.println(map);

    }
}
