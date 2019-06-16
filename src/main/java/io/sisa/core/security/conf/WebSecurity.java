package io.sisa.core.security.conf;

import io.sisa.core.security.AuthenticationTokenFilter;
import io.sisa.core.security.JwtTokenHelper;
import io.sisa.core.security.RestAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * @author isaozturk
 */

@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(value = {JwtProperties.class})
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurity extends WebSecurityConfigurerAdapter {

	private final UserDetailsService userDetailsService;

	private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;

	private final BCryptPasswordEncoder cryptPasswordEncoder;

	private final JwtTokenHelper jwtTokenHelper;

	public WebSecurity(@Qualifier UserDetailsService userDetailsService,
					   RestAuthenticationEntryPoint restAuthenticationEntryPoint,
					   BCryptPasswordEncoder cryptPasswordEncoder,
					   JwtTokenHelper jwtTokenHelper) {

		this.userDetailsService = userDetailsService;
		this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
		this.cryptPasswordEncoder = cryptPasswordEncoder;
		this.jwtTokenHelper = jwtTokenHelper;
	}

	@Bean
	public AuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
		return new AuthenticationTokenFilter(jwtTokenHelper, userDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint)
				.and()
				.authorizeRequests()
				.antMatchers(HttpMethod.POST, "/api/v1/auth/login").permitAll()
				.antMatchers("/api/v1/cities/*").hasRole("ADMIN")
				.antMatchers("/api/v1/cities").hasRole("STANDARD")
				.anyRequest().authenticated()
				.and()
				.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(cryptPasswordEncoder);
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}
}
