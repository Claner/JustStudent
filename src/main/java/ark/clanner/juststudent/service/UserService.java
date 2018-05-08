package ark.clanner.juststudent.service;

import ark.clanner.juststudent.utils.OKHttpUtil;
import ark.clanner.juststudent.utils.WXUtil;
import org.springframework.stereotype.Service;

/**
 * Created by Clanner on 2018/5/4.
 * 用户服务
 */
@Service
public class UserService {
    private static final String USER_LIST_URL = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID";

    public static void getUserList(String accessToken) {
        String json = OKHttpUtil.get(USER_LIST_URL.replace("ACCESS_TOKEN", accessToken));
        System.out.println(json);
    }
}
