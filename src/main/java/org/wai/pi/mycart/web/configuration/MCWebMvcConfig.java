package org.wai.pi.mycart.web.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.wai.pi.mycart.web.MCURIConstants;
import org.wai.pi.mycart.web.security.MCSecurityInterceptor;



@EnableWebMvc
@Configuration
@Import({ MCSecuritySetupConfig.class,MCDataSourceConfig.class })
public class MCWebMvcConfig extends WebMvcConfigurerAdapter {
	
	@Autowired
	@Qualifier("mcSecurityInterceptor")
	private MCSecurityInterceptor mcSecurityInterceptor; 
	
	@Bean
	public ViewResolver getViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/view/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	/**
	 * Used to deliver static content. Supports caching.
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/css/**").addResourceLocations("/resources/css/")
				.setCachePeriod(31556926);
		registry.addResourceHandler("/resources/img/**").addResourceLocations("/resources/img/")
				.setCachePeriod(31556926);
		registry.addResourceHandler("/resources/js/**").addResourceLocations("/resources/js/").setCachePeriod(31556926);
	}

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages.mycart");
		return messageSource;
	}
	
	@Bean
	@Autowired
	public MessageSourceAccessor messageSourceAccessor(MessageSource messageSource){
		MessageSourceAccessor accessor = new MessageSourceAccessor(messageSource);
		return accessor;
	}

	@Bean
	 public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
	  return new PropertySourcesPlaceholderConfigurer();
	 }
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(mcSecurityInterceptor).addPathPatterns(MCURIConstants.mycartAppUrl);
		
	}
	
	@Bean
	public RedirectStrategy redirectStrategy(){
		return new DefaultRedirectStrategy();
	}
}
