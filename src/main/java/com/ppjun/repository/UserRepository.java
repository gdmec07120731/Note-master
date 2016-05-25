package com.ppjun.repository;

import com.ppjun.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * UserRepository
 *
 * @author Rc3
 * @create 2016/05/25  14:12
 */
public interface UserRepository extends JpaRepository<UserEntity,Integer>
{


    @Modifying
    @Transactional
    @Query("update UserEntity  user set user.username=:qUserName,user.password=:qPassWord where user.id=:qId")
  public void updateUser(@Param("qUserName") String userName, @Param("qPassWord") String password , @Param("qId") Integer id );

}
