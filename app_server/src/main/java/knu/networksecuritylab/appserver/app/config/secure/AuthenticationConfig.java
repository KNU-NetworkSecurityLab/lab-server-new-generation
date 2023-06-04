package knu.networksecuritylab.appserver.app.config.secure;

import knu.networksecuritylab.appserver.app.config.jwt.JwtAuthenticationFilter;
import knu.networksecuritylab.appserver.app.exception.handler.auth.CustomAccessDeniedHandler;
import knu.networksecuritylab.appserver.app.exception.handler.auth.CustomAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class AuthenticationConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .httpBasic().disable() // UI 인증이 아닌 토큰 인이기 때문에 basic disable
                .csrf().disable() // Cross-Site Request Forgery 방지
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // jwt 이용 시 사용
                .and()
                .authorizeRequests()

                // permitAll() -> 모두 허용     authenticated() -> 인증 필요     hasRole() -> 권한 필요
                // rest api
                .antMatchers("/api/v1/users/sign-up", "/api/v1/users/sign-in").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/iot/**").permitAll()
                .antMatchers("/api/v1/admin/**").hasRole("ADMIN")

                // web
                .antMatchers("/").permitAll()
                .antMatchers("/member/**").permitAll()
                .antMatchers("/thesis/**").permitAll()
                .antMatchers("/notice/**").permitAll()
                .antMatchers("/activity/**").permitAll()
                .antMatchers("/contact/**").permitAll()

                // css, image 파일들은 모두 허용
                .antMatchers("/css/**").permitAll()
                .antMatchers("/images/**").permitAll()

                // 그 외 모든 요청은 인증 필요
                .anyRequest().authenticated()
                .and()
                // 권한을 확인하는 과정에서 통과하지 못하는 예외가 발생할 경우, 예외를 전달 -> CustomAccessDeniedHandler
                .exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler())
                .and()
                // 인증 과정에서 예외가 발생할 경우, 예외를 전달 -> CustomAuthenticationEntryPoint
                .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                .and()
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
