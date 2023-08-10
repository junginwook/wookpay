package com.castle.wookpay.membership.security;

import com.castle.wookpay.membership.application.service.dto.MemberDto;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

	private static final String BEARER =  "Bearer ";
	private final JwtTokenProvider jwtTokenProvider;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

		String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (authorization != null && authorization.startsWith(BEARER)) {
			//Authorization 헤더에서 토큰 획득
			String token = authorization.substring(7);

			//토큰 유효성 검사
			if(!jwtTokenProvider.validateToken(token)) {
				doFilter(request, response, filterChain);
			}

			//토큰에서 유저 정보 획득
			MemberDto memberDto = jwtTokenProvider.getMemberDto(token);
			if (memberDto != null) {
				Authentication authentication = new UsernamePasswordAuthenticationToken(
						memberDto,
						null,
						null
				);

				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}
		doFilter(request, response, filterChain);
	}
}
