package com.footballChampionship.matchHistory;

import org.apache.commons.collections4.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class MatchHistoryByTeam {
    public static Map<String, List<MatchDetails>> divideMatchHistoryByTeams(Set<MatchDetails> matchHistory){
        Map<String, List<MatchDetails>> matchHistory1 = divideMatchHistoryByHomeTeam(matchHistory);
        Map<String, List<MatchDetails>> matchHistory2 = divideMatchHistoryByAwayTeam(matchHistory);
        Map<String, List<MatchDetails>> completeList = new HashMap<>();
        matchHistory1.entrySet().forEach(group -> completeList.put(group.getKey(),
                                        CollectionUtils.collate(matchHistory1.get(group.getKey()),
                                        matchHistory2.get(group.getKey()))));

        return completeList;
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
