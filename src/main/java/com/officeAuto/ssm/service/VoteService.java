package com.officeAuto.ssm.service;

import com.officeAuto.ssm.model.Activity;
import com.officeAuto.ssm.model.Vote;

import java.util.List;

public interface VoteService {

    public int insert(Vote vote) ;

    Vote findById(Integer voteId);

    List<Vote> findByActivity(Integer activityId);
}
