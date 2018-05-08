package ark.clanner.juststudent.utils;

import ark.clanner.juststudent.entity.msg.BaseMsg;
import ark.clanner.juststudent.entity.msg.LinkMessage;
import ark.clanner.juststudent.entity.msg.MsgType;
import ark.clanner.juststudent.entity.msg.TextMessage;
import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Clanner on 2018/5/3.
 * 消息处理工具
 */
public class MessageUtil {
    /**
     * 将微信的请求中参数转成map
     *
     * @param request
     * @return
     */
    public static Map<String, String> xml2Map(HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();
        SAXReader reader = new SAXReader();
        InputStream in = null;
        try {
            in = request.getInputStream();
            Document doc = reader.read(in);
            Element root = doc.getRootElement();
            List<Element> list = root.elements();
            for (Element element : list) {
                map.put(element.getName(), element.getText());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    public static String message2Xml(BaseMsg message) {
        XStream xstream = new XStream();
        xstream.alias("xml", message.getClass());
        return xstream.toXML(message);
    }

    public static String initTextMessage(String to, String from, String Content) {
        TextMessage text = new TextMessage();
        text.setToUserName(to);
        text.setFromUserName(from);
        text.setContent(Content);
        text.setCreateTime(new Date().getTime());
        text.setMsgType(MsgType.TEXT);
        return message2Xml(text);
    }

    public static String initLinkMessage(String to, String from, String Title, String Url, Integer MsgId) {
        LinkMessage link = new LinkMessage();
        link.setToUserName(to);
        link.setFromUserName(from);
        link.setCreateTime(new Date().getTime());
        link.setDescription(Url);
        link.setTitle(Title);
        link.setUrl(Url);
        link.setMsgId(MsgId);
        link.setMsgType(MsgType.LINK);
        return message2Xml(link);
    }
}
