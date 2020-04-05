package io.rushb.allmq.ms.message;

/**
 * @author zxj<br>
 * 时间 2018/3/19 14:35
 * 说明 ...
 */
public final class KeyValueMessage extends Message {
    protected String key;


    public KeyValueMessage(String key, String data) {
        this.key = key;
        this.data = data;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return key + " -> " + data;
    }
}
