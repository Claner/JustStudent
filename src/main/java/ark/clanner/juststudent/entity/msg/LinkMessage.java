package ark.clanner.juststudent.entity.msg;

/**
 * Created by Clanner on 2018/5/5.
 * 链接消息
 */
public class LinkMessage extends BaseMsg{
    private String Title;
    private String Description;
    private String Url;
    private int MsgId;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public int getMsgId() {
        return MsgId;
    }

    public void setMsgId(int msgId) {
        MsgId = msgId;
    }
}
