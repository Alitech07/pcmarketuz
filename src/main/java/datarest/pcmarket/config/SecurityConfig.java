package datarest.pcmarket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails director = User.withDefaultPasswordEncoder()
                .username("super_admin")
                .password("1234")
                .roles("SUPER_ADMIN")
//                .authorities("CONTROL_PRODUCT","CONTROL_ORDER","CONTROL_BREND","CONTROL_BLOG","CONTROL_CATEGORY","CONTROL_PRODUCTINFO","CONTROL_REVIEWS")
                .build();
        UserDetails manager = User.withDefaultPasswordEncoder()
                .username("moderator")
                .password("2345")
                .roles("MODERATOR ")
//                .authorities("READ_ALL_PRODUCTS","READ_PRODUCT","DELETE_PRODUCT","EDIT_PRODUCT")
                .build();
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("operator")
                .password("3456")
                .roles("OPERATOR ")
//                .authorities("READ_ALL_ORDERS","READ_ORDER")
                .build();
        return new InMemoryUserDetailsManager(director,manager,user);
    }
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
        return http.build();
    }
}
