package com.bronationfitness.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bronationfitness.model.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
	 List<Member> findByNameContainingIgnoreCase(String name);
	    List<Member> findByPhoneContaining(String phone);

}
