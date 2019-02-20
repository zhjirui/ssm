package com.demo.service.impl;

import com.demo.domain.JobAndTrigger;
import com.demo.domain.JobAndTriggerMapper;
import com.demo.service.IJobAndTriggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class JobAndTriggerImpl implements IJobAndTriggerService {

    @Autowired
    private JobAndTriggerMapper jobAndTriggerMapper;

    public List<JobAndTrigger> getJobAndTriggerDetails() {
        List<JobAndTrigger> list = jobAndTriggerMapper.getJobAndTriggerDetails();
        return list;
    }

}
