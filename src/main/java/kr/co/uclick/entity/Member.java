package kr.co.uclick.entity;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.cache.annotation.Cacheable;

@Cacheable
@Cache(region="Member", usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name="member")
public class Member {

	@Id
	@TableGenerator(name = "member", allocationSize = 1)//sample 테이블 생성 : jpa
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "member")
	private Long id;

	@Column(length = 20, nullable = false) //20bytes 설정
	private String name;

	@Cache(region="Member.phones", usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)//.NONSTRICT_READ_WRITE   , , fetch = FetchType.EAGER, , orphanRemoval = true, cascade=CascadeType.ALL
	@OneToMany(mappedBy="member", orphanRemoval=true, cascade=CascadeType.ALL)//
	private List<Phone> phones;
	
	@Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false, insertable = true)
	private Date regidate;

	@Column(nullable = false)
	private String age;
	
	@Column(nullable = false)
	private String email;

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public Date getRegidate() {
		return regidate;
	}

	public void setRegidate(Date regidate) {
		this.regidate = regidate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Phone> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}
	
	public void addPhone(Phone phone) {
		if(phones==null) {
			phones = new ArrayList<Phone>();
		}
		phones.add(phone);
		phone.setMember(this);
	}

	public void removePhone(Phone phone) {
		if(phones==null) {
			phones = new ArrayList<Phone>();
		}
		phones.removeIf(p -> p.getId()==phone.getId());
		phone.setMember(null);
	}
	
	
}
	

