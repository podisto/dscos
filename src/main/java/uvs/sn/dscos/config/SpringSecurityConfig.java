package uvs.sn.dscos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private MyAccessDeniedHandler accessDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests()
			.anyRequest()
			.authenticated()
			.and()
			.formLogin()
			.loginPage("/login")
			.permitAll()
			.defaultSuccessUrl("/")
            .failureUrl("/login?error")
            .usernameParameter("username")
            .passwordParameter("password")
			.and()
			.logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/login")
			.and()
			.exceptionHandling()
			.accessDeniedHandler(accessDeniedHandler);
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("commandant@dscos.sn").password(encoder().encode("commandant")).roles("COMMANDANT")
                .and()
                .withUser("secretaire@dscos.sn").password(encoder().encode("secretaire")).roles("SECRETAIRE")
        		.and()
        		.withUser("adjointcommandant@dscos.sn").password(encoder().encode("adjointcommandant")).roles("ADJOINT_COMMANDANT");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }
    
    @Bean
    public PasswordEncoder encoder() {
    	return new BCryptPasswordEncoder();
    }
}