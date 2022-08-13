package com.sedatbsp.asu.infrastructure.configuration.security.jwt;

import com.sedatbsp.asu.domain.common.exception.UrlBusinessException;
import com.sedatbsp.asu.infrastructure.configuration.security.utils.CookieUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String token = jwtProvider.resolveToken(request);

        getCookieFromRequest(request,response);

        try {
            if (token != null && jwtProvider.validateToken(token)) {
                Authentication auth = jwtProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (UrlBusinessException otmBusinessException) {
            SecurityContextHolder.clearContext();
            response.sendError(422, otmBusinessException.getMessage());
            return;
        }

        filterChain.doFilter(request, response);
    }

    private void getCookieFromRequest(HttpServletRequest request, HttpServletResponse response){
        String redirectUriAfterLogin = request.getParameter("redirect_uri");
        if (StringUtils.isNotBlank(redirectUriAfterLogin)) {
            CookieUtils.addCookie(response, "redirect_uri", redirectUriAfterLogin, 35000);
        }
    }

}
