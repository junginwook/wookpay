package com.castle.wookpay.membership.adapter.out.persistence;

import com.castle.wookpay.common.annotation.PersistenceAdapter;
import com.castle.wookpay.membership.domain.entity.MembershipJpaEntity;
import com.castle.wookpay.membership.adapter.out.persistence.repository.SpringDataMembershipRepository;
import com.castle.wookpay.membership.application.port.out.ValidateMembershipPort;
import com.castle.wookpay.membership.application.port.out.LoginMembershipPort;
import com.castle.wookpay.membership.application.port.out.RegisterMembershipPort;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@PersistenceAdapter
public class MembershipPersistenceAdapter implements RegisterMembershipPort, LoginMembershipPort, ValidateMembershipPort {

	private final SpringDataMembershipRepository springDataMembershipRepository;

	private final PasswordEncoder passwordEncoder;

	@Override
	public boolean existMemberEmail(String email) {
		return springDataMembershipRepository.existsByEmail(email);
	}

	@Override
	public MembershipJpaEntity registerMembership(String name, String password, String email, String address) {
		return springDataMembershipRepository.save(MembershipJpaEntity.of(name, email, passwordEncoder.encode(password), address, true, false));
	}

	@Override
	public Optional<MembershipJpaEntity> findMembershipByEmail(String email) {
		return springDataMembershipRepository.findByEmail(email);
	}

	@Override
	public Optional<MembershipJpaEntity> findMembershipById(String memberId) {
		return springDataMembershipRepository.findById(Long.parseLong(memberId));
	}
}
