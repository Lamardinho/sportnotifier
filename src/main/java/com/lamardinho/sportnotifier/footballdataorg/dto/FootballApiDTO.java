package com.lamardinho.sportnotifier.footballdataorg.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FootballApiDTO {
    private Filters filters;
    private ResultSetDTO resultSet;
    private CompetitionDTO competition;
    private List<MatchDTO> matches;
}
