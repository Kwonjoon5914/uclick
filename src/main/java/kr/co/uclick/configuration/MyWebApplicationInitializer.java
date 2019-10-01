package kr.co.uclick.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
//web.xml 대체
public class MyWebApplicationInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletCxt) {//dispatcher 서블릿은 모든 서블릿을 분석해서 dispatch할 준비를 한다.

		// Create the 'root' Spring application context(root-context 해당하는 파일)
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(SpringConfiguration.class);

		// Manage the lifecycle of the root application context(servlet-context 해당하는 파일)
		servletCxt.addListener(new ContextLoaderListener(rootContext));

		// Create the dispatcher servlet's Spring application context(dispatcher-context 해당하는 파일)
		AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
		dispatcherContext.register(SpringWebConfiguration.class);

		// Register and map the dispatcher servlet
		//서블릿 웹 애플리케이션 컨텍스트는 서블릿 안에서 초기화되고 서블릿이 종료될 떄 같이 종료된다.
		//이때 사용되는 서블릿이 DispatcherServlet이다.
		//이것을 자바코드로 바꾼것(root-context,java-context를 자바 코드로 분리)
		ServletRegistration.Dynamic dispatcher = servletCxt.addServlet("politech",
				new DispatcherServlet(dispatcherContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");

		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();//utf-8 필터
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		servletCxt.addFilter("characterEncodingFilter", characterEncodingFilter).addMappingForUrlPatterns(null, false,
				"/*");
	}

}