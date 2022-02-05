package com.footballChampionship.matchHistory;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@Builder
@EqualsAndHashCode
@Getter
@ToString

public class MatchDetails implements Comparable<MatchDetails>{
    private String homeTeam;
    private String awayTeam;
    private int homeTeamScore;
    private int awayTeamScore;
    private LocalDate matchDate;

    @Override
    public int compareTo(MatchDetails match) {
        int matchDateCompare = matchDate.compareTo(match.matchDate);
        if(matchDateCompare!=0){
            return matchDateCompare;
        }
        int homeTeamCompare = homeTeam.compareTo(match.homeTeam);
        if(matchDateCompare!=0){
            return homeTeamCompare;
        }
        return awayTeam.compareTo(match.awayTeam);
    }
}

