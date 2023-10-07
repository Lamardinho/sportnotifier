package com.lamardinho.sportnotifier.footballdataorg.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamDTO {
    private int id;
    private String name;
    private String shortName;
    private String tla;
    private String crest;
}
