package com.footballChampionship.fileWriter;

import com.footballChampionship.matchHistory.MatchDetails;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MatchHistoryFileWriter {
    public static void prepareAndWriteTeamMatchHistory(Map<String, List<MatchDetails>> matchHistoryByTeams){
            matchHistoryByTeams.forEach((teamName, matchDetails) -> {

                List<String> matchHistoryFromEachTeam = new LinkedList<>();

                matchDetails.forEach(matchHistory -> {
                    String listEntry = generateListEntry(matchHistory);
                    matchHistoryFromEachTeam.add(listEntry);
                });
                writeTeamMatchHistory(teamName, matchHistoryFromEachTeam);
            });
    }

    public static void writeTeamMatchHistory(String teamName,List<String> teamMatchHistory){
        try{
            FileUtils.writeLines(new File(teamName + ".txt"), teamMatchHistory);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private static String formatDate(LocalDate date){
        DateTimeFormatter barFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(barFormatter);
    }

    private static String generateListEntry(MatchDetails matchHistory){
        return formatDate(matchHistory.getMatchDate()) + ":"
                + matchHistory.getHomeTeam() + " "
                + matchHistory.getHomeTeamScore() + " X "
                + matchHistory.getAwayTeamScore() + " "
                + matchHistory.getAwayTeam();
    }
}
