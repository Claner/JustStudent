package ark.clanner.juststudent.dao;

import ark.clanner.juststudent.entity.DO.AdminDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Clanner on 2018/5/5.
 */
public interface AdminDao extends JpaRepository<AdminDO,Integer>{
    AdminDO findByAccountAndPwd(String account,String pwd);

    @Query("update AdminDO  a set a.pwd=:nPwd where a.account=:account and a.pwd=:oPwd")
    @Modifying
    @Transactional
    int updatePwdByAccountAndPwd(@Param("nPwd") String nPwd,
                                     @Param("account") String account,
                                     @Param("oPwd") String oPwd);
}
