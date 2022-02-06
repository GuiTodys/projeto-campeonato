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
    private Integer homeTeamScore;
    private Integer awayTeamScore;
    private LocalDate matchDate;

    @Override
    public int compareTo(MatchDetails match) {
        int matchDateCompare = matchDate.compareTo(match.matchDate);
        if(matchDateCompare!=0){
            return matchDateCompare;
        }
        int homeTeamCompare = homeTeam.compareTo(match.homeTeam);
        if(homeTeamCompare!=0){
            return homeTeamCompare;
        }
        int awayTeamCompare = awayTeam.compareTo(match.awayTeam);
        if(awayTeamCompare!=0){
            return awayTeamCompare;
        }
        int homeTeamScoreCompare = homeTeamScore.compareTo(match.homeTeamScore);
        if(homeTeamScoreCompare!=0){
            return homeTeamScoreCompare;
        }
        return awayTeamScore.compareTo(match.awayTeamScore);
    }
}

