package ark.clanner.juststudent.controller;

import ark.clanner.juststudent.base.BaseController;
import ark.clanner.juststudent.base.Response;
import ark.clanner.juststudent.entity.DO.TextMessageDO;
import ark.clanner.juststudent.entity.msg.MsgType;
import ark.clanner.juststudent.service.MessageService;
import ark.clanner.juststudent.utils.EncodeUtil;
import ark.clanner.juststudent.utils.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by Clanner on 2018/5/2.
 */
@RestController
@Scope("session")
public class HomeController extends BaseController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/Home")
    public void check(HttpServletRequest request, HttpServletResponse response) {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            if (checkSignature(signature, timestamp, nonce)) {
                out.write(echostr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }

    @PostMapping(value = "/Home")
    public void dopost(HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding("utf-8");
        PrintWriter out = null;
        //将微信请求xml转为map格式，获取所需的参数
        Map<String, String> map = MessageUtil.xml2Map(request);
        String ToUserName = map.get("ToUserName");
        String FromUserName = map.get("FromUserName");
        String type = map.get("MsgType");

        String message;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        switch (type) {
            case MsgType.TEXT:
                messageService.analyseTextMessage(FromUserName, ToUserName, map.get("Content"), out);
                break;
            case MsgType.IMAGE:
                out.write(MessageUtil.initTextMessage(FromUserName, ToUserName, "图片"));
                break;
            case MsgType.VOICE:
                out.write(MessageUtil.initTextMessage(FromUserName, ToUserName, "音频"));
                break;
            case MsgType.VIDEO:
                out.write(MessageUtil.initTextMessage(FromUserName, ToUserName, "视频"));
                break;
            case MsgType.SHORT_VIDEO:
                out.write(MessageUtil.initTextMessage(FromUserName, ToUserName, "小视频"));
                break;
            case MsgType.LOCATION:
                out.write(MessageUtil.initTextMessage(FromUserName, ToUserName, "位置"));
                break;
            case MsgType.LINK:
                messageService.addLinkMessage(FromUserName, ToUserName, map.get("Title"), map.get("Description"), map.get("Url"), out);
                break;
            default:
                message = "未知类型的消息，C同学不知道怎么回复了，这时候夸你长得好看一定没错";
                out.write(MessageUtil.initTextMessage(FromUserName, ToUserName, message));
                break;
        }
        out.close();
    }

    private boolean checkSignature(String signature, String timestamp, String nonce) {
        String[] str = new String[]{EncodeUtil.TOKEN, timestamp, nonce};
        Arrays.sort(str);
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < str.length; i++) {
            buffer.append(str[i]);
        }
        String temp = EncodeUtil.SHA1(buffer.toString());
        return signature.equals(temp);
    }
}
