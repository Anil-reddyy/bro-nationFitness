package com.bronationfitness.service;


import com.bronationfitness.model.Member;
import com.bronationfitness.model.Plan;
import com.bronationfitness.repository.MemberRepository;
import com.bronationfitness.repository.PlanRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class SeedDataRunner implements CommandLineRunner {

    private final PlanRepository planRepo;
    private final MemberRepository memberRepo;

    public SeedDataRunner(PlanRepository planRepo, MemberRepository memberRepo) {
        this.planRepo = planRepo;
        this.memberRepo = memberRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        if (planRepo.count() == 0) {
            Plan monthly = new Plan("Monthly", 1, new BigDecimal("1000"));
            Plan quarterly = new Plan("Quarterly", 3, new BigDecimal("2700"));
            Plan yearly = new Plan("Yearly", 12, new BigDecimal("10000"));
            planRepo.save(monthly);
            planRepo.save(quarterly);
            planRepo.save(yearly);

            // Add sample member
            Member m = new Member();
            m.setName("Ramesh");
            m.setPhone("9876543210");
            m.setEmail("ramesh@example.com");
            m.setStartDate(LocalDate.now().minusDays(10));
            m.setPlan(monthly);
            m.setExpiryDate(LocalDate.now().minusDays(10).plusMonths(monthly.getDurationMonths()));
            m.setNotes("Sample member");
            memberRepo.save(m);
        }
    }
}
