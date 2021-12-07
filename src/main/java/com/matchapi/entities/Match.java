package com.matchapi.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Entity
@Table(name="match")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "description", length = 64)
    @NotBlank(message = "Match description can not be blank")
    private String description;

    @Column(name="match_date")
    @NotNull(message = "Match date can not be null")
    private LocalDate matchDate;

    @Column(name="match_time")
    @NotNull(message = "Match time can not be null")
    private LocalTime matchTime;

    @Column(name="team_a", length=32)
    @NotBlank(message = "Team A can not be blank")
    private String teamA;

    @Column(name="team_b", length=32)
    @NotBlank(message = "Team B can not be blank")
    private String teamB;


    @Column(name="sport")
    @NotNull(message = "Sport can not be null")
    @Enumerated(EnumType.ORDINAL)
    private Sport sport;

    @OneToMany(mappedBy = "match", fetch = FetchType.EAGER)
    private Set<MatchOdds> matchOdds;

    public Match() {
    }

    public Match(String description, LocalDate matchDate, LocalTime matchTime, String teamA, String teamB, Sport sport ) {
        this.description = description;
        this.matchDate = matchDate;
        this.matchTime = matchTime;
        this.teamA = teamA;
        this.teamB = teamB;
        this.sport = sport;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String name) {
        this.description = name;
    }

    public LocalDate getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(LocalDate matchDate) {
        this.matchDate = matchDate;
    }

    public LocalTime getMatchTime() {
        return matchTime;
    }

    public void setMatchTime(LocalTime matchTime) {
        this.matchTime = matchTime;
    }

    public String getTeamA() {
        return teamA;
    }

    public void setTeamA(String teamA) {
        this.teamA = teamA;
    }

    public String getTeamB() {
        return teamB;
    }

    public void setTeamB(String teamB) {
        this.teamB = teamB;
    }

    public Sport getSport() {return sport;}

    public void setSport(Sport sport) {this.sport = sport;}

    public Set<MatchOdds> getMatchOdds() {return matchOdds;}

    public void setMatchOdds(Set<MatchOdds> matchOdds) {this.matchOdds = matchOdds;}

}
