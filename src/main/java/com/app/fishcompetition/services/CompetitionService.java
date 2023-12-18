package com.app.fishcompetition.services;

import com.app.fishcompetition.common.exceptions.custom.DateNotAvailableException;
import com.app.fishcompetition.model.entity.Competition;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface CompetitionService {
    List<Competition> getAllCompetitions();

     List<Competition> getCompetitionByStatus(String status);

     Optional<Competition> getCompetitionById(UUID competitionId);

    List<Competition> getCompetitionByDate(Date date);
    Competition addCompetition(Competition competition) ;

    Competition updateCompetition(UUID competitionId, Competition competition);

    void deleteCompetition(UUID competitionId);
}
