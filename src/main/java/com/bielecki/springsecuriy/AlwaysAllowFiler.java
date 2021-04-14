package com.bielecki.springsecuriy;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.List;

@Component
public class AlwaysAllowFiler  implements Filter {

    //zawiera contextSecurity, czyli jaki jest objekt authentykacji
    private SecurityContextHolderStrategy securityContextHolderStrategy;

    public AlwaysAllowFiler(SecurityContextHolderStrategy securityContextHolderStrategy) {
        this.securityContextHolderStrategy = securityContextHolderStrategy;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        securityContextHolderStrategy.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken("principal"
                ,"credentials"
                        , List.of(new SimpleGrantedAuthority("ROLE_USER"))));

        chain.doFilter(request,response);
    }
}
