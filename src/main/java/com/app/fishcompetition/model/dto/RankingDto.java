package com.app.fishcompetition.model.dto;


import com.app.fishcompetition.model.entity.Competition;
import com.app.fishcompetition.model.entity.Member;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RankingDto {


    private Integer rank;
    private Integer score;

    @NotNull(message = "Member is mandatory")
    private Member member;

    @NotNull(message = "Competition is mandatory")
    private Competition competition;
}
