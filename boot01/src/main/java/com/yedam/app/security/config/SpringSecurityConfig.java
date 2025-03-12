package com.yedam.app.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
	@Bean //비밀번호 암호화
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	/*
	@Bean
	InMemoryUserDetailsManager inMemoryUserDetailService() {
		UserDetails user = User.builder()
					.username("user1")
					.password(passwordEncoder().encode("1234"))
					.roles("USER")
					//.authorities("ROLE_USER")
					.build();
		
		UserDetails admin = User.builder()
				.username("admin1")
				.password(passwordEncoder().encode("1234"))
				//.roles("ADMIN")
				.authorities("ROLE_ADMIN","ROLE_USER")
				.build();
		return new InMemoryUserDetailsManager(user, admin);
	} */
	
		@Bean
		SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http // 보호된 리소스 URI에 접근할 수 있는 권한을 설정
		.authorizeHttpRequests((authrize)
		-> authrize
		.requestMatchers("/", "/all").permitAll() // 전체 접근 허용
		.requestMatchers("/user/**").hasAnyRole("USER", "ADMIN") // USER, ADMIN이라는 롤을 가진 사용자만 접
		.requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN") // ROLE_ADMIN이라는 롤을 가진 사용자만 접
		.anyRequest().authenticated() // 권한 상관없이 인증받은 사용자만 접근 허용
		)
		.formLogin(formlogin -> formlogin
		.defaultSuccessUrl("/all"))
		.logout(logout -> logout
		.logoutSuccessUrl("/all")
		.invalidateHttpSession(true));
		return http.build();
		}
	
	@Bean
	 public WebSecurityCustomizer webSecurityCustomizer() {
	 return (web) -> web.ignoring().requestMatchers("/images/**", "/js/**", "/css/**"); // 예외처리하고 싶은 url
	 }

	
}
