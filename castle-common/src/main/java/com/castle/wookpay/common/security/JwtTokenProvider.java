package com.castle.wookpay.common.security;

import com.castle.wookpay.common.security.dto.MemberDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import javax.crypto.spec.SecretKeySpec;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JwtTokenProvider {
	private final long expirationTime;
	private final Key key;
	private static final String CLAIM_KEY = "USER_INFO";
	public JwtTokenProvider(@Value("${jwt.jwtSecretKey}") String jwtSecretKey,
				@Value("${jwt.expirationTime}") long expirationTime) {
		this.expirationTime = expirationTime;

		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(jwtSecretKey);
		this.key = new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS256.getJcaName());
	}

	public String generateJwtToken(MemberDto memberDto) {
		final Date expireDate = new Date(System.currentTimeMillis() + this.expirationTime);
		JwtBuilder builder = Jwts.builder()
				.claim(CLAIM_KEY, memberDto)
				.setSubject(String.valueOf(memberDto.memberId()))
				.signWith(key, SignatureAlgorithm.HS256)
				.setExpiration(expireDate);
		return builder.compact();
	}

	public boolean validateToken(String token) {
		try{
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
			return true;
		}catch(io.jsonwebtoken.security.SecurityException | MalformedJwtException e){
			log.info("잘못된 JWT 서명입니다.");
		}catch(ExpiredJwtException e){
			log.info("만료된 JWT 토큰입니다.");
		}catch(UnsupportedJwtException e){
			log.info("지원하지 않는 JWT 토큰입니다.");
		}catch(IllegalArgumentException e){
			log.info("JWT 토큰이 잘못되었습니다.");
		}
		return false;
	}

	public MemberDto getMemberDto(String token) {
		Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
		HashMap<String, Object> memberMap = claims.get(CLAIM_KEY, HashMap.class);
		return new MemberDto((String) memberMap.get("memberId"), (String) memberMap.get("email"));
	}
}
