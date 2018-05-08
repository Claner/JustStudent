package ark.clanner.juststudent.controller.admin;

import ark.clanner.juststudent.base.BaseController;
import ark.clanner.juststudent.base.Response;
import ark.clanner.juststudent.entity.DO.TextMessageDO;
import ark.clanner.juststudent.entity.DTO.ArticleDTO;
import ark.clanner.juststudent.service.AdminService;
import ark.clanner.juststudent.service.MaterialService;
import ark.clanner.juststudent.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Clanner on 2018/5/5.
 * 管理员
 */
@RestController
@Scope("session")
@RequestMapping("/Admin")
public class AdminController extends BaseController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private MaterialService materialService;

    @Autowired
    private MessageService messageService;

    @PostMapping("/login")
    public Response login(String account, String pwd, String from,HttpServletResponse response) {
        if (adminService.findAdmin(account, pwd, from, response)) {
            return success("登陆成功");
        } else {
            return failure("用户名或密码错误");
        }
    }

    @PostMapping("/modifyPwd")
    public Response modifyPwd(String nPwd, String account, String oPwd) {
        if (adminService.updatePwd(nPwd, account, oPwd)) {
            return success("修改密码成功");
        } else {
            return failure("修改密码失败");
        }
    }

    @PostMapping("/addMaterial")
    public Response addMaterial(String type, String title, String url) {
        if (materialService.addMaterial(type, title, url)) {
            return success("添加素材成功");
        } else {
            return failure("添加素材失败");
        }
    }

    @PostMapping("/deleteMaterial")
    public Response deleteMaterial(int id) {
        if (materialService.deleteMaterial(id)) {
            return success("删除素材成功");
        } else {
            return failure("删除素材失败");
        }
    }

    @PostMapping("/modifyMaterial")
    public Response modifyMaterial(int id, String type, String title, String url) {
        if (materialService.updateMaterial(id, type, title, url)) {
            return success("修改素材成功");
        } else {
            return failure("修改素材失败");
        }
    }

    //获取所有素材
    @GetMapping("/getAllMaterial")
    public Response getAllMaterial() {
        List<ArticleDTO> articles = materialService.getAllMaterial();
        if (articles != null && articles.size() > 0) {
            return success("获取素材成功", articles);
        } else {
            return failure("暂无素材");
        }
    }


    @GetMapping("/getMessage")
    public Response getMessage() {
        List<TextMessageDO> list = messageService.getTop5Message();
        if (list != null && list.size() > 0) {
            return success("获取消息成功", list);
        } else {
            return failure("暂无消息");
        }
    }
}
