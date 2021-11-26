package br.com.bandtec.piteste.config.security;


import br.com.bandtec.piteste.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
@Configuration
@Component
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

    @Autowired
    private AutentificacaoService autentificacaoService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    // configurações de autentificação
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(autentificacaoService).passwordEncoder(new BCryptPasswordEncoder());
    }

    // Configuração de Autorizacao
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors(withDefaults())
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/publicacoes").permitAll()
                // ativo
                // adm
                .antMatchers(HttpMethod.GET, "/publicacoes/**").permitAll()
                .antMatchers(HttpMethod.GET, "/publicacoes/em-analise").hasAuthority("adm")
                .antMatchers(HttpMethod.GET, "/publicacoes/aprovadas").permitAll()
                .antMatchers(HttpMethod.GET, "/publicacoes/{id}").permitAll()
                .antMatchers(HttpMethod.PUT, "/publicacoes/aprovar/{id}").hasAuthority("adm")
                .antMatchers(HttpMethod.DELETE, "/publicacoes/**").permitAll()
                .antMatchers(HttpMethod.GET, "/obras").permitAll()
                .antMatchers(HttpMethod.GET, "/obras/").permitAll()
                .antMatchers(HttpMethod.GET, "/emails/enviar").permitAll()
                .antMatchers(HttpMethod.GET, "/user").permitAll()
                .antMatchers(HttpMethod.POST, "/user").permitAll()
                .antMatchers(HttpMethod.POST, "/auth").permitAll()
                .antMatchers(HttpMethod.GET, "/obras/txt").permitAll()
                .antMatchers(HttpMethod.GET, "/obras/uptxt").permitAll()
                .antMatchers(HttpMethod.GET, "/obras/importar").permitAll()
                .antMatchers(HttpMethod.GET, "/obras/csv").permitAll()
                .antMatchers(HttpMethod.GET, "/interacao/**").permitAll()
                .antMatchers(HttpMethod.POST, "/arquivos/**").permitAll()
                .antMatchers(HttpMethod.GET, "/arquivos/**").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(new AutentificacaoViaTokenFilter(tokenService, usuarioRepository), UsernamePasswordAuthenticationFilter.class);


        //  http.cors().and().cors();
    }

    // configuracao de recursos(js, css, imagens, etc)
    @Override
    public void configure(WebSecurity web) throws Exception {

    }

}
