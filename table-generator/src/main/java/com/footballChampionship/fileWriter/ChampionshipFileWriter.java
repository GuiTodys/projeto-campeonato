package com.footballChampionship.fileWriter;

import com.footballChampionship.championshipRank.TeamInformation;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class ChampionshipFileWriter {

    private static final String HEADER = "Team;Wins;Draws;Defeats;Score";

    public static void prepareAndWriteChampionshipClassificationTable(Set<TeamInformation> classificationTable){
        List<String> formattedClassificationTable = setUpChampionshipFormattedList();

        classificationTable.forEach(teamInformation -> {
            String listEntry = generateListEntry(teamInformation);
            formattedClassificationTable.add(listEntry);
        });

        writeChampionshipTableFile(formattedClassificationTable);
    }

    public static void writeChampionshipTableFile(List<String> championshipTable){
        try{
            FileUtils.writeLines(new File("resultadoCampeonato.csv"), championshipTable);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private static List<String> setUpChampionshipFormattedList(){
        List<String> formattedList = new LinkedList<>();
        formattedList.add(HEADER);
        return formattedList;
    }

    private static String generateListEntry(TeamInformation teamInformation){
        return  teamInformation.getTeamName() + ";"
                + teamInformation.getWins() + ";"
                + teamInformation.getDraws() + ";"
                + teamInformation.getDefeats() + ";"
                + teamInformation.getScore();
    }
}
