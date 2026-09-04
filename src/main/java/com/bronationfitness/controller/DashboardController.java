package com.bronationfitness.controller;

import com.bronationfitness.model.Member;
import com.bronationfitness.repository.MemberRepository;
import com.bronationfitness.repository.PlanRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
public class DashboardController {

    private final MemberRepository memberRepo;
    private final PlanRepository planRepo;

    public DashboardController(
            MemberRepository memberRepo,
            PlanRepository planRepo) {

        this.memberRepo = memberRepo;
        this.planRepo = planRepo;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        long total = memberRepo.count();

        List<Member> all = memberRepo.findAll();

        long expired = all.stream()
                .filter(m -> m.getExpiryDate() != null
                        && m.getExpiryDate().isBefore(LocalDate.now()))
                .count();

        long active = total - expired;

        long expiringSoon = all.stream()
                .filter(m -> m.getExpiryDate() != null
                        && !m.getExpiryDate().isBefore(LocalDate.now()))
                .filter(m -> m.getExpiryDate()
                        .isBefore(LocalDate.now().plusDays(8)))
                .count();

        model.addAttribute("total", total);
        model.addAttribute("active", active);
        model.addAttribute("expired", expired);
        model.addAttribute("expiringSoon", expiringSoon);
        model.addAttribute("plansCount", planRepo.count());
        model.addAttribute("members", all);

        return "dashboard";
    }
}