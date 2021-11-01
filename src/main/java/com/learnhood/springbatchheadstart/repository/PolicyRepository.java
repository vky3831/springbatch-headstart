package com.learnhood.springbatchheadstart.repository;

import com.learnhood.springbatchheadstart.model.Policy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PolicyRepository extends JpaRepository<Policy, Integer> {
}
