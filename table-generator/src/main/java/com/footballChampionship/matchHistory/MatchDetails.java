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
        int homeTeamCompare = homeTeam.compareTo(match.homeTeam);
        if(homeTeamCompare!=0){
            return homeTeamCompare;
        }
        int awayTeamCompare = awayTeam.compareTo(match.awayTeam);
        if(awayTeamCompare!=0){
            return awayTeamCompare;
        }
        return matchDate.compareTo(match.matchDate);
    }
}

