package com.matchapi.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name="matchodds")
public class MatchOdds {


    public MatchOdds() {
    }


    public MatchOdds(Long id, String specifier, BigDecimal odd) {
        this.id = id;
        this.specifier = specifier;
        this.odd = odd;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="match_id")
    private Match match;

    @Column(name = "specifier", length = 64)
    @NotBlank(message = "Team B can not be blank")
    private String specifier;

    @Column(name="odd", precision =8, scale =2)
    @NotNull(message = "odd can not be null")
    private BigDecimal odd;

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getSpecifier() {return specifier;}

    public void setSpecifier(String specifier) {this.specifier = specifier;}

    public BigDecimal getOdd() {return odd;}

    public void setOdd(BigDecimal odd) {this.odd = odd;}

    public Match getMatch() {return match;}

    public void setMatch(Match match) {this.match = match; }

}