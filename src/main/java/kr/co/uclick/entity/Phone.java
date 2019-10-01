package kr.co.uclick.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.cache.annotation.Cacheable;


@Cacheable
@Cache(region="Phone", usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
public class Phone {

	@Id
	@TableGenerator(name = "phone", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "phone")
	private Long id;

	@Column
	private String number;
	
	@Column
	private String tongsinsa;

	@ManyToOne
	@JoinColumn(name="member_id")
	private Member member;
	
	public Phone() {

	}
	
	public Phone(String number) {

		this.number = number;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	public String getTongsinsa() {
		return tongsinsa;
	}

	public void setTongsinsa(String tongsinsa) {
		this.tongsinsa = tongsinsa;
	}
	
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	
	public void clear() {
		this.number = null;
		this.tongsinsa = null;
		this.member = null;
	}

	
	
}