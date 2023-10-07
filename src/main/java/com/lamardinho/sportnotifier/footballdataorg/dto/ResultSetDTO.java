package com.lamardinho.sportnotifier.footballdataorg.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultSetDTO {
    private int count;
    private String first;
    private String last;
    private int played;
}
