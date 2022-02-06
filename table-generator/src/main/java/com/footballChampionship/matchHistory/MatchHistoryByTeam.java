package com.footballChampionship.matchHistory;

import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class MatchHistoryByTeam {
    public static Map<String, List<MatchDetails>> divideMatchHistoryByTeams(Set<MatchDetails> matchHistory){
        Map<String, List<MatchDetails>> matchHistoryByHomeTeam = divideMatchHistoryByHomeTeam(matchHistory);
        Map<String, List<MatchDetails>> matchHistoryByAwayTeam = divideMatchHistoryByAwayTeam(matchHistory);
        Map<String, List<MatchDetails>> matchHistoryByTeams = new TreeMap<>();
        matchHistoryByHomeTeam.entrySet().forEach(group -> matchHistoryByTeams.put(group.getKey(),
                                        CollectionUtils.collate(matchHistoryByHomeTeam.get(group.getKey()),
                                        matchHistoryByAwayTeam.get(group.getKey()))));

        return matchHistoryByTeams;
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
