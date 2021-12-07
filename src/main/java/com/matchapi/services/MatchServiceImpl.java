package com.matchapi.services;

import com.matchapi.dtos.MatchDTO;
import com.matchapi.dtos.MatchOddsDTO;
import com.matchapi.entities.Match;
import com.matchapi.entities.MatchOdds;
import com.matchapi.entities.Sport;
import com.matchapi.repos.MatchOddsRepository;
import com.matchapi.repos.MatchRepository;
import com.matchapi.utils.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MatchServiceImpl implements MatchService{

    @Autowired
    private MatchRepository matchRepository;
    @Autowired
    private MatchOddsRepository matchOddsRepository;


    public List<MatchDTO> findAll(){
        return matchRepository.findAll()
                .stream().map(x->ConvertUtil.convertMatchToDTO(x)).collect(Collectors.toList());
    }

    public List<MatchDTO> findByDate(LocalDate matchDate){
        return matchRepository.findByMatchDate(matchDate)
                .stream().map(x->ConvertUtil.convertMatchToDTO(x)).collect(Collectors.toList());
    }

    public Optional<MatchDTO> findById(long id) {
        Optional<Match> match = matchRepository.findById(id);
        if (!match.isPresent()){
            return Optional.empty();
        }

        return Optional.ofNullable(ConvertUtil.convertMatchToDTO(match.get()));

    }

    public void createMatch(MatchDTO newMatchDTO){
        matchRepository.save(new Match(newMatchDTO.getDescription(),
                                        newMatchDTO.getMatchDate(),
                                        newMatchDTO.getMatchTime(),
                                        newMatchDTO.getTeamA(),
                                        newMatchDTO.getTeamB(),
                                        Sport.fromInteger(newMatchDTO.getSport())
                        ));

    }


    public Optional<MatchDTO> updateMatch(long id, MatchDTO newMatchData){
        Optional<Match> matchEntity = matchRepository.findById(id);
        if (!matchEntity.isPresent())  {return Optional.empty();}

        Match oldMatch = matchEntity.get();
        oldMatch.setDescription(newMatchData.getDescription());
        oldMatch.setMatchDate(newMatchData.getMatchDate());
        oldMatch.setMatchTime(newMatchData.getMatchTime());
        oldMatch.setTeamA(newMatchData.getTeamA());
        oldMatch.setTeamB(newMatchData.getTeamB());
        oldMatch.setSport(Sport.fromInteger(newMatchData.getSport()));

        return Optional.ofNullable(ConvertUtil.convertMatchToDTO(matchRepository.save(oldMatch)));
}

    public void deleteById(long id) {
         matchRepository.deleteById(id);
    }

    public List<MatchOddsDTO> getAllOddsByMatchId(long id){
        Optional<Match> match = matchRepository.findById(id);
        if (!match.isPresent()){
            return Collections.EMPTY_LIST;
        }
        return match.get().getMatchOdds().stream().map(x->ConvertUtil.convertMatchOddsToDTO(x)).collect(Collectors.toList());
    }

    public void createMatchOdds(Long matchId, MatchOddsDTO newMatchOddsDTO){
        Match match  = matchRepository.findById(matchId).orElse(null);

        if (Objects.isNull(match)) return;

        MatchOdds newMatchOdds = new MatchOdds(newMatchOddsDTO.getMatchId(),
                newMatchOddsDTO.getSpecifier(),
                newMatchOddsDTO.getOdd()
        );

        match.getMatchOdds().add(newMatchOdds);

        matchOddsRepository.save(newMatchOdds);
        matchRepository.save(match);
    }


}
