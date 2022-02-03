package com.footballChampionship;

import com.footballChampionship.matchHistory.MatchDetails;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.*;

public class FileReader {
    public static final String FILE_PATH = "C:\\Users\\guilh\\Documents\\projeto-campeonato\\lista-times.csv";
//    public static final File PATH = new File("C:\\\\Users\\\\guilh\\\\Documents\\\\projeto-campeonato\\\\lista-times.csv");

    public static void readFileByScanner(){
        Set<MatchDetails> matchHistorySet = new TreeSet<>();
        MatchDetails match;

        try(Scanner scan = new Scanner(Path.of(FILE_PATH))){
            while(scan.hasNext()){
                String matchDetails = scan.nextLine();
                String[] matchDetailsArray = matchDetails.split(";");
                match = MatchDetails.builder().homeTeam(matchDetailsArray[0]).
                        awayTeam(matchDetailsArray[1]).
                        homeTeamScore(Integer. parseInt(matchDetailsArray[2])).
                        awayTeamScore(Integer. parseInt(matchDetailsArray[3])).
                        matchDate(LocalDate.parse(matchDetailsArray[4]))
                        .build();
                matchHistorySet.add(match);
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

        matchHistorySet.forEach(System.out::println);

        Set<String> teamList = new TreeSet<>();

        teamList = TeamList.createTeamList(matchHistorySet);

        System.out.println(teamList);

        teamList.forEach(System.out::println);
    }


    public static void main(String[] args) {
        readFileByScanner();
    }
}
