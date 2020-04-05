package io.rushb.allmq;

import java.util.HashMap;

/**
 * @author zxj<br>
 * 时间 2018/3/19 11:21
 * 说明 配置
 */
public class Configuration extends HashMap<String,Object> {

    public void add(String key,Object value){
        this.put(key,value);
    }
}
