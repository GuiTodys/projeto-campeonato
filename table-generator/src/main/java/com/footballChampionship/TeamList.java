package com.footballChampionship;

import com.footballChampionship.matchHistory.MatchDetails;

import java.util.Set;
import java.util.TreeSet;

public class TeamList {

    public static Set<String> createTeamList(Set<MatchDetails> matchDetails){
        Set<String> teamList = new TreeSet<>();
        matchDetails.stream().map(match -> teamList.add(match.getHomeTeam()));
        matchDetails.stream().map(match -> teamList.add(match.getAwayTeam()));

        return teamList;
    }
}
