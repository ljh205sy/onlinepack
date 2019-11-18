package com.pack.service.impl;

import com.pack.entity.Demand;
import com.pack.entity.User;
import com.pack.repository.BaseRepository;
import com.pack.repository.DemandRepository;
import com.pack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @Author: liujinhui
 * @Date: 2019/11/9 10:03
 */
@Service
@Transactional
public class DemandService extends BaseServiceImpl<Demand, Integer> {
    @Autowired
    private DemandRepository demandRepository;

    @Override
    public BaseRepository<Demand, Integer> getRepository() {
        return demandRepository;
    }
}
