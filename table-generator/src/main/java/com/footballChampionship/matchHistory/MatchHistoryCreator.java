package com.footballChampionship.matchHistory;

import com.footballChampionship.championshipRank.ChampionshipTableGenerator;
import com.footballChampionship.championshipRank.TeamInformation;
import com.footballChampionship.fileWriter.ChampionshipFileWriter;
import com.footballChampionship.fileWriter.MatchHistoryFileWriter;
import org.apache.commons.lang3.ArrayUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class MatchHistoryCreator {
    public static final String FILE_PATH = "C:\\Users\\guilh\\Documents\\GitHub\\projeto-campeonato\\santander811matchesResult.csv";

    private static Set<MatchDetails> matchHistoryCreator(){
        MatchDetails matchEntry;
        Set<MatchDetails> matchHistory = new TreeSet<>();

        try(Scanner scan = new Scanner(Path.of(FILE_PATH))){
            while(scan.hasNext()){
                String[] matchDetailsStringArray = createMatchDetailsArray(scan.nextLine());
                matchEntry = createMatchEntry(matchDetailsStringArray);
                matchHistory.add(matchEntry);
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        return matchHistory;
    }

    private static String[] createMatchDetailsArray(String matchDetailsString){
        return matchDetailsString.split(";");
    }

    private static MatchDetails createMatchEntry(String[] matchDetailsStringArray){
        return  MatchDetails.builder().homeTeam(matchDetailsStringArray[0]).
                awayTeam(matchDetailsStringArray[1]).
                homeTeamScore(Integer. parseInt(matchDetailsStringArray[2])).
                awayTeamScore(Integer. parseInt(matchDetailsStringArray[3])).
                matchDate(LocalDate.parse(convertDateFormt(matchDetailsStringArray[4])))
                .build();
    }

    private static String convertDateFormt(String date){
        String[] dateArray = date.split("/");
        ArrayUtils.reverse(dateArray);
        return dateArray[0] + "-" + dateArray[1] + "-" + dateArray[2];
    }


    public static void main(String[] args) {
        Set<MatchDetails> matchHistory = matchHistoryCreator();
        matchHistory.forEach(System.out::println);

        Map<String, List<MatchDetails>> matchHistoryByTeam = MatchHistoryByTeam.divideMatchHistoryByTeams(matchHistory);
        MatchHistoryByTeam.printFilteredList(matchHistoryByTeam);

        Set<TeamInformation> classificationTable = ChampionshipTableGenerator.generateClassificationTable(matchHistoryByTeam);

        ChampionshipTableGenerator.printClassificationTable(classificationTable);

        ChampionshipFileWriter.prepareAndWriteChampionshipClassificationTable(classificationTable);

        MatchHistoryFileWriter.prepareAndWriteTeamMatchHistory(matchHistoryByTeam);

}

}
