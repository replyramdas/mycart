package org.wai.pi.mycart.web.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Import(MCDataSourceConfig.class)
@ComponentScan(basePackages = "org.wai.pi.mycart.web")
public class MCAppConfig {
	

    @Bean(name="passwordEncoder")
    public PasswordEncoder getEncoder(){
    	return new BCryptPasswordEncoder();
    }
	
	
}
