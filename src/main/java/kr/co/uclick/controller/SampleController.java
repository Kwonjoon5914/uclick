/*package kr.co.uclick.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kr.co.uclick.entity.Sample;
import kr.co.uclick.service.SampleService;

@Controller//컨트롤러 임을 선언
public class SampleController {

	private static final Logger logger = LoggerFactory.getLogger(SampleController.class);//logger 객체 생성

	@Autowired//Service를 Autowire =>( Controller -> Service -> Repository )
	private SampleService sampleService;
	
	//@Autowired
	//private MemberService memberService;
	//@Autowired
	//private PhoneService phoneService;

	@GetMapping(value = "list.html")//=@RequestMapping(value="list.html",method={RequestMethod.POST})
	public String list(Model model) {
		model.addAttribute("users", sampleService.findAll());//서비스를 호출하고 결과를 바로 모델에 붙인다.
		return "list";//list.jsp를 return	
	}
	
	//@RequestMapping(value = "

	@GetMapping(value = "newForm.html")//입력부
	public String newForm(Model model) {//get방식을 사용할 경우 헤더에 붙어서 전송이 되기 때문에,도메인 주소를 보면 내가 입력한 값이 노출된다.(보안상 문제 발생)
		return "newForm";//newForm.jsp를 return
	}

	@GetMapping(value = "editForm.html")//수정화면
	public String editForm(Long sampleId, Model model) {
		sampleService.findById(sampleId);
		return "editForm";//editForm.jsp를 return
	}

	@PostMapping(value = "save.html")//post방식을 사용할 경우, 패킷안에 숨겨져서 전송이 된다.(숨겨서 보낼 데이터=post방식)
	public String save(Sample sample, Model model) {
		return "redirect:list.html";//뷰페이지가 아닌 list.html 경로를 반환
	}

	@DeleteMapping(value = "delete.html")
	public String delete(Long sampleId, Model model) {
		return "redirect:list.html";//뷰페이지가 아닌 list.html 경로를 반환
	}

	@GetMapping(value = "sample.html")
	public String sample(String name, Sample sample, Model model) {//메서드 이름 : sample

		logger.debug("sample name : {}", name);
		logger.debug("Sample.name : {}", sample.getName());

		model.addAttribute("samples", sampleService.findAll());
		model.addAttribute("sample", sample);
		model.addAttribute("findSampleByName", sampleService.findSampleByName(name));
		return "sample";//SpringWebConfiguration.java에 있는 internalResourceViewResolver.setPrefix("/WEB-INF/jsp/")에 의해서생략된것(원래는 /WEB-INF/sample.jsp)
	}	//여기서 리턴값에 sample을 입력하지 않아도 위 메서드 이름으로 찾아서 리턴한다.
}
*/