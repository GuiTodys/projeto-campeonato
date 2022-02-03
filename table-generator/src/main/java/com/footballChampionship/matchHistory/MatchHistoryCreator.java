package com.footballChampionship.matchHistory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.*;

public class MatchHistoryCreator {
    public static final String FILE_PATH = "C:\\Users\\guilh\\Documents\\projeto-campeonato\\lista-times.csv";

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
                matchDate(LocalDate.parse(matchDetailsStringArray[4]))
                .build();
    }


    public static void main(String[] args) {
        Set<MatchDetails> matchHistory = matchHistoryCreator();
        matchHistory.forEach(System.out::println);

        Map<String, List<MatchDetails>> matchHistoryByTeam = MatchHistoryByTeam.divideMatchHistoryByTeams(matchHistory);
        MatchHistoryByTeam.printFilteredList(matchHistoryByTeam);
}

}
