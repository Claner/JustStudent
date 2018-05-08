package ark.clanner.juststudent.service;

import ark.clanner.juststudent.dao.MaterialDao;
import ark.clanner.juststudent.entity.DO.ArticleDO;
import ark.clanner.juststudent.entity.DTO.ArticleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Clanner on 2018/5/5.
 * 素材服务
 */
@Service
public class MaterialService {

    @Autowired
    private MaterialDao materialDao;

    //添加文章
    public boolean addMaterial(String type, String title, String url) {
        ArticleDO article = new ArticleDO(type, title, url);
        try {
            materialDao.saveAndFlush(article);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //删除文章
    public boolean deleteMaterial(int id) {
        try {
            materialDao.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //修改文章
    public boolean updateMaterial(int id, String type, String title, String url) {
        int success_row = materialDao.updateArticleById(id, type, title, url);
        return success_row > 0 ? true : false;
    }

    //获取所有文章
    public List<ArticleDTO> getAllMaterial() {
        List<ArticleDO> originalList = materialDao.findAll();
        if (originalList == null || originalList.size() == 0) return null;
        List<ArticleDTO> list = new ArrayList<>(originalList.size());
        list.addAll(originalList.stream().map(articleDO -> new ArticleDTO(articleDO.getId(),
                articleDO.getType(), articleDO.getTitle(), articleDO.getUrl())).collect(Collectors.toList()));
        return list;
    }

    //根据type获取所有文章
    public List<ArticleDTO> getMaterial(String type) {
        List<ArticleDO> originalList = materialDao.findAllByType(type);
        if (originalList == null || originalList.size() == 0) return null;
        List<ArticleDTO> list = new ArrayList<>(originalList.size());
        list.addAll(originalList.stream().map(articleDO -> new ArticleDTO(articleDO.getId(),
                articleDO.getType(), articleDO.getTitle(), articleDO.getUrl())).collect(Collectors.toList()));
        return list;
    }
}
