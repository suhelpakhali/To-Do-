package net.suhel.springboot.todo.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
@AllArgsConstructor
public class SpringSecurityConfig {

  private   UserDetailsService userDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http.csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests((authorize)-> {
                    // Role Based Authorisation can be achieved by 2 ways, 1 is authorize.requestMtchers other is using Annotations
                /*    authorize.requestMatchers(HttpMethod.POST,"/api/**").hasRole("ADMIN");
                    authorize.requestMatchers(HttpMethod.PUT,"/api/**").hasRole("ADMIN");
                    authorize.requestMatchers(HttpMethod.DELETE,"/api/**").hasRole("ADMIN");
                //  authorize.requestMatchers(HttpMethod.GET,"api/**").hasAnyRole("ADMIN","USER");
                    authorize.requestMatchers(HttpMethod.PATCH,"/api/**").hasAnyRole("ADMIN","USER");
                 */

          //      authorize.requestMatchers(HttpMethod.GET,"/api/**").permitAll();
                    authorize.anyRequest().authenticated();
                }).httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
public AuthenticationManager authenticationManager (AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
}

 /*
    // In-Memory Authorization
    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsService userDetailsService(){
        UserDetails Suhel = User.builder()
                .username("Suhel")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build();

        UserDetails Sanket = User.builder()
                .username("Sanket")
                .password(passwordEncoder().encode("password"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(Suhel,Sanket);




    }

  */
}
