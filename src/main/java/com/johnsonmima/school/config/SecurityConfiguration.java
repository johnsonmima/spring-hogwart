package com.johnsonmima.school.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //http.csrf().disable()
        http
                .authorizeHttpRequests(
                        (authorize) -> authorize
                                .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll()
                                .requestMatchers("/", "/home").permitAll()
                                .requestMatchers("/contact").permitAll()
                                .requestMatchers("/saveMsg").permitAll()
                                .requestMatchers("/css/**").permitAll()
                                .requestMatchers("/js/**").permitAll()
                                .requestMatchers("/login").permitAll()
                                .requestMatchers("/logout").permitAll()
                                .requestMatchers("/displayMessage").hasRole(
                                        "ADMIN")
                                .requestMatchers("/closeMsg").hasRole("ADMIN")
                                .requestMatchers("/dashboard").authenticated()
                )
                .httpBasic(withDefaults())
                //.formLogin(withDefaults())
                .formLogin((form) -> form.loginPage("/login")
                        .permitAll()
                        .defaultSuccessUrl("/dashboard")
                        .failureUrl("/login?error=true")

                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout=true")
                        .invalidateHttpSession(true)
                        .permitAll()
                );
        http.csrf().disable();
        http.headers().frameOptions().disable();


        return http.build();
    }


    @Bean
    public InMemoryUserDetailsManager userDetailsService() {

        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder().encode("12345"))
                .roles("USER")
                .build();

        UserDetails user1 = User.builder()
                .username("mima")
                .password(passwordEncoder().encode("12345"))
                .roles("ADMIN")
                .build();


        return new InMemoryUserDetailsManager(user, user1);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
