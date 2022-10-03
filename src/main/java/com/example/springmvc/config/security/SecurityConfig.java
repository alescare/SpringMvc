package com.example.springmvc.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("customUserDetailsService")
    private UserDetailsService userDetailsService;


    @Autowired
    DataSource dataSource;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }


    /*@Bean
    public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowUrlEncodedSlash(true);
        firewall.setAllowSemicolon(true);

        return firewall;
    }*/

    @Override
    public void configure(WebSecurity web) throws Exception {

        super.configure(web);
        //web.httpFirewall(allowUrlEncodedSlashHttpFirewall());

    }

    @Override
    public void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();

        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return authenticationProvider;
    }

    private static final String[] ADMIN_UTENTI_MATCHER =
            {
                    "/utente/aggiungi_utente",
                    "/utente/cancella_utente",
                    "/utente/gestione_utenti"
            };

    private static final String[] ADMIN_PRENOTAZIONI_MATCHER =
            {
                    "/prenotazione/prenotazioni_da_approvare",
                    "/prenotazione/approva_prenotazione",
                    "/prenotazione//prenotazioni_da_cancellare"
            };

    private static final String[] ADMIN_AUTO_MATCHER =
            {
                    "/auto/aggiungi/**",
                    "/auto/modifica/**",
                    "/auto/elimina/**"
            };

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/index/**").permitAll()
                .antMatchers("/").hasAnyRole("ANONYMOUS", "USER")
                .antMatchers("/").hasAnyRole("ANONYMOUS", "USER")
                .antMatchers(ADMIN_AUTO_MATCHER).access("hasRole('ADMIN')")
                .antMatchers(ADMIN_UTENTI_MATCHER).access("hasRole('ADMIN')")
                .antMatchers(ADMIN_PRENOTAZIONI_MATCHER).access("hasRole('ADMIN')")
                .antMatchers("/**").permitAll()
                .and()
                .addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                .loginPage("/index")
                .loginProcessingUrl("/utente/home")
                .failureUrl("/login/form?error")
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/login/form?forbidden")
                .and()
                .logout()
                .logoutUrl("/login/form?logout");
    }

    public AuthenticationFilter authenticationFilter()
            throws Exception {

        AuthenticationFilter filter = new AuthenticationFilter();

        filter.setAuthenticationManager(authenticationManagerBean());
        filter.setAuthenticationFailureHandler(failureHandler());
        filter.setAuthenticationSuccessHandler(authenticationSuccessHandler());

        return filter;

    }

    public SimpleUrlAuthenticationFailureHandler failureHandler() {
        return new SimpleUrlAuthenticationFailureHandler("/login/form?error");
    }

    @Bean
    public SavedRequestAwareAuthenticationSuccessHandler authenticationSuccessHandler() {
        SavedRequestAwareAuthenticationSuccessHandler auth = new SavedRequestAwareAuthenticationSuccessHandler();
        auth.setTargetUrlParameter("/utente/home");

        return auth;
    }


}


