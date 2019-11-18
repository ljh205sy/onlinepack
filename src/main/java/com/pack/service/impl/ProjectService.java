package com.pack.service.impl;

import com.pack.entity.Demand;
import com.pack.entity.Project;
import com.pack.repository.BaseRepository;
import com.pack.repository.DemandRepository;
import com.pack.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @Author: liujinhui
 * @Date: 2019/11/9 10:03
 */
@Service
@Transactional
public class ProjectService extends BaseServiceImpl<Project, Integer> {
    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public BaseRepository<Project, Integer> getRepository() {
        return projectRepository;
    }
}
