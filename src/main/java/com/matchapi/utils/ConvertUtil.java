package com.matchapi.utils;

import com.matchapi.dtos.MatchDTO;
import com.matchapi.dtos.MatchOddsDTO;
import com.matchapi.entities.Match;
import com.matchapi.entities.MatchOdds;

public class ConvertUtil {

    public static MatchDTO convertMatchToDTO(Match matchEntity){
        MatchDTO matchDTO = new MatchDTO();
        matchDTO.setId(matchEntity.getId());
        matchDTO.setDescription(matchEntity.getDescription());
        matchDTO.setMatchDate(matchEntity.getMatchDate());
        matchDTO.setMatchTime(matchEntity.getMatchTime());
        matchDTO.setTeamA(matchEntity.getTeamA());
        matchDTO.setTeamB(matchEntity.getTeamB());
        matchDTO.setSport(matchDTO.getSport());
        return matchDTO;
    }

    public static MatchOddsDTO convertMatchOddsToDTO(MatchOdds matchOddsEntity){
        MatchOddsDTO matchOddsDTO = new MatchOddsDTO();
        matchOddsDTO.setId(matchOddsEntity.getId());
        matchOddsDTO.setMatchId(matchOddsEntity.getMatch().getId());
        matchOddsDTO.setSpecifier(matchOddsDTO.getSpecifier());
        matchOddsDTO.setOdd(matchOddsDTO.getOdd());
        return matchOddsDTO;
    }

}
