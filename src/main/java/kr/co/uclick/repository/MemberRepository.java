package kr.co.uclick.repository;

import java.util.List;

import javax.persistence.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;


import kr.co.uclick.entity.Member;

public interface MemberRepository //JpaRepository는 인터페이스를 준비하기만 하면, 자동으로 클래스를 만들고 Bean을 생성하는 것(필요한건 인터페이스뿐)
	extends JpaRepository<Member, Long>, QuerydslPredicateExecutor<Member> {
			
	//querycache 적용
	@QueryHints(value= {
			@QueryHint(name="org.hibernate.cacheable", value="true"),
			@QueryHint(name="org.hibernate.cacheMode", value="NORMAL"),
	})
	public List<Member> findMemberByNameContaining(String name);
	
	public Member findMemberByid(Long memberId);

}
