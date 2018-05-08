package ark.clanner.juststudent.entity.msg;

/**
 * Created by Clanner on 2018/5/3.
 * 文本消息
 */
public class TextMessage extends BaseMsg{
    private String Content;//文本消息内容

    private String MsgId;//消息id，64位整型

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getMsgId() {
        return MsgId;
    }

    public void setMsgId(String msgId) {
        MsgId = msgId;
    }
}
