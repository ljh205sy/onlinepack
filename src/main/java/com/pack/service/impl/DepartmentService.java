package com.pack.service.impl;

import com.pack.entity.Department;
import com.pack.repository.BaseRepository;
import com.pack.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @Author: liujinhui
 * @Date: 2019/11/9 10:03
 */
@Service
@Transactional
public class DepartmentService extends BaseServiceImpl<Department, String> {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public BaseRepository<Department, String> getRepository() {
        return departmentRepository;
    }
}
