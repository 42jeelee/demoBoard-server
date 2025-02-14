package kr.co.jeelee.demoboard.global.config.security;

import kr.co.jeelee.demoboard.domain.auth.service.CustomUserDetailsService;
import kr.co.jeelee.demoboard.global.config.security.filter.AuthenticationFilter;
import kr.co.jeelee.demoboard.global.config.security.filter.FilterExceptionFilter;
import kr.co.jeelee.demoboard.global.config.security.handler.CustomAccessDeniedHandler;
import kr.co.jeelee.demoboard.global.config.security.handler.CustomAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final AuthenticationFilter authenticationFilter;
	private final FilterExceptionFilter filterExceptionHandler;
	private final CustomAccessDeniedHandler accessDeniedHandler;
	private final CustomAuthenticationEntryPoint authenticationEntryPoint;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.csrf(AbstractHttpConfigurer::disable)
			.sessionManagement(sessionManagement -> sessionManagement
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			)
			.authorizeHttpRequests(auth -> auth
				.requestMatchers("/h2-console/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.POST, "/categories/**").hasRole("ADMIN")

				.requestMatchers(HttpMethod.POST, "/posts", "/comments").hasRole("MEMBER")
				.requestMatchers(HttpMethod.POST, "/members", "/login").permitAll()

				.requestMatchers(HttpMethod.GET ,"/", "/posts/**", "/categories/**").permitAll()
				.anyRequest().authenticated()
			)
			.headers(headers -> headers
				.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
			)
			.addFilterBefore(authenticationFilter, RequestHeaderAuthenticationFilter.class)
			.addFilterBefore(filterExceptionHandler, AuthenticationFilter.class)
			.exceptionHandling(exception -> exception
					.accessDeniedHandler(accessDeniedHandler)
					.authenticationEntryPoint(authenticationEntryPoint)
			)
		;
		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(
			CustomUserDetailsService customUserDetailsService,
			PasswordEncoder passwordEncoder
	) {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(customUserDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder);

		return new ProviderManager(authenticationProvider);
	}
}
