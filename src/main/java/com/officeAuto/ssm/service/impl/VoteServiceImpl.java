package com.officeAuto.ssm.service.impl;


import com.officeAuto.ssm.dao.system.PlanDao;
import com.officeAuto.ssm.dao.system.VoteDao;
import com.officeAuto.ssm.model.Activity;
import com.officeAuto.ssm.model.Vote;
import com.officeAuto.ssm.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class VoteServiceImpl implements VoteService{

    @Autowired
    private VoteDao voteDao;

    @Override
    public int insert(Vote vote) {

        return voteDao.insert(vote);
    }

    @Override
    public Vote findById(Integer voteId) {
        return voteDao.findById(voteId);
    }

    @Override
    public List<Vote> findByActivity(Integer activityId) {
        return voteDao.findByActivity(activityId);
    }


}
