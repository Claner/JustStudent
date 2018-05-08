package ark.clanner.juststudent.service;

import ark.clanner.juststudent.dao.LinkMessageDao;
import ark.clanner.juststudent.dao.MaterialDao;
import ark.clanner.juststudent.dao.TextMessageDao;
import ark.clanner.juststudent.entity.DO.ArticleDO;
import ark.clanner.juststudent.entity.DO.LinkMessageDO;
import ark.clanner.juststudent.entity.DO.TextMessageDO;
import ark.clanner.juststudent.utils.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

/**
 * Created by Clanner on 2018/5/5.
 */
@Service
public class MessageService {

    private static final int SIZE = 5;
    private static final String GET_MESSAGE = "获取用户消息";
    private static final String ABOUT = "关于系列";
    private static final String TALK = "聊聊系列";
    private static final String NIGHT_STORY = "晚安故事";
    private static final String ROAD = "套路合辑";
    private static final String NEXT_YEAR = "明年今日";
    private static final String myWord = "C同学正在努力开发明年今日，请期待哟";
    private static final String Clanner = "oNxnht9xI3R1LKB4aFyQSgFfZIf0";
    private static final String Wang = "oNxnht3F6yglpyt04meG78qBtLNo";
    private static final String LINK_HEAD = "https://mp.weixin.qq.com/s/";
    @Autowired
    private MaterialDao materialDao;
    @Autowired
    private TextMessageDao textMessageDao;
    @Autowired
    private LinkMessageDao linkMessageDao;

    public void analyseTextMessage(String FromUserName, String ToUserName, String Content, PrintWriter out) {
        List<ArticleDO> originalList = null;
        switch (Content) {
            case GET_MESSAGE:
                if (FromUserName.equals(Clanner) || FromUserName.equals(Wang)) {
                    List<TextMessageDO> messages = getTop5Message();
                    if (messages.size() > 0) {
                        StringBuffer buffer = new StringBuffer();
                        for (int i = messages.size(), j = 0; i > 0; j++) {
                            buffer.append("第" + (j + 1) + "条：");
                            buffer.append(messages.get(--i).getContent());
                            buffer.append("\n");
                        }
                        out.write(MessageUtil.initTextMessage(FromUserName, ToUserName, buffer.toString()));
                        buffer.setLength(0);
                    } else {
                        out.write(MessageUtil.initTextMessage(FromUserName, ToUserName, "暂无消息"));
                    }
                } else {
                    textMessageDao.save(new TextMessageDO(FromUserName, (int) new Date().getTime(), Content));
                    out.write(MessageUtil.initTextMessage(FromUserName, ToUserName, "C同学收到您的消息啦"));
                }
                break;
            case ABOUT:
                originalList = materialDao.findAllByType(ABOUT);
                break;
            case NIGHT_STORY:
                originalList = materialDao.findAllByType(NIGHT_STORY);
                break;
            case TALK:
                originalList = materialDao.findAllByType(TALK);
                break;
            case ROAD:
                originalList = materialDao.findAllByType(ROAD);
                break;
            case NEXT_YEAR:
                out.write(MessageUtil.initTextMessage(FromUserName, ToUserName, myWord));
                break;
            default:
                textMessageDao.save(new TextMessageDO(FromUserName, (int) new Date().getTime(), Content));
                out.write(MessageUtil.initTextMessage(FromUserName, ToUserName, "C同学收到您的消息啦"));
                break;
        }
        if (originalList != null && originalList.size() > 0) {
            StringBuffer buffer = new StringBuffer();
            for (int i = originalList.size(); i > 0; ) {
                buffer.append(originalList.get(--i).getTitle() + "：");
                buffer.append(LINK_HEAD + originalList.get(i).getUrl());
                buffer.append("\n");
            }
            out.write(MessageUtil.initTextMessage(FromUserName, ToUserName, buffer.toString()));
            buffer.setLength(0);
        }
    }

    public void addLinkMessage(String FromUserName, String ToUserName, String Title, String Description, String Url, PrintWriter out) {
        try {
            LinkMessageDO link = new LinkMessageDO();
            link.setFromUsername(FromUserName);
            link.setCreateTime((int) new Date().getTime());
            link.setDescription(Description);
            link.setTitle(Title);
            link.setUrl(Url);
            linkMessageDao.save(link);
            out.write(MessageUtil.initTextMessage(FromUserName, ToUserName, "C同学收到你的链接啦"));
        } catch (Exception e) {
            out.write(MessageUtil.initTextMessage(FromUserName, ToUserName, "发生了一点小意外，C同学丢失了您的链接"));
        }
    }

    public List<TextMessageDO> getTop5Message() {
        Pageable pageable = new PageRequest(textMessageDao.getCount() / SIZE - 1, SIZE);
        Page<TextMessageDO> messagePage = textMessageDao.findAll(pageable);
        List<TextMessageDO> messages = messagePage.getContent();
        return messages;
    }
}
