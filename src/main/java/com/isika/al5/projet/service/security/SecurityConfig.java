package com.isika.al5.projet.service.security;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.isika.al5.projet.service.service.imp.UserDetailsServiceImpl;





@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // permet de proteger les methodes (il faut l'activer) 
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
   }

    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
       auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
     //  auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ROLE_ADMIN");
       
   }

    @Bean
    public JwtAuthenticationFilter authenticationTokenFilterBean() throws Exception {
        return new JwtAuthenticationFilter();
    }
    
    

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().// ne pas utiliser le jeton d'authetification (session id) 
                authorizeRequests()
                // mettre la page visiteur /product
                .antMatchers("/api/token/register", "/api/token", "/api/users/**").permitAll()
                //.antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                //.antMatchers("/users/**").hasAuthority("ROLE_USER")
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                
        http
                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
    }

   @Bean
   public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

}