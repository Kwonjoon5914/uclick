package kr.co.uclick.configuration;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.hibernate.Hibernate;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import kr.co.uclick.entity.Member;
import kr.co.uclick.entity.Phone;
import kr.co.uclick.repository.MemberRepository;
import kr.co.uclick.repository.PhoneRepository;
import kr.co.uclick.service.MemberService;
import kr.co.uclick.service.PhoneService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class)
public class ConfigurationTest {

	@Autowired
	MemberService memberService;
	@Autowired
	PhoneService phoneService;


	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private PhoneRepository phoneRepository;

	@Ignore
	public void test1() {
		Member m = new Member();
		m.setName("권준");
		m.setAge("27");
		m.setEmail("kj4288@naver.com");
		Phone p = new Phone("01031066004");
		Phone p2 = new Phone("01023145914");
		m.addPhone(p);
		m.addPhone(p2);
		p.setMember(m);
		p2.setMember(m);
		memberService.save(m);


		IgniteConfiguration igniteConfig = new IgniteConfiguration();//환경 설정과 관련된 부분
		CacheConfiguration<Long, Member> cacheConfig = new CacheConfiguration("cafe-grid");
		igniteConfig.setCacheConfiguration(cacheConfig);
		Ignite ignite = Ignition.start(igniteConfig);
		IgniteCache cache = ignite.getOrCreateCache(cacheConfig);//Long, Member로 해서 Cache에 집어넣는다.
		
		Member mm = memberRepository.findById(66L).get();
		
		cache.put(66L, mm);//cache에 66인 값 집어넣고
		
		Member m2 = (Member) cache.get(66L);//캐시에 있는 값을 get하여 가져온다.
		

//		cache.put(1, "111");
//		cache.put(2, "222");
//
//		String s = (String) cache.get(1);
//		System.out.println(s);
		
	    		 
//	    		 SampleCacheServiceImpl a = new SampleCacheServiceImpl();
//			a.testCache(66L);
//			a.testCache(66L);
	    		 
//		memberService.findById(66L);
//		memberService.findAll();
		
//		IgniteCache<Integer, EmployeeDTO> cache = ignite.cache("baeldungCache");
//		EmployeeDTO employeeDTO = cache.get(employeeId);
		
		
//		memberService.findById(66L);
//		memberService.findAll();
//		memberService.findById(66L);
//		memberService.findAll();
		
		
		
//		m.removePhone(p);
//		p.setMember(null);
//		phoneService.save(p);
//		p.setMember(null);
//		phoneService.delete(p);
		
//		phoneRepository.deleteById(8L);
	}
	
	private static void loadCache(IgniteCache<Long, Member> cache) {
        long start = System.currentTimeMillis();

        // Start loading cache from persistent store on all caching nodes.
        cache.loadCache(null, 100000);

        long end = System.currentTimeMillis();

        System.out.println(">>> Loaded " + cache.size() + " keys with backups in " + (end - start) + "ms.");
    }
	
//	@Test
//	public void test2() {
//		Member m = new Member();
//		m.setName("김기윤");
//		m.setAge("23");
//		m.setEmail("dd");
//
////		memberRepository.save(m);
////		memberRepository.delete(m);
//		
//		Phone p = new Phone("01031066004");
////		Phone p2 = new Phone("01031066002");
//		m.addPhone(p);
////		m.addPhone(p2);
//		memberRepository.save(m);
//		
//		m.removePhone(p);
//		memberRepository.save(m);
//		memberRepository.flush();
//		
////		phoneService.save(p);
////		m.removePhone(p);
////		phoneRepository.delete(p);
////		phoneService.delete(p);
//		
//		
//		
//		
//	}
	
	@Test
	public void b() {
		Long pid = 164L; 
		Phone p = phoneService.findPhoneById(pid);
		Member member = memberService.findById2(p.getMember().getId());
		
		int count = 0;
		
		for(int i = 0 ; i < member.getPhones().size(); i++) {
			if(member.getPhones().get(i).getId() == 102L) {
				count = 0;
			}
		}
		
		member.getPhones().remove(count);
		
	
		for(int i =0 ; i < member.getPhones().size(); i++) {
			System.out.println(member.getPhones().get(i).getNumber());
		}
		
		memberService.save(member);

		for(int i =0 ; i < member.getPhones().size(); i++) {
			System.out.println(member.getPhones().get(i).getNumber());
		}
	}
	
	
	@Ignore
	public void a() {
		Long pid = 103L; 
		Phone p = phoneService.findPhoneById(pid);
		Member member = memberService.findById2(p.getMember().getId());
		
		for(int i =0 ; i < member.getPhones().size(); i++) {
			System.out.println(member.getPhones().get(i).getNumber());
		}
		
		p.setMember(null);
		phoneService.save(p);
		
		member.getPhones().remove(p);
			
		memberService.save(member);
		
		for(int i =0 ; i < member.getPhones().size(); i++) {
			System.out.println(member.getPhones().get(i).getNumber());
		}

	}

}
