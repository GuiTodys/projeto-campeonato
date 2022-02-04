package com.footballChampionship.championshipRank;

import com.footballChampionship.matchHistory.MatchDetails;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;

import java.util.*;

public class ChampionshipTableGenerator{

    public static Set<TeamInformation> generateClassificationTable(Map<String, List<MatchDetails>> matchHistoryByTeam){
        Set<TeamInformation> classificationTable = new TreeSet<>(Comparator.
                                                                            comparing(TeamInformation::getScore, Comparator.reverseOrder()).
                                                                            thenComparing(TeamInformation::getTeamName));

        matchHistoryByTeam.entrySet().forEach( group -> {

            String teamName = group.getKey();
            TeamInformation teamInformation= new TeamInformation(teamName);

            group.getValue().forEach(match ->{
                // Caso em que o time é igual ao time da casa
                if(teamInformation.isTeamHomeTeam(teamName, match)){
                    teamInformation.updateStatusIfHomeTeam(match);
                }
                if(teamInformation.isTeamAwayTeam(teamName, match)){
                    teamInformation.updateStatusIfAwayTeam(match);
                }
            });
            teamInformation.calculateScore();
            classificationTable.add(teamInformation);
        });
        return  classificationTable;
    }

    public static void printClassificationTable(Set<TeamInformation> classificationTable){
        classificationTable.forEach((teamInformation) ->{
            System.out.println(teamInformation);
        });
    }

    private static void organizeClassificationTableByPoints(Map<String, TeamInformation> classificationTable){
        BidiMap<String, TeamInformation> classificationTableInBM = new DualHashBidiMap<>();
        classificationTableInBM.putAll(classificationTable);
        BidiMap<TeamInformation, String> classificationTableInBMInverted = classificationTableInBM.inverseBidiMap();


    }
}