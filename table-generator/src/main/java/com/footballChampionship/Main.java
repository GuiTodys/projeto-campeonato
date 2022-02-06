package com.footballChampionship;

import com.footballChampionship.championshipRank.ChampionshipTableGenerator;
import com.footballChampionship.championshipRank.TeamInformation;
import com.footballChampionship.fileWriter.ChampionshipFileWriter;
import com.footballChampionship.fileWriter.MatchHistoryFileWriter;
import com.footballChampionship.matchHistory.MatchDetails;
import com.footballChampionship.matchHistory.MatchHistoryByTeam;
import com.footballChampionship.matchHistory.MatchHistoryCreator;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set<MatchDetails> matchHistory = MatchHistoryCreator.createMatchHistory();
        Map<String, List<MatchDetails>> matchHistoryByTeam = MatchHistoryByTeam.divideMatchHistoryByTeams(matchHistory);
        Set<TeamInformation> classificationTable = ChampionshipTableGenerator.generateClassificationTable(matchHistoryByTeam);
        ChampionshipTableGenerator.printClassificationTable(classificationTable);

        MatchHistoryFileWriter.prepareAndWriteTeamMatchHistory(matchHistoryByTeam);
        ChampionshipFileWriter.prepareAndWriteChampionshipClassificationTable(classificationTable);
    }
}
