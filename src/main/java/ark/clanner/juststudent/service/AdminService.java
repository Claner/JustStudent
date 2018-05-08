package ark.clanner.juststudent.service;

import ark.clanner.juststudent.config.Constant;
import ark.clanner.juststudent.dao.AdminDao;
import ark.clanner.juststudent.entity.DO.AdminDO;
import ark.clanner.juststudent.utils.EncodeUtil;
import ark.clanner.juststudent.utils.JavaWebToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Clanner on 2018/5/5.
 * 管理员服务
 */
@Service
public class AdminService {

    @Autowired
    private AdminDao adminDao;

    /**
     * 查找管理员
     *
     * @param account  账号
     * @param pwd      密码
     * @param from     请求来源
     * @param response 用于设置cookie
     * @return
     */
    public boolean findAdmin(String account, String pwd, String from, HttpServletResponse response) {
        AdminDO admin = adminDao.findByAccountAndPwd(account, EncodeUtil.MD5(pwd));
        if (admin != null) {
            String token = JavaWebToken.createJWT("" + admin.getId(), Constant.ISSUER, from, System.currentTimeMillis(), JavaWebToken.ADMIN_KEY);
            Cookie cookie = new Cookie(Constant.JWT_KEY, token);
            cookie.setMaxAge(24 * 60 * 60);//单位为秒
            response.addCookie(cookie);
            Constant.ADMINS.put(Constant.ADMIN + admin.getId(), from);
            return true;
        } else {
            return false;
        }
    }

    //修改密码
    public boolean updatePwd(String nPwd, String account, String oPwd) {
        int success_row = adminDao.updatePwdByAccountAndPwd(EncodeUtil.MD5(Constant.START + nPwd + Constant.END), account, EncodeUtil.MD5(oPwd));
        return success_row > 0 ? true : false;
    }
}
