package org.lu.repository;

import org.lu.entity.CustomerAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<CustomerAddressEntity, Long> {

}
