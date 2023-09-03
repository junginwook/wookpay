package com.castle.wookpay.membership.domain.repository;

import com.castle.wookpay.membership.domain.entity.MembershipJpaEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SpringDataMembershipRepository extends JpaRepository<MembershipJpaEntity, Long> {

	boolean existsByEmail(String email);

	@Query("SELECT m FROM MembershipJpaEntity m WHERE m.email = :email")
	Optional<MembershipJpaEntity> findByEmail(@Param("email") String email);
}
