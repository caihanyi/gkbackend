package com.chy.gk.repository;

import com.chy.gk.model.uesr.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PlayerRepository extends JpaRepository<Player, Long> , JpaSpecificationExecutor {
}
