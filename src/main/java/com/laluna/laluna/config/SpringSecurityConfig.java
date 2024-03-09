package com.laluna.laluna.config;

import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SpringSecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .cors(cors -> cors.disable())
                .authorizeHttpRequests(request -> request
                        .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                        .requestMatchers("/css/**", "/images/**", "/js/**").permitAll() // static resources
                        .requestMatchers("/view/join","/auth/join", "/home","/boards/list","/boards/read/**", "/boards/category/**").permitAll()
                        .anyRequest().authenticated() // 어떠한 요청이라도 인증필요
                )
                .formLogin(login -> login	// form 방식 로그인 사용
                        .loginPage("/view/login")   //커스텀 로그인 페이지 지정
                        .loginProcessingUrl("/login-process") //submit 받을 url .html파일에 action으로 설정
                        .usernameParameter("memberid")     //submit 할 아이디 .html파일에 name으로 설정
                        .passwordParameter("memberpassword") //submit 할  비밀번호 .html파일에 name으로 설정
                        .defaultSuccessUrl("/home" )	// 성공 시 dashboard로
                        .permitAll()	// 대시보드 이동이 막히면 안되므로 얘는 허용
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/home"));	// 로그아웃은 기본설정으로 (/logout으로 인증해제)

        return http.build();
    }
}
