package io.rushb.allmq.ms.message;

/**
 * @author zxj<br>
 * 时间 2018/3/19 14:34
 * 说明 ...
 */
public class Message {

    protected String data;

    public Message() {
    }

    public Message(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return data;
    }
}
