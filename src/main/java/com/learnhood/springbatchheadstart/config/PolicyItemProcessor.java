package com.learnhood.springbatchheadstart.config;

import com.learnhood.springbatchheadstart.model.Policy;
import org.springframework.batch.item.ItemProcessor;

public class PolicyItemProcessor implements ItemProcessor<Policy, Policy> {
    @Override
    public Policy process(Policy policy) throws Exception {
        return policy;
    }
}
