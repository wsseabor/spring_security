package com.example.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

/* This is most likely where the issue lies, with the old web security configurer adapter
 * deprecated in spring boot 2.7.0 +, spring security documentation suggests to move to 
 * component-based web security declaring @Bean types instead of overriding methods found
 * in the superclass WebSecurityConfigurerAdapter, more than likely the problem is found in
 * the securityFilterChain method, I've messed with it quite a bit but still can't seem to get it to work.
 * When starting up spring in the terminal with ./mvnw spring-boot:run a default user-generated security password
 * is provided with defualt username as "user", once logged in with that the idea is to have an endpoint of the
 * /users template be the only authenticated page, but this does not seem to work.
 */

public class SecurityConfiguration {

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();

    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
         
        return authProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authenticationProvider(authenticationProvider());
        http.authorizeHttpRequests()
            .antMatchers("/users").authenticated()
            .anyRequest().permitAll()
            .and()
            .formLogin()
                .usernameParameter("email")
                .defaultSuccessUrl("/users")
                .permitAll()
            .and()
            .logout().logoutSuccessUrl("/").permitAll();

        return http.build();

    }
    
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/css/**", "/images/**", "/js/**", "/webjars/**");
    }
}
