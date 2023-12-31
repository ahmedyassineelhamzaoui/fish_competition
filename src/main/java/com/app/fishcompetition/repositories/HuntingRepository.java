package com.app.fishcompetition.repositories;

import com.app.fishcompetition.model.entity.Competition;
import com.app.fishcompetition.model.entity.Hunting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface HuntingRepository extends JpaRepository<Hunting, UUID> {

    @Query("SELECT h FROM Hunting h WHERE h.member.id = ?1 AND h.fish.id = ?2 AND h.competition.id = ?3")
    Optional<Hunting> findByMemberIdAndFishId(UUID memberId, UUID fishId, UUID competitionId);

    @Query("SELECT h FROM Hunting h WHERE h.member.id = ?1 AND h.competition.id = ?2")
    List<Hunting> getAllHuntingOfSameCompetitionAndSameMember( UUID memberId, UUID competitionId);
}
