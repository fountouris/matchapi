package com.matchapi.services;


import com.matchapi.dtos.MatchDTO;
import com.matchapi.dtos.MatchOddsDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MatchService {

    List<MatchDTO> findAll();
    List<MatchDTO> findByDate(LocalDate matchDate);
    Optional<MatchDTO> findById(long id);
    void createMatch(MatchDTO newMarchData);
    Optional updateMatch(long id, MatchDTO newMatchData);
    void deleteById(long id);
    List<MatchOddsDTO> getAllOddsByMatchId(long id);
    void createMatchOdds(Long matchId, MatchOddsDTO matchOddsDTO);

}
