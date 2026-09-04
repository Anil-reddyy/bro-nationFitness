package com.bronationfitness.controller;

import com.bronationfitness.model.Member;
import com.bronationfitness.model.Plan;
import com.bronationfitness.repository.MemberRepository;
import com.bronationfitness.repository.PlanRepository;
import com.bronationfitness.util.DateUtils;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/members")
public class MemberController {

    private final MemberRepository memberRepo;
    private final PlanRepository planRepo;

    public MemberController(
            MemberRepository memberRepo,
            PlanRepository planRepo) {

        this.memberRepo = memberRepo;
        this.planRepo = planRepo;
    }

    @GetMapping
    public String list(
            @RequestParam(value = "q", required = false) String q,
            Model model) {

        List<Member> members;

        if (q != null && !q.trim().isEmpty()) {
            members = memberRepo.findByNameContainingIgnoreCase(q);
        } else {
            members = memberRepo.findAll();
        }

        model.addAttribute("members", members);

        return "members";
    }

    @GetMapping("/add")
    public String addForm(Model model) {

        model.addAttribute("member", new Member());
        model.addAttribute("plans", planRepo.findAll());

        return "member_form";
    }

    @PostMapping("/save")
    public String save(
            @ModelAttribute Member member,
            @RequestParam("planId") Long planId) {

        Plan plan = planRepo.findById(planId).orElse(null);

        member.setPlan(plan);

        if (member.getStartDate() == null) {
            member.setStartDate(LocalDate.now());
        }

        if (plan != null) {
            member.setExpiryDate(
                DateUtils.addMonths(
                    member.getStartDate(),
                    plan.getDurationMonths()
                )
            );
        }

        memberRepo.save(member);

        return "redirect:/members";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {

        Member member = memberRepo.findById(id).orElseThrow();

        model.addAttribute("member", member);
        model.addAttribute("plans", planRepo.findAll());

        return "member_form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {

        memberRepo.deleteById(id);

        return "redirect:/members";
    }
}