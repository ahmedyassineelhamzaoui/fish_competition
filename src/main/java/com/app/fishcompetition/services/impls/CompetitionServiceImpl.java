package com.app.fishcompetition.services.impls;

import com.app.fishcompetition.common.exceptions.custom.DateNotAvailableException;
import com.app.fishcompetition.common.responses.RequestResponseWithDetails;
import com.app.fishcompetition.model.entity.Competition;
import com.app.fishcompetition.repositories.CompetitionRepository;
import com.app.fishcompetition.services.CompetitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CompetitionServiceImpl implements CompetitionService {

    private final CompetitionRepository competitionRepository;
    private final RequestResponseWithDetails requestResponseWithDetails;;
    @Override
    public List<Competition> getAllCompetitions() {
        return null;
    }

    @Override
    public Optional<Competition> getCompetitionById(UUID competitionId) {
        return competitionRepository.findById(competitionId);
    }
    @Override
    public List<Competition> getCompetitionByDate(Date date) {
        return  competitionRepository.findByDate(date);
    }

    @Override
    public Competition addCompetition(Competition competition)  {
       if(!checkIfDateIsAvailable(competition.getDate())){
              throw new DateNotAvailableException("date is not available :  date should be at least 2 months from now");
       }else if(checkDateExistence(competition.getDate())){
              throw new DateNotAvailableException("date is not available :  there is already a competition on this date");
       }else if(checkRangeBetweenEndAndStartTime(competition.getStartTime(), competition.getEndTime())){

       }

        competition.setNumberOfParticipants(0);
        return competitionRepository.save(competition);
    }

    @Override
    public Competition updateCompetition(UUID competitionId, Competition competition) {
        return null;
    }

    @Override
    public void deleteCompetition(UUID competitionId) {

    }
    public boolean checkIfDateIsAvailable(Date date){
        LocalDate givenDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate currentDate = LocalDate.now();
        long daysBetween = ChronoUnit.DAYS.between(currentDate, givenDate);
        return daysBetween <= 60;
    }
    public boolean  checkDateExistence(Date date){
        return competitionRepository.findByDate(date).size() > 0;
    }
    public boolean checkRangeBetweenEndAndStartTime(Time startTime, Time endTime){
        LocalTime startLocalTime = startTime.toLocalTime();
        LocalTime endLocalTime = endTime.toLocalTime();
        Duration duration = Duration.between(startLocalTime, endLocalTime);
        return duration.toHours() > 1;
    }

}
