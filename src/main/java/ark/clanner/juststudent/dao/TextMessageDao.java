package ark.clanner.juststudent.dao;

import ark.clanner.juststudent.entity.DO.TextMessageDO;
import ark.clanner.juststudent.entity.msg.TextMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Clanner on 2018/5/5.
 */
public interface TextMessageDao extends JpaRepository<TextMessageDO, Integer> {

    @Query("select count(*) from TextMessageDO ")
    @Transactional
    int getCount();
}
