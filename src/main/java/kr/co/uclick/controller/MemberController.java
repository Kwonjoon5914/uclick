package kr.co.uclick.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.uclick.entity.Member;
import kr.co.uclick.entity.Phone;
import kr.co.uclick.repository.MemberRepository;
import kr.co.uclick.service.MemberService;
import kr.co.uclick.service.PhoneService;


@Controller
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	private MemberService memberService;
	@Autowired
	private PhoneService phoneService;
	@Autowired
	MemberRepository memberRepository;

	@RequestMapping(value = "insertForm")
	public String insertForm(Model model) {
		return "insertForm";
	}

	@RequestMapping(value = "view", method = {RequestMethod.GET, RequestMethod.POST})
	public String view(@RequestParam Map<String, String> param, Model model, HttpServletRequest request) {
		logger.info("===========================view==============================================");
		long id = Long.parseLong(param.get("id"));

		Member member = memberService.findById2(id);

		model.addAttribute("member", member);
		logger.info("클릭한 아이디:" + id);
		return "view";
	}

	// 검색
	@RequestMapping(value = "search")
	public String search(@RequestParam Map<String, String> param, Model model) {
		
		logger.info("=================================search=====================================");
		
		String keyword = param.get("keyword");
		String keyfield = param.get("keyfield");
		
		logger.debug("keyfield : {}", keyfield);
		logger.debug("keyword : {}", keyword);
		
		List<Member> members = new ArrayList<Member>();
		List<Phone> phones = new ArrayList<Phone>();
		
		if (keyfield.equals("searchname")) { //이름 like 조회
			members = memberService.findMemberByNameContaining(keyword);
			
			if (members.size() > 0) {
				model.addAttribute("members", members);
			}
			
		}else if(keyfield.equals("searchphone")) { //폰번호 like 조회
			phones = phoneService.findPhoneByNumberContaining(keyword);
			
			for (Phone phone : phones) {
				members.add(phone.getMember());
			}
		}else if(keyfield.equals("searchtongsinsa")) {
			
			phones = phoneService.findPhoneByTongsinsa(keyword);
			for (Phone phone : phones) {
				members.add(phone.getMember());
			}
		}

		members = new ArrayList<Member>(new HashSet<Member>(members));
		model.addAttribute("members", members);

		return "search";
	}
	
	@RequestMapping(value = "updateForm",method = {RequestMethod.GET, RequestMethod.POST})
	public String updateForm(@RequestParam Map<String, String> param, Model model) {

		logger.info("=========================================updateform===================================================");
		long id = Long.parseLong(param.get("id"));
		long pid = Long.parseLong(param.get("pid"));
		Optional<Phone> phone = phoneService.findById(pid);
		model.addAttribute("phone", phone.get());
		model.addAttribute("memberid", id);
		logger.info("=====================================클릭한 아이디:" + pid);
		return "updateForm";
	}

	// 멤버 수정폼
	@RequestMapping(value = "editForm")
	public String editForm(@RequestParam Map<String, String> param, Model model) {

		logger.info("view");
		long id = Long.parseLong(param.get("id"));
		Optional<Member> member = memberService.findById(id);
		model.addAttribute("member", member.get());
		logger.info("=====================================클릭한 아이디:" + id);
		return "editForm";
	}

	// 멤버 수정 후 저장
	@RequestMapping(value = "Member_updating")
	public String Member_updating(@RequestParam Map<String, String> param, Model model, HttpServletRequest request) {

		long id = Long.parseLong(param.get("id"));
		logger.debug("=====================================id : {}", id);
		String name = param.get("name");
		String age = param.get("age");
		String email = param.get("email");
		Optional<Member> updateList = memberService.findById(id);
		updateList.get().setName(name);
		updateList.get().setAge(age);
		updateList.get().setEmail(email);

		memberService.save(updateList.get());

		return "redirect:/0";
	}

	// 번호 수정 후 저장
		@RequestMapping(value = "Phone_updating", method = {RequestMethod.GET, RequestMethod.POST})
		public String Phone_updating(@RequestParam Map<String, String> param, Model model, HttpServletRequest request, HttpServletResponse resp) throws IOException {
			
			long member_id = Long.parseLong(param.get("id"));
			long phoneid = Long.parseLong(param.get("phoneid"));
			
			String tongsinsa = param.get("tongsinsa");
			String number = param.get("number");
			
			Optional<Phone> phone = phoneService.findById(phoneid);
			logger.info("====================================phoneid : {}", phone);
			
			if (phoneService.exist(number)!=true) {
				
				resp.setContentType("text/html; charset=UTF-8");
				PrintWriter out = resp.getWriter();
				
				out.println("<script>alert('핸드폰 번호가 수정 되었습니다!');location.href = 'view?id="+member_id+ "'</script>");
				out.flush();

				phone.get().setNumber(number);
				phone.get().setTongsinsa(tongsinsa);
				phoneService.save(phone.get());
				
				return null;
				
			}else{
				
				return "duplicate_error";
				
			}

		}

		//핸드폰 번호 추가
		@RequestMapping(value = "Phone_add", method = {RequestMethod.GET, RequestMethod.POST})
		public String Phone_add(@RequestParam Map<String, String> param, Model model, HttpServletRequest request, HttpServletResponse resp) throws IOException {
			
			long member_id = Long.parseLong(param.get("id"));
			String number = param.get("addphone");
			String tongsinsa = param.get("tongsinsa");
			
			Member m = memberService.findById2(member_id);

			if (phoneService.exist(number)!=true) {
				
				resp.setContentType("text/html; charset=UTF-8");
				PrintWriter out = resp.getWriter();				

				out.println("<script>alert('핸드폰번호가 추가 되었습니다!');location.href = 'view?id="+member_id+"'</script>");
				out.flush();
				
				Phone phone = new Phone();
				phone.setTongsinsa(tongsinsa);
				phone.setNumber(number);
				phone.setMember(m);

				m.getPhones().add(phone);
				memberService.save(m);
				
				return null;

			}else{
				return "duplicate_error";
			}

		}

		//핸드폰 번호만 삭제
		@RequestMapping(value = "Phone_delete", method = {RequestMethod.GET, RequestMethod.POST})
		public String Phone_delete(@RequestParam Map<String, String> param, Model model, HttpServletRequest request) {
			
			String old_url = request.getHeader("referer");
			long pid = Long.parseLong(param.get("pid"));

			logger.info("==============================================Delete id : " + pid);

			Phone p = phoneService.findPhoneById(pid);
			Member member = memberService.findById2(p.getMember().getId());
			
			int count = 0;
			
			for(int i = 0 ; i < member.getPhones().size(); i++) {
				if(member.getPhones().get(i).getId() == pid) {
					count = i;
				}
			}

			member.getPhones().remove(count);
			memberService.save(member);

			return "redirect:"+ old_url;
		}

	// 회원 저장
	@RequestMapping(value = "save")
	public String save(@RequestParam Map<String, String> param, Model model, HttpServletResponse resp) throws IOException {
		String age = param.get("age");
		String name = param.get("name");
		String number = param.get("number");
		String email = param.get("email");
		String tongsinsa = param.get("tongsinsa");
		
		if (phoneService.exist(number)!=true) {
			
			resp.setContentType("text/html; charset=UTF-8");
			PrintWriter out = resp.getWriter();
			
			out.println("<script>alert('회원 등록이 완료되었습니다!');location.href = '0'</script>");
			out.flush();
			
			Member member = new Member();
			member.setName(name);
			member.setAge(age);
			member.setEmail(email);

			Phone phone = new Phone();
			phone.setTongsinsa(tongsinsa);
			phone.setNumber(number);
			member.addPhone(phone);

			memberService.save(member);
			
			return null;

		}else{
			
			return "duplicate_error";

		}
	}

	// 회원 삭제
	// 회원 폰번호도 삭제됨
	@RequestMapping(value = "Member_delete")
	public String Member_delete(@RequestParam Map<String, String> param, Model model) {
		Long memberid = Long.parseLong(param.get("id"));
		Optional<Member> member = memberService.findById(memberid);
		memberService.delete(member.get());
		logger.info("=============================================Delete id:" + member);
		return "redirect:/0";

	}

	// 회원 리스트 출력
	@RequestMapping(value = "/{page}")
	public String list(Model model,@PathVariable("page") Integer page) {
			
			//@RequestParam Map<String, String> param
		Pageable pageable = PageRequest.of(page, 10, Direction.ASC, "regidate");
		
		if(page<0 || page==null) {
			page=0;
		}
		
		//뽑혀야 할 전체 페이지버튼 수
		long cntTmp = memberService.memberCount();
		double cntTmp2 = cntTmp/10.0;
		long finalVal = (long)(Math.ceil(cntTmp2));
		
		//한 화면에 보여줄 첫번째 버튼과 마지막 버튼(첫번째 숫자, 마지막숫자 (ex)1과5, 6과 10...)
		long startTmp = page/5;
		long startRange = startTmp*5;
		long endRange = ((startTmp+1)*5)-1;
		if(endRange>finalVal) {//맨 마지막 화면에서 숫자 끊어주기
			endRange = finalVal-1;
		}
		if(endRange<0) {
			endRange = 0;
		}
		model.addAttribute("startRange",startRange);
		model.addAttribute("endRange",endRange);
		model.addAttribute("pageNum",finalVal);
	
		model.addAttribute("memberlist",memberService.getAllMember(pageable));

		return "list";
	}
	
}

