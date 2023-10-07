package com.lamardinho.sportnotifier.footballdataorg.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScoreDTO {
    private String winner;
    private String duration;
    private TimeDTO fullTime;
    private TimeDTO halfTime;
}
