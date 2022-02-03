package com.footballChampionship.matchHistory;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class MatchHistoryByTeam {
    public static Map<String, List<MatchDetails>> divideMatchHistoryByTeams(Set<MatchDetails> matchHistory){
        Map<String, List<MatchDetails>> matchHistory1 = divideMatchHistoryByHomeTeam(matchHistory);
        Map<String, List<MatchDetails>> matchHistory2 = divideMatchHistoryByAwayTeam(matchHistory);
        return Map<String, List<MatchDetails>> matchHistoryDividedByTeams = matchHistory2.forEach((key,value) -> matchHistory1.merge(key, value, (v1,v2) -> v1.equalsIgnoreCase()));
    }

    public static Map<String, List<MatchDetails>> divideMatchHistoryByHomeTeam(Set<MatchDetails> matchHistory){
        return matchHistory.stream().
                collect(Collectors.
                groupingBy(MatchDetails::getHomeTeam));
    }

    public static Map<String, List<MatchDetails>> divideMatchHistoryByAwayTeam(Set<MatchDetails> matchHistory){
        return matchHistory.stream().
                collect(Collectors.
                        groupingBy(MatchDetails::getAwayTeam));
    }


    public static void printFilteredList(Map<String, List<MatchDetails>> filteredList){
        filteredList.forEach((team, matchHistory) ->{
            System.out.println(team);
            System.out.println(matchHistory);
        });
    }
}
