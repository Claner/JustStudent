package ark.clanner.juststudent.entity.msg;

/**
 * Created by Clanner on 2018/5/3.
 * 微信消息基类
 */
public class BaseMsg {
    //开发者账号
    protected String ToUserName;
    //发送方帐号（一个OpenID）
    protected String FromUserName;
    //消息创建时间 （整型）
    protected long CreateTime;
    //消息类型
    protected String MsgType;

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(long createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }
}
