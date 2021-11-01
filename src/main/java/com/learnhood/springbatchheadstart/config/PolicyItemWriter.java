package com.learnhood.springbatchheadstart.config;

import com.learnhood.springbatchheadstart.model.Policy;
import com.learnhood.springbatchheadstart.repository.PolicyRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PolicyItemWriter implements ItemWriter<Policy> {
    @Autowired
    private PolicyRepository policyRepository;

    @Override
    public void write(List<? extends Policy> policies) throws Exception {
        policyRepository.saveAll(policies);
    }
}
