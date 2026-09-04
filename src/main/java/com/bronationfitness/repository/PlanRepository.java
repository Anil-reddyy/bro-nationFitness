package com.bronationfitness.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bronationfitness.model.Plan;

public interface PlanRepository extends JpaRepository<Plan, Long>{

}
