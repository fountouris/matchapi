package com.matchapi.repos;

import com.matchapi.entities.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findByMatchDate(LocalDate matchDate);
}
