package com.lamardinho.sportnotifier.footballdataorg.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FootballMatchDTO {
    private AreaDTO area;
    private CompetitionDTO competition;
    private SeasonDTO season;
    private int id;
    private String utcDate;
    private String status;
    private int matchday;
    private String stage;
    private String group;
    private String lastUpdated;
    private TeamDTO homeTeam;
    private TeamDTO awayTeam;
    private ScoreDTO score;
    private OddsDTO odds;
    private List<RefereeDTO> referees;
}
