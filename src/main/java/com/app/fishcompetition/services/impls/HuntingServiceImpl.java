package com.app.fishcompetition.services.impls;

import com.app.fishcompetition.common.exceptions.custom.HuntingAllReadyExistException;
import com.app.fishcompetition.model.entity.Hunting;
import com.app.fishcompetition.repositories.HuntingRepository;
import com.app.fishcompetition.services.HuntingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class HuntingServiceImpl implements HuntingService {

    private final HuntingRepository huntingRepository;
    private final MemberServiceImpl memberService;
    private final FishServiceImpl fishService;
    @Override
    public List<Hunting> getAllHunting() {
        return null;
    }

    @Override
    public Optional<Hunting> getHuntingById(UUID huntingId) {
        return  huntingRepository.findById(huntingId);
    }

    @Override
    public Hunting addHunting(Hunting hunting) {
        if(!checkIfMemberExist(hunting.getMember().getId())) {
            throw new NoSuchElementException("Member does that you entered not exist");
        }else if(!checkIfFishExist(hunting.getFish().getId())){
            throw new NoSuchElementException("Fish that you entered not exist");
        }else{
            return huntingRepository.save(hunting);
        }

    }

    @Override
    public Hunting updateHunting(UUID huntingId, Hunting hunting) {
        return null;
    }

    @Override
    public void deleteHunting(UUID huntingId) {

    }
    public boolean checkIfHuntingForTheSameMemberAndSameFish(UUID memberId, UUID fishId){
            return getHuntingByMemberIdAndFishId(memberId,fishId).isPresent();
    }
    public Optional<Hunting> getHuntingByMemberIdAndFishId(UUID memberId, UUID fishId){
        return huntingRepository.findByMemberIdAndFishId(memberId,fishId);
    }
    public boolean checkIfMemberExist(UUID memberId){
        return memberService.getMemberById(memberId).isPresent();
    }
    public boolean checkIfFishExist(UUID fishId){
        return fishService.getFishById(fishId).isPresent();
    }
}
