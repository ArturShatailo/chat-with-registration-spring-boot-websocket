package com.websocket.onlinechat.authentification.registration.repository;

import com.websocket.onlinechat.authentification.registration.domain.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {

    Optional<ConfirmationToken> findConfirmationTokenByToken (String token);
}
