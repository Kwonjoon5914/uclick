//package kr.co.uclick.controller;
//
//import java.util.HashMap;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import kr.co.uclick.service.SampleCacheService;
//import kr.co.uclick.service.SampleCacheServiceImpl;
//
//@Controller
//public class SampleCacheController {
//
//	private static final Logger logger = LoggerFactory.getLogger(SampleCacheServiceImpl.class);
//	
//	@Autowired
//	private SampleCacheService sampleCacheService;
//	
//	@RequestMapping(value = "/sample/noCache")
//	@ResponseBody
//	public String noCache(@RequestParam HashMap<String, String> map) {
//		Long id = Long.parseLong(map.get("id"));
//		return sampleCacheService.testCache(id);
//	}
//}
