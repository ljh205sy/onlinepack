package com.pack.repository;

import com.pack.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @Author: liujinhui
 * @Date: 2019/11/3 21:25
 */

@Repository
public interface UserRepository extends BaseRepository<User, String> {

}
