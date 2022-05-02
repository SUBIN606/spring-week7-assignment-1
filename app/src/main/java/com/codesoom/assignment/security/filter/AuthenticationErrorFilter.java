package com.codesoom.assignment.security.filter;

import com.codesoom.assignment.exceptions.InvalidTokenException;
import com.codesoom.assignment.exceptions.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/** 토큰 검증 필터에서 발생한 예외를 처리합니다. */
@Slf4j
public class AuthenticationErrorFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest request,
                            HttpServletResponse response,
                            FilterChain chain) throws IOException, ServletException {
        try {
            chain.doFilter(request, response);
        } catch (InvalidTokenException e) {
            log.error("유효하지 않은 토큰 입력");
            response.sendError(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
        }
    }

}
