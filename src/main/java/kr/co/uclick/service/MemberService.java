package kr.co.uclick.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.uclick.entity.Member;
import kr.co.uclick.repository.MemberRepository;

@Service
@Transactional
public class MemberService {
	
	@Autowired
	private MemberRepository memberRepository;

	@Transactional
	public List<Member> findAll() {
		return memberRepository.findAll();
	}

	@Transactional
	public void delete(Member member) {

		memberRepository.delete(member);
	}
	
	@Transactional
	public Optional<Member> findById(Long id) {
		
		return memberRepository.findById(id);
	}
	
	@Transactional
	public Member findById2(Long id) {		
		Member member = memberRepository.findMemberByid(id);
		Hibernate.initialize(member.getPhones());
		return member;
	}

	@Transactional
	public void save(Member member) {
		memberRepository.save(member);	
	}
    

	@Transactional
	public List<Member> findMemberByNameContaining(String name) {
		return memberRepository.findMemberByNameContaining(name);
	}
	

	@Transactional
	public Page<Member> getAllMember(Pageable page){
		return memberRepository.findAll(page);
	}

	@Transactional
	public long memberCount() {
		
		return memberRepository.count();
	}

}