package com.example.springmvc.config.security;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Qualifier("customUserDetailsService")
    private final UserDetailsService userDetailsService;

    private final DataSource dataSource;

    public SecurityConfig(UserDetailsService userDetailsService, DataSource dataSource) {
        this.userDetailsService = userDetailsService;
        this.dataSource = dataSource;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
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
                    "/utente/aggiungi_utente**",
                    "/utente/cancella_utente**",
                    "/utente/gestione_utenti**",
                    "/utente/aggiungi_utente",
                    "/utente/prenotazioni_da_approvare",
                    "/utente/prenotazioni_da_cancellare"
            };

    private static final String[] ADMIN_PRENOTAZIONI_MATCHER =
            {
                    "/prenotazione/prenotazioni_da_approvare**",
                    "/prenotazione/approva_prenotazione**",
                    "/prenotazione/prenotazioni_da_cancellare**"
            };

    private static final String[] ADMIN_AUTO_MATCHER =
            {
                    "/auto/gestione_auto",
                    "/auto/gestione_modifiche_auto",
                    "/auto/elimina_auto**",
                    "/auto/modifica_auto_{autoId}"
            };
    private static final String[] USER_UTENTI_MATCHER =
            {
                    "/utente/profilo",
                    "/utente/modifica_credenziali**",
                    "/utente/prenota_auto**"

            };

    private static final String[] USER_PRENOTAZIONI_MATCHER =
            {
                    "/prenotazione/auto_disponibili",
                    "/prenotazione/prenota_auto**",

            };


    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/index/**").permitAll()
                .antMatchers(ADMIN_AUTO_MATCHER).access("hasRole('ADMIN')")
                .antMatchers(ADMIN_UTENTI_MATCHER).access("hasRole('ADMIN')")
                .antMatchers(ADMIN_PRENOTAZIONI_MATCHER).access("hasRole('ADMIN')")
                .antMatchers(USER_UTENTI_MATCHER).access("hasRole('USER')")
                .antMatchers(USER_PRENOTAZIONI_MATCHER).access("hasRole('USER')")
                .and()
                .addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/performLogin")
                .successHandler(authenticationSuccessHandler())
                .failureUrl("/login/form?error")
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/login/form?forbidden")
                .and()
                .logout()
                .logoutUrl("/login/form?logout")
                .clearAuthentication(true);
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



