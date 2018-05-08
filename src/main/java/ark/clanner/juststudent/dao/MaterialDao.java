package ark.clanner.juststudent.dao;

import ark.clanner.juststudent.entity.DO.ArticleDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Clanner on 2018/5/5.
 */
public interface MaterialDao extends JpaRepository<ArticleDO, Integer> {

    @Query("update ArticleDO article set article.type=:type,article.title=:title,article.url=:url where article.id=:id")
    @Modifying
    @Transactional
    int updateArticleById(@Param("id") int id,
                          @Param("type") String type,
                          @Param("title") String title,
                          @Param("url") String url);

    List<ArticleDO> findAllByType(String type);
}
