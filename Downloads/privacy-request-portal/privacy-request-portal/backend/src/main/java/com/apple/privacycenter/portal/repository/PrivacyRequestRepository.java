package com.apple.privacycenter.portal.repository;


import com.apple.privacycenter.portal.entity.PrivacyRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrivacyRequestRepository extends JpaRepository<PrivacyRequest, Long> {
    List<PrivacyRequest> findAllByOwnerUsernameOrderByCreatedOnDesc(String ownerUsername);
}