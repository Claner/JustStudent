package ark.clanner.juststudent.controller.admin;

import ark.clanner.juststudent.base.BaseController;
import ark.clanner.juststudent.base.Response;
import ark.clanner.juststudent.entity.DO.ArticleDO;
import ark.clanner.juststudent.entity.DTO.ArticleDTO;
import ark.clanner.juststudent.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Clanner on 2018/5/5.
 * 素材
 */
@RestController
@Scope("session")
@RequestMapping("/Material")
public class MaterialController extends BaseController {
    @Autowired
    private MaterialService materialService;

    @PostMapping("/getArticleByType")
    public Response getArticleByType(String type) {
        List<ArticleDTO> list = materialService.getMaterial(type);
        if (list != null && list.size() > 0) {
            return success("获取素材成功", list);
        } else {
            return failure("暂无素材");
        }
    }
}
