package com.officeAuto.ssm.service;

import com.officeAuto.ssm.model.Vote;

public interface VoteService {

    public int insert(Vote vote) ;

    Vote findById(Integer voteId);
}
