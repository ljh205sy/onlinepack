package com.pack.service.impl;

import com.pack.entity.Role;
import com.pack.entity.User;
import com.pack.repository.BaseRepository;
import com.pack.repository.RoleRepository;
import com.pack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @Author: liujinhui
 * @Date: 2019年11月9日19:32:12
 */
@Service
@Transactional
public class RoleService extends BaseServiceImpl<Role, String> {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public BaseRepository<Role, String> getRepository() {
        return roleRepository;
    }
}
