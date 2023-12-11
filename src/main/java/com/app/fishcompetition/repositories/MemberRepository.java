package com.app.fishcompetition.repositories;

import com.app.fishcompetition.model.entity.Competition;
import com.app.fishcompetition.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface MemberRepository extends JpaRepository<Member, UUID> {
}
