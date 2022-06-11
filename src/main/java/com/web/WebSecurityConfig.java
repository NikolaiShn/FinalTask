package com.web;

import com.web.services.UserAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

@EnableWebSecurity(debug = true)
@ComponentScan("com.web")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserAuthenticationService userAuthenticationService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userAuthenticationService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        httpSecurity.csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin/**",
                        "/course/create", "/course/editName", "/course/editCost", "/course/delete", "/course/createReview",
                        "/lessons/create", "/lessons/editName", "/lessons/editCost", "/lessons/delete", "/lessons/createReview",
                        "/sections/create", "/sections/editName", "/sections/delete",
                        "/themes/create", "/themes/editName", "/themes/delete",
                        "/knowledgeDirectory/create", "/knowledgeDirectory/editName", "/knowledgeDirectory/delete", "/knowledgeDirectory/createTheme", "/knowledgeDirectory/createSection")
                .hasRole("ADMIN")
                .antMatchers("/user/award").hasRole("USER")
                .antMatchers("/profile", "/profile/changeName", "/profile/changeLastName",
                        "/user/schedule", "/user/createLesson", "/user/createCourse", "/user/subscribeLesson",
                        "/user/subscribeCourse", "/user/lessonsSortByCost", "/user/lessons",
                        "/user/coursesSortByCost", "/user/courses").authenticated()
                .and()
                .formLogin().defaultSuccessUrl("/")
                .and()
                .logout()
                .and().addFilterBefore(characterEncodingFilter, UsernamePasswordAuthenticationFilter.class);
    }
}