package com.mp.shop.configuration;


import com.mp.shop.services.CustomUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final CustomUserDetailService userDetailService;

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(requests ->
                        requests
                                .requestMatchers("/about")
                                .hasRole("ADMIN")
                                .requestMatchers("/registration", "/", "/contacts", "/shop/catalog")
                                .permitAll()
                                .anyRequest()
                                .authenticated())
                .formLogin((form) ->
                        form
                                .loginPage("/login")
                                .defaultSuccessUrl("/")
                                .failureUrl("/login?errors=true")
                                .permitAll())
                .logout(logout->
                        logout
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/")
                                .permitAll());

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityConfiguration() {
        return (web) -> web.ignoring()
                .requestMatchers("/css/**",
                        "/images/**",
                        "/js/**");
    }

    // I injected the custom UserDetailService in the authentication provider.
    @Bean
    public AuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    //Регистрирую провайдера авторизации
    public void authenticationManager(AuthenticationManagerBuilder builder) throws Exception {
        builder.authenticationProvider(authProvider());
    }

}
