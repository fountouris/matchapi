package com.matchapi.controllers;

import com.matchapi.dtos.MatchDTO;
import com.matchapi.dtos.MatchOddsDTO;
import com.matchapi.services.MatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MatchController {

   final MatchService matchService;
    private static final Logger logger = LoggerFactory.getLogger(MatchController.class);


    @Autowired
    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping("/matches")
    public ResponseEntity<List<MatchDTO>> getAllMatches(@RequestParam(required = false) LocalDate matchDate) {
        try {
            List<MatchDTO> matches;

            if (Objects.isNull(matchDate))
                matches = matchService.findAll();
            else
                matches = matchService.findByDate(matchDate);

            return new ResponseEntity<>(matches, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/matches/{id}")
    public ResponseEntity<MatchDTO> getMatchById(@PathVariable("id") long id) {
        try {
            Optional<MatchDTO> match = matchService.findById(id);

            if (match.isPresent()) {
                return new ResponseEntity<>(match.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/matches")
    public ResponseEntity<MatchDTO> createMatch(@RequestBody MatchDTO matchDTO) {
        try {
            matchService.createMatch(matchDTO);
            return new ResponseEntity<>(matchDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @PutMapping("/matches/{id}")
    public ResponseEntity<MatchDTO> updateMatch(@PathVariable("id") long id, @RequestBody MatchDTO newMatchData) {
       try {
           Optional<MatchDTO> existingMatch = matchService.updateMatch(id, newMatchData);

           if (existingMatch.isPresent()) {
               return new ResponseEntity<>(existingMatch.get(), HttpStatus.OK);
           } else {
               return new ResponseEntity<>(HttpStatus.NOT_FOUND);
           }
       }
       catch (Exception e) {
           logger.error(e.getMessage());
           return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    @DeleteMapping("/matches/{id}")
    public ResponseEntity<HttpStatus> deleteMatch(@PathVariable("id") long id) {
        try {
            matchService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/matches/{id}/odds")
    public ResponseEntity<List<MatchOddsDTO>> getAllOddsByMatchId(@PathVariable("id") long id) {
        try {
            Optional<MatchDTO> match = matchService.findById(id);
            if (!match.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            List<MatchOddsDTO> allOddsforMatchId = matchService.getAllOddsByMatchId(id);
            return new ResponseEntity<>(allOddsforMatchId, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/matches{id}/odds")
    public ResponseEntity<MatchOddsDTO> createMatchOdds(@PathVariable("id") long matchId, @RequestBody MatchOddsDTO matchOddsDTO) {
        try {
            if (!matchService.findById(matchId).isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            matchService.createMatchOdds(matchId,matchOddsDTO);
            return new ResponseEntity<>(matchOddsDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
