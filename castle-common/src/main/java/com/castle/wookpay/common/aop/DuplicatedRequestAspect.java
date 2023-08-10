package com.castle.wookpay.common.aop;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class DuplicatedRequestAspect {


	@Around("@annotation(Test)")
	public Object executeDuplicatedRequest(ProceedingJoinPoint joinPoint) throws Throwable {
//		ServletRequestAttributes requestAttributes =
//				(ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
//
//		HttpServletRequest request = requestAttributes.getRequest();
//
//		final UserCache userCache = localCacheUserDataService.getUserCacheIfNullThrowException(user.getLoginKey(), "LuckyOrbsController.openByTimer()");
//		if (duplicateRequestCheckService.setLuckyOrbsDuplicateRequest(userCache.uid) == false) {
//			throw new DDCCustomException(HttpStatus.BAD_REQUEST.value(), "fail", String.format("Duplicate request. uid=%s, loginKey=%s", userCache.uid, userCache.login_key), null);
//		}
		try {
			System.out.println("###before###");
			return joinPoint.proceed();
		} finally {
			System.out.println("###after###");
		}
//		duplicateRequestCheckService.deleteLuckyOrbsDuplicateRequest(userCache.uid);

	}
}
