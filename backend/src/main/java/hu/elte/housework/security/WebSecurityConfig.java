package hu.elte.housework.security;

import org.springframework.beans.factory.annotation.Autowired;
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

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    protected void configureAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.PUT, "/api//users/{\\d+}").access("hasRole('ROLE_ADMIN')")
                .antMatchers(HttpMethod.PUT, "/api/users/{\\d+}/set-to-owner").access("hasRole('ROLE_ADMIN')")
                .antMatchers(HttpMethod.PUT,"/api/users/{\\d+}/add-score").access("hasRole('ROLE_OWNER') or hasRole('ROLE_ADMIN')")
                .antMatchers(HttpMethod.PATCH,"/api/users/{\\d+}").access("hasRole('ROLE_ADMIN')")
                .antMatchers(HttpMethod.DELETE, "/api/users/{\\d+}").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/api/users/**").access("hasRole('ROLE_USER') or hasRole('ROLE_OWNER') or hasRole('ROLE_ADMIN')")

                .antMatchers(HttpMethod.POST, "/api/tasks").access("hasRole('ROLE_OWNER') or hasRole('ROLE_ADMIN')")
                .antMatchers(HttpMethod.PUT, "/api/tasks/{\\d+}").access("hasRole('ROLE_OWNER') or hasRole('ROLE_ADMIN')")
                .antMatchers(HttpMethod.PATCH, "/api/tasks/{\\d+}").access("hasRole('ROLE_OWNER') or hasRole('ROLE_ADMIN')")
                .antMatchers(HttpMethod.PUT, "/api/tasks/{\\d+}/categories").access("hasRole('ROLE_OWNER') or hasRole('ROLE_ADMIN')")
                .antMatchers(HttpMethod.DELETE, "/api/tasks/{\\d+}").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/api/tasks/**").access("hasRole('ROLE_USER') or hasRole('ROLE_OWNER') or hasRole('ROLE_ADMIN')")

                .antMatchers(HttpMethod.PUT,"/api/rooms/{\\d+}").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_OWNER')")
                .antMatchers(HttpMethod.PATCH, "/api/rooms/{\\d+}").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_OWNER')")
                .antMatchers(HttpMethod.DELETE, "/api/rooms/{\\d+}").access("hasRole('ROLE_ADMIN')")
                .antMatchers(HttpMethod.GET, "/api/rooms").permitAll()
                .antMatchers("/api/rooms/**").access("hasRole('ROLE_USER') or hasRole('ROLE_OWNER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/h2/**", "/api/register", "/api/login").permitAll()   // important!
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .headers()      // important!
                .frameOptions().disable()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}