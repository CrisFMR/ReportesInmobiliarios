package cl.praxis.ReposrtesInmobiliaria.configure;

import cl.praxis.ReposrtesInmobiliaria.model.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final UserDetailsService userDetailsService;
    private final JwtFilterRequest jwtFilterRequest;

    public SecurityConfig(UserDetailsService userDetailsService, JwtFilterRequest jwtFilterRequest) {
        this.userDetailsService = userDetailsService;
        this.jwtFilterRequest = jwtFilterRequest;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsServiceImpl userDS){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDS);
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        //Tipos de autorizaciones para USER y ADMIN
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((auth) -> auth
                        //----ADMIN & USER ROL ----
                        .requestMatchers("/auth").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/propiedades", "/api/propiedades/{id}").hasAnyRole("ADMIN", "USER") //Vista de Datos
                        //----ADMIN ROL EXCLUSIVE----
                        .requestMatchers(HttpMethod.POST, "/api/propiedades").hasRole("ADMIN") //Enviar Datos
                        .requestMatchers(HttpMethod.PUT, "/api/propiedades").hasRole("ADMIN") //Reemplaza Datos
                        .requestMatchers(HttpMethod.DELETE, "/api/propiedades/{id}").hasRole("ADMIN") //Elimina Datos
                        .anyRequest().authenticated())
                .httpBasic(withDefaults());
        http.addFilterBefore(jwtFilterRequest, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
