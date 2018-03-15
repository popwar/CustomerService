package org.lu.repository;

import java.util.Optional;

import org.lu.entity.CustomerProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerProfileRepository extends
		JpaRepository<CustomerProfileEntity, Long> {

	Optional<CustomerProfileEntity> findByCustomerProfileId(Long id);

}
