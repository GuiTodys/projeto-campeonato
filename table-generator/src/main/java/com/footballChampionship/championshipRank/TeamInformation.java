package com.footballChampionship.championshipRank;

import com.footballChampionship.matchHistory.MatchDetails;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class TeamInformation{
    private String teamName;
    private Integer wins = 0;
    private Integer draws = 0;
    private Integer defeats = 0;
    private Integer score = 0;
    private Integer numberOfMatchesPlayed = 0;

    public TeamInformation(String teamName) {
        this.teamName = teamName;
    }

    public void increaseWinsNumber(){
        this.wins++;
        this.numberOfMatchesPlayed++;
    }

    public void increaseDefeatsNumber(){
        this.defeats++;
        this.numberOfMatchesPlayed++;
    }

    public void increaseDrawsNumber(){
        this.draws++;
        this.numberOfMatchesPlayed++;
    }

    public void calculateScore(){
        this.score = this.wins*3 + this.draws;
    }

    public boolean isTeamHomeTeam(String teamName, MatchDetails match){
        return teamName.equals(match.getHomeTeam());
    }

    public boolean isTeamAwayTeam(String teamName, MatchDetails match){
        return teamName.equals(match.getAwayTeam());
    }

    public void updateStatusIfHomeTeam(MatchDetails match){
        if (match.getHomeTeamScore()>match.getAwayTeamScore()){
            this.increaseWinsNumber();
        } else if(match.getHomeTeamScore()==match.getAwayTeamScore()){
            this.increaseDrawsNumber();
        } else{
            this.increaseDefeatsNumber();
        }
    }

    public void updateStatusIfAwayTeam(MatchDetails match){
        if (match.getAwayTeamScore()>match.getHomeTeamScore()){
            this.increaseWinsNumber();
        } else if(match.getAwayTeamScore()==match.getHomeTeamScore()){
            this.increaseDrawsNumber();
        } else{
            this.increaseDefeatsNumber();
        }
    }
}
