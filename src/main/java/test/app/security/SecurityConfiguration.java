package test.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	public void configureAuthentication(
			AuthenticationManagerBuilder authenticationManagerBuilder)
			throws Exception {

		authenticationManagerBuilder
				.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public AuthenticationTokenFilter authenticationTokenFilterBean()
			throws Exception {
		AuthenticationTokenFilter authenticationTokenFilter = new AuthenticationTokenFilter();
		authenticationTokenFilter
				.setAuthenticationManager(authenticationManagerBean());
		return authenticationTokenFilter;
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.headers().cacheControl().disable();
		httpSecurity.cors();
		httpSecurity
				.csrf().disable()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authorizeRequests()
				.antMatchers(HttpMethod.POST, "/api/users/auth")
					.permitAll()
				.antMatchers(HttpMethod.GET, "/api/tasks")
					 .permitAll()
				.anyRequest().authenticated();

		// Custom JWT based authentication
		httpSecurity.addFilterBefore(authenticationTokenFilterBean(),
				UsernamePasswordAuthenticationFilter.class);
	}

	@Configuration
	public static class WebConfig implements WebMvcConfigurer {
		@Override
		public void addCorsMappings(CorsRegistry registry) {
			/*registry.addMapping("/api/filmovi")
					.allowedOrigins("http://localhost:3000")
					.allowedMethods("OPTIONS", "HEAD", "GET", "PUT", "POST", "DELETE", "PATCH")
					.allowedHeaders("*")
					.exposedHeaders("Total-Pages")
					.allowCredentials(true)
					.maxAge(3600);*/

			registry.addMapping("/**")
					.allowedOrigins("*")
					.allowedMethods( "HEAD", "GET", "PUT", "POST", "DELETE", "PATCH", "OPTIONS")
					.allowedHeaders("Authorization","content-type")
					.exposedHeaders("Total-Pages")
					.allowCredentials(true)
					.maxAge(3600);
		}
	}
}
