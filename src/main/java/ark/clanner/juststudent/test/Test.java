package ark.clanner.juststudent.test;
import ark.clanner.juststudent.entity.view.Menu;
import ark.clanner.juststudent.service.MessageService;
import ark.clanner.juststudent.service.UserService;
import ark.clanner.juststudent.utils.EncodeUtil;
import ark.clanner.juststudent.utils.MenuUtil;
import ark.clanner.juststudent.utils.WXUtil;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;

/**
 * Created by Clanner on 2018/5/3.
 * 没有微信认证，搞不了
 */
public class Test {
    public static void main(String[] args) {
//        String accessToken = WXUtil.getAccessToken();
//        System.out.println(accessToken);
//        String menu = MenuUtil.initMenu();
//        int result = MenuUtil.createMenu(accessToken,menu);
//        if(result==0){
//            System.out.println("菜单创建成功");
//        }else{
//            System.out.println("错误码"+result);
//        }
        System.out.println(EncodeUtil.MD5("Clanner"+"123456"+"Wang"));
        System.out.println(EncodeUtil.MD5("Clanner123456Wang"));

    }
}
