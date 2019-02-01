package com.smepublish.demo.utils;

import cn.izern.sequence.Sequence;

/**
 * 〈IdUtils〉 id生成器
 *
 * @author mazhongrong@smeyun.com
 * @date 2019/1/29
 */
public class IdUtils {
    public static long getId() {
        Sequence sequence = new Sequence();
        return sequence.nextId();
    }
}
