package com.estatemarket.realestate.configuration;

import com.estatemarket.realestate.filter.CustomAuthenticationFilter;
import com.estatemarket.realestate.filter.CustomAuthorisationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/EstateMarketplace/login");
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);

        http.authorizeRequests().antMatchers(GET, "/EstateMarketplace/login").permitAll();
        http.authorizeRequests().antMatchers(POST, "/EstateMarketplace/signup").permitAll();

        http.authorizeRequests().antMatchers(GET, "/EstateMarketplace/user/getAll").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers(GET, "/EstateMarketplace/user//getById/id={id}").permitAll();
        http.authorizeRequests().antMatchers(GET, "/EstateMarketplace/user//getByEmail/email={email}").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers(PATCH, "/EstateMarketplace/user/update").permitAll();
        http.authorizeRequests().antMatchers(PATCH, "/EstateMarketplace/user/block/userId={id}").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers(GET, "/EstateMarketplace/user/unblock/userId={id}").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers(PATCH, "/EstateMarketplace/user/delete").permitAll();

        http.authorizeRequests().antMatchers(GET, "EstateMarketplace/estate/getAll").permitAll();
        http.authorizeRequests().antMatchers(GET, "EstateMarketplace/estate/getMyEstate").hasAuthority("CLIENT");
        http.authorizeRequests().antMatchers(GET, "EstateMarketplace/estate/getById/id={id}").permitAll();
        http.authorizeRequests().antMatchers(POST, "EstateMarketplace/estate/create").hasAnyAuthority("CLIENT");
        http.authorizeRequests().antMatchers(PATCH, "EstateMarketplace/estate/update/id={id}").hasAnyAuthority("CLIENT");
        http.authorizeRequests().antMatchers(DELETE, "EstateMarketplace/estate/delete/id={id}").hasAnyAuthority("CLIENT");

        http.authorizeRequests().antMatchers(GET, "EstateMarketplace/deal/showOffers/**").hasAuthority("CLIENT");
        http.authorizeRequests().antMatchers(GET, "EstateMarketplace/deal/showDeals").hasAuthority("REALTOR");
        http.authorizeRequests().antMatchers(POST, "EstateMarketplace/deal/createDeal/**").hasAuthority("CLIENT");
        http.authorizeRequests().antMatchers(POST, "EstateMarketplace/deal/createOffer/**").hasAuthority("REALTOR");
        http.authorizeRequests().antMatchers(DELETE, "EstateMarketplace/deal/deleteOffer/**").hasAuthority("REALTOR");
        http.authorizeRequests().antMatchers(PATCH, "EstateMarketplace/deal/cancelDeal/**").hasAnyAuthority("REALTOR", "CLIENT");
        http.authorizeRequests().antMatchers(PATCH, "EstateMarketplace/deal/closeDeal/**").hasAuthority("REALTOR");


        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorisationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }
}
