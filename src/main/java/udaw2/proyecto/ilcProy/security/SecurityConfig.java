package udaw2.proyecto.ilcProy.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
        @Bean
        public AuthenticationManager authenticationManager(
                        AuthenticationConfiguration authenticationConfiguration)
                        throws Exception {
                return authenticationConfiguration.getAuthenticationManager();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http.headers(
                                headersConfigurer -> headersConfigurer
                                                .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));
                http.authorizeHttpRequests(auth -> auth
                                .requestMatchers("/categorias/**").hasAnyRole("MANAGER", "ADMIN")
                                .requestMatchers("/clientes/clientesView").hasRole("ADMIN")
                                .requestMatchers("/pedidos/**").authenticated()
                                .requestMatchers("/pedidos/pedidoListView").hasAnyRole("MANAGER", "ADMIN")
                                .requestMatchers("/productos/editProductView", "/productos/newProductView").hasAnyRole("MANAGER", "ADMIN")
                                .requestMatchers("/public/signoutView").authenticated()
                                .requestMatchers("/", "/public/**", "/productos/**", "/api/productos").permitAll()
                                .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                                .permitAll()
                                .anyRequest().permitAll())
                                .formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer
                                                .loginPage("/public/signin")
                                                .loginProcessingUrl("/login")
                                                .failureUrl("/public/signin?error")
                                                .defaultSuccessUrl("/productos/list", true)
                                                .permitAll())
                                .logout((logout) -> logout
                                                .logoutSuccessUrl("/").permitAll())
                                .httpBasic(Customizer.withDefaults());
                http.exceptionHandling(exceptions -> exceptions.accessDeniedPage("/public"));
                return http.build();
        }
}

