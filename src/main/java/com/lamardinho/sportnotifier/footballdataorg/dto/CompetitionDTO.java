package com.lamardinho.sportnotifier.footballdataorg.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompetitionDTO {
    private int id;
    private String name;
    private String code;
    private String type;
    private String emblem;
}
