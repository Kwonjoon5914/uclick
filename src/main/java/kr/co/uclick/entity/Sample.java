/*package kr.co.uclick.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

@Entity
public class Sample {

	@Id
	@TableGenerator(name = "sample")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "sample")
	private Long id;

	private String name;

	private int number;
	
	private Date date;
	
	private String tongsinsa;

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

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}


	public String getTongsinsa() {
		return tongsinsa;
	}

	public void setTongsinsa(String tongsinsa) {
		this.tongsinsa = tongsinsa;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
*/