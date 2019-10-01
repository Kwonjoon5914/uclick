package kr.co.uclick.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration//Configuration을 클래스에 적용하고 @Bean을 해당 클래스의 메소드에 적용하면 @Autowired로 빈을 부를 수 있다.
@EnableWebMvc//자동으로 WebMvcConfigurationSupport 클래스가 Bean에 등록된다.
@ComponentScan("kr.co.uclick.controller")//@Component와 @Service, @Repository, @Controller, @Configuration이 붙은 클래스 Bean들을 찾아서 Context에 bean등록을 해주는 Annotation
public class SpringWebConfiguration implements WebMvcConfigurer {

	@Override//슈퍼 클래스에 존재하는 필드나 메서드를 서브 클래스에서 재정의하여 사용할때 사용(오버라이딩을 통한 슈퍼클래스를 생성할 때에는 super 키워드를 사용)
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {//컨트롤러에서 자동으로 컨텐츠 타입을 체크
		//
		configurer.favorPathExtension(false);	//프로퍼티 값을 보고 URL의 확장자에서 리턴 포맷을 결정 여부
		configurer.favorParameter(true);		//URL 호출 시 특정 파라미터로 리턴포맷 전달 허용 여부 ex)a.do?format=json
		configurer.parameterName("mediaType");	//Parameter이름 설정
		configurer.ignoreAcceptHeader(true);	//HttpRequest Header의 Accept 무시 여부
		configurer.useJaf(false);
		configurer.mediaType("xml", MediaType.APPLICATION_XML);		//매핑할 ContentType을 작성
		configurer.mediaType("json", MediaType.APPLICATION_JSON);	//매핑할 ContentType을 작성
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {//무언가를 진행할 때 특정 작업을 수행하는 Intercepter 설정
		registry.addInterceptor(new LocaleChangeInterceptor());
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();//@RequestMapping에 등록되지 않은 요청 또는 JSP에 대한 요청을 처리하는 DefaultServletHandler 설정(이곳으로 요청전달됨)
	}

	@Bean
	public LocaleResolver LocaleResolver() {
		return new CookieLocaleResolver();//언어를 변경하고 쿠키에 값을 저장한다.
	}

	@Bean
	public MessageSource messageSource() {//messages 파일들을 읽어오는 부분
		ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
		resourceBundleMessageSource.setBasename("validate-message.properties");
		return resourceBundleMessageSource;
	}

	@Bean
	public InternalResourceViewResolver InternalResourceViewResolver() {//웹 어플리케이션의 WAR 파일 내에 포함된 뷰 템플릿을 찾는다
		InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
		internalResourceViewResolver.setPrefix("/WEB-INF/jsp/");	//경로는 논리적 뷰 이름에 접두어와 접미어를 붙여서 구성
		internalResourceViewResolver.setSuffix(".jsp");				//SpringWebConfiguration.java에 있는 internalResourceViewResolver.setPrefix("/WEB-INF/jsp/")에 의해서 생략될 수 있다.
																	//여기서 리턴값에 sample을 입력하지 않아도 위 메서드 이름으로 찾아서 리턴한다.
		return internalResourceViewResolver;
	}
}
