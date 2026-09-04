package com.bronationfitness.controller;

import com.bronationfitness.model.Plan;
import com.bronationfitness.repository.PlanRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/plans")
public class PlanController {

    private final PlanRepository planRepo;

    public PlanController(PlanRepository planRepo) {
        this.planRepo = planRepo;
    }

    @GetMapping
    public String list(Model model) {

        model.addAttribute("plans", planRepo.findAll());

        // This is required for th:object="${plan}"
        model.addAttribute("plan", new Plan());

        return "plans";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Plan plan) {

        planRepo.save(plan);

        return "redirect:/plans";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {

        planRepo.deleteById(id);

        return "redirect:/plans";
    }
}