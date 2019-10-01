package kr.co.uclick.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Predicate;


import kr.co.uclick.entity.Phone;
import kr.co.uclick.entity.QPhone;
import kr.co.uclick.repository.PhoneRepository;

@Service
@Transactional
public class PhoneService {

	@Autowired
	private PhoneRepository phoneRepository;
	private QPhone phone = QPhone.phone;
	
	@Transactional
	public void delete(Phone phone) {
		phoneRepository.delete(phone);
	}

	@Transactional
	public void delete(long id) {
		phoneRepository.deleteById(id);
	}
	

	@Transactional(readOnly = true)
	public List<Phone> findAll() {

		return phoneRepository.findAll();
	}
	
	@Transactional
	public void save(Phone phone) {

		phoneRepository.save(phone);
	}
	
	
	@Transactional
	public Optional<Phone> findById(long id) {
		phoneRepository.findById(id);
		return phoneRepository.findById(id);
	}
	
	@Transactional
	public Phone findPhoneById(Long id) {
		return phoneRepository.findPhoneById(id);
	}
	
	
	
	
	@Transactional(readOnly = true)
	public List<Phone> findPhoneByNumberContaining(String number) {

		return phoneRepository.findPhoneByNumberContaining(number);
	}
	
	//querydsl
	@Transactional
	public boolean exist(String number) {
		Predicate predicate = phone.number.eq(number);
		return phoneRepository.exists(predicate);
	}

	@Transactional
	public List<Phone> findPhoneByTongsinsa(String keyword) {
		
		return phoneRepository.findPhoneByTongsinsa(keyword);
	}

//	@Cacheable(value = "area")
//	public Phone findPhoneByNumber(String number) {
//		// TODO Auto-generated method stub
//		return phoneRepository.findPhoneByNumber(number);
//	}
	
}