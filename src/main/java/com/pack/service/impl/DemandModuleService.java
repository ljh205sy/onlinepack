package com.pack.service.impl;

import com.pack.entity.DemandModule;
import com.pack.repository.BaseRepository;
import com.pack.repository.DemandModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @Author: liujinhui
 * @Date: 2019/11/9 10:03
 */
@Service
@Transactional
public class DemandModuleService extends BaseServiceImpl<DemandModule, String> {
    @Autowired
    private DemandModuleRepository demandModuleRepository;

    @Override
    public BaseRepository<DemandModule, String> getRepository() {
        return demandModuleRepository;
    }
}
