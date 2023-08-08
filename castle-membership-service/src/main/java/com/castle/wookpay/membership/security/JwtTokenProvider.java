package com.castle.wookpay.membership.security;

import java.util.HashMap;
import java.util.Map;

public class JwtTokenProvider {

	private static final String jwtSecretKey = "exampleSecretKey";

//	public static String generateJwtToken(MemberPrincipal memberPrincipal) {
//		JwtBuilder builder = Jwts.builder()
//				.setHeader(createHeader())
//				.setClaims()
//				.setSubject()
//				.signWith()
//				.setExpiration();
//		return null;
//	}

	private static Map<String, Object> createHeader() {
		Map<String, Object> header = new HashMap<>();

		header.put("typ", "JWT");
		header.put("alg", "HS256");
		header.put("regDate", System.currentTimeMillis());
		return header;
	}
}
