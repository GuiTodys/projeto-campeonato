package com.footballChampionship.matchHistory;

import com.footballChampionship.championshipRank.ChampionshipTableGenerator;
import com.footballChampionship.championshipRank.TeamInformation;
import com.footballChampionship.fileWriter.ChampionshipFileWriter;
import com.footballChampionship.fileWriter.MatchHistoryFileWriter;
import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.*;

public class MatchHistoryCreator {
    public static final String FILE_PATH = "C:\\Users\\guilh\\Documents\\GitHub\\projeto-campeonato\\santander811matchesResult.csv";

    public static Set<MatchDetails> createMatchHistory() {
        MatchDetails matchEntry;
        Set<MatchDetails> matchHistory = new TreeSet<>();

        try (Scanner scan = new Scanner(Path.of(FILE_PATH))) {
            String tableHeader = scan.nextLine();
            while (scan.hasNext()) {
                String[] matchDetailsStringArray = createMatchDetailsArray(scan.nextLine());
                matchEntry = createMatchEntry(matchDetailsStringArray);
                matchHistory.add(matchEntry);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return matchHistory;
    }

    private static String[] createMatchDetailsArray(String matchDetailsString) {
        return matchDetailsString.split(";");
    }

    private static MatchDetails createMatchEntry(String[] matchDetailsStringArray) {
        return MatchDetails.builder().homeTeam(matchDetailsStringArray[0]).
                awayTeam(matchDetailsStringArray[1]).
                homeTeamScore(Integer.parseInt(matchDetailsStringArray[2])).
                awayTeamScore(Integer.parseInt(matchDetailsStringArray[3])).
                matchDate(LocalDate.parse(convertDateFormt(matchDetailsStringArray[4])))
                .build();
    }

    private static String convertDateFormt(String date) {
        String[] dateArray = date.split("/");
        ArrayUtils.reverse(dateArray);
        return dateArray[0] + "-" + dateArray[1] + "-" + dateArray[2];
    }
}
