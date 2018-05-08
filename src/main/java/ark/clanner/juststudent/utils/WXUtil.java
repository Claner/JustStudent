package ark.clanner.juststudent.utils;

import ark.clanner.juststudent.entity.AccessToken;
import ark.clanner.juststudent.entity.msg.ErrorMsg;
import com.google.gson.Gson;

/**
 * Created by Clanner on 2018/5/3.
 * 微信工具
 */
public class WXUtil {
    private static final String APPID = "wxf2e05e95e437818c";
    private static final String APPSECRET = "1d70855c52ceefa46a10f3f7627fcb5b";
    public static final String ACCESS_TOKEN_URL =
            "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + APPID + "&secret=" + APPSECRET;

    public static String getAccessToken() {
        String json = OKHttpUtil.get(ACCESS_TOKEN_URL);
        AccessToken accessToken = GsonUtil.getInstance().fromJson(json, AccessToken.class);
        ErrorMsg errorMsg = GsonUtil.getInstance().fromJson(json, ErrorMsg.class);
        if (errorMsg != null && errorMsg.getErrcode() != null) {
            switch (errorMsg.getErrcode()) {
                case -1:
                    System.out.println("系统繁忙，此时请开发者稍候再试," + errorMsg.getErrmsg());
                    break;
                case 0:
                    System.out.println("请求成功");
                    break;
                case 40001:
                    System.out.println("AppSecret错误或者AppSecret不属于这个公众号，请开发者确认AppSecret的正确性," + errorMsg.getErrmsg());
                    break;
                case 40002:
                    System.out.println("请确保grant_type字段值为client_credential," + errorMsg.getErrmsg());
                    break;
                case 40164:
                    System.out.println("调用接口的IP地址不在白名单中，请在接口IP白名单中进行设置," + errorMsg.getErrmsg());
                    break;

            }
        }
        return accessToken.getAccess_token();
    }
}
