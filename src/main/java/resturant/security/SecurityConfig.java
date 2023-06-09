package resturant.security;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import resturant.security.error.CustomOAuth2AccessDeniedHandler;
import resturant.security.error.CustomOAuth2AuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;
import java.util.List;

@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        System.out.println("CALLED");
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("http://localhost:63342");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();
        http
                .cors().and().csrf().disable()

                .httpBasic(Customizer.withDefaults())
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling((exceptions) -> exceptions
                        .authenticationEntryPoint(new CustomOAuth2AuthenticationEntryPoint())
                        .accessDeniedHandler(new CustomOAuth2AccessDeniedHandler())
                )
                .oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(authenticationConverter());

        http.authorizeHttpRequests((authorize) -> authorize
                .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()



                .requestMatchers("/h2*/**").permitAll()

                .requestMatchers("/").permitAll() //Allow the default index.html file

                .requestMatchers("/session-demo.html").permitAll()
                .requestMatchers("/api/cookie/**").permitAll()

                .requestMatchers(HttpMethod.GET, "/menu/menus").permitAll()
                .requestMatchers(HttpMethod.GET, "/menu/{menuId}").permitAll()
                .requestMatchers(HttpMethod.GET, "/menu/{menuId}/items").permitAll()
                .requestMatchers(HttpMethod.POST, "/menu").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/menu/{menuId}").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/menu/{menuId}").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.GET, "/menu/{menuId}/items").permitAll()
                .requestMatchers(HttpMethod.PUT, "/menu/{menuId}/updateMenuAndItems").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.GET, "/menuItem/menuItems").permitAll()
                .requestMatchers(HttpMethod.POST, "/menuItem/postMenuItem").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/menuItem/menuItems/{id}").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/menuItem/updateMenuItems").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/menuItem/deleteMenuItem/{id}").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.GET, "/menuItem/{menuItemId}").permitAll()
                .requestMatchers(HttpMethod.GET, "/image/{id}").permitAll()
                .requestMatchers(HttpMethod.POST, "/image").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/image/{id}").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/image").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/image/{id}").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/demo/anonymous").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/demo/authenticated").authenticated()
                .requestMatchers(HttpMethod.GET, "/api/demo/user-admin").hasAnyAuthority("USER", "ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/demo/admin").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/demo/user").hasAuthority("USER")
                .requestMatchers(HttpMethod.GET, "/api/demo/user-fromtoken").permitAll()
                .requestMatchers(HttpMethod.POST, "/generate-pdf").permitAll()

                .requestMatchers("/error").permitAll()


                .requestMatchers("/h2-console").permitAll()

                .requestMatchers("/favicon.ico").permitAll()
                .anyRequest().authenticated());

        return http.build();
    }

    @Bean
    public JwtAuthenticationConverter authenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("roles");
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("");
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return jwtAuthenticationConverter;
    }

    @Value("${app.secret-key}")
    private String secretKey;
    public static String tokenSecret;

    @Value("${app.secret-key}")
    public void setStaticValue(String secretKey) {
        SecurityConfig.tokenSecret = secretKey;
    }

    @Bean
    public JwtEncoder jwtEncoder() throws JOSEException {
        System.out.println("1) ---> " + tokenSecret);
        SecretKey key = new SecretKeySpec(tokenSecret.getBytes(), "HmacSHA256");
        JWKSource<SecurityContext> immutableSecret = new ImmutableSecret<SecurityContext>(key);
        return new NimbusJwtEncoder(immutableSecret);
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        System.out.println("2) ---> " + tokenSecret);
        SecretKey originalKey = new SecretKeySpec(tokenSecret.getBytes(), "HmacSHA256");
        NimbusJwtDecoder jwtDecoder = NimbusJwtDecoder.withSecretKey(originalKey).build();
        return jwtDecoder;
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


}
