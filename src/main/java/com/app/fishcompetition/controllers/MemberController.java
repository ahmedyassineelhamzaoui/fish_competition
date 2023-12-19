package com.app.fishcompetition.controllers;

import com.app.fishcompetition.common.responses.RequestResponseWithDetails;
import com.app.fishcompetition.common.responses.RequestResponseWithoutDetails;
import com.app.fishcompetition.mapper.MemberDtoConverter;
import com.app.fishcompetition.model.dto.LevelDto;
import com.app.fishcompetition.model.dto.MemberDto;
import com.app.fishcompetition.model.entity.Level;
import com.app.fishcompetition.model.entity.Member;
import com.app.fishcompetition.services.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MemberController {

    private final RequestResponseWithDetails requestResponseWithDetails;
    private final RequestResponseWithoutDetails requestResponseWithoutDetails;;
   private final MemberService memberService;;
   private final MemberDtoConverter memberDtoConverter;;

    @PostMapping("/member")
    public ResponseEntity<RequestResponseWithDetails> addMember(@Valid  @RequestBody MemberDto memberDto) {
        Map<String,Object> response = new HashMap<>();;
        Member memberToAdd = memberService.addMember(memberDtoConverter.convertDtoTOMember(memberDto));
        requestResponseWithDetails.setMessage("Member added successfully");
        requestResponseWithDetails.setTimestamp(LocalDateTime.now());
        requestResponseWithDetails.setStatus("200");
        response.put("member",memberToAdd);
        requestResponseWithDetails.setDetails(response);
        return ResponseEntity.ok().body(requestResponseWithDetails);
    }

    @GetMapping("/members")
    public ResponseEntity<RequestResponseWithDetails> getAllMembers() {
        Map<String,Object> response = new HashMap<>();;
        List<Member> members = memberService.getAllMembers();
        List<MemberDto> membersData = members.stream()
                .map(memberDtoConverter::convertMemberTODto)
                .collect(Collectors.toList());
        response.put("members",membersData);

        requestResponseWithDetails.setMessage("Members retrieved successfully");
        requestResponseWithDetails.setTimestamp(LocalDateTime.now());
        requestResponseWithDetails.setStatus("200");
        requestResponseWithDetails.setDetails(response);
        return ResponseEntity.ok().body(requestResponseWithDetails);
    }
    @GetMapping("/members/{pageNumber}/{pageSize}")
    public ResponseEntity<RequestResponseWithDetails> getMemberById(@PathVariable("pageNumber") int pageNumber, @PathVariable("pageSize") int pageSize) {
        Map<String,Object> response = new HashMap<>();
        Page<Member> members = memberService.getAllMembersWithPagination(pageNumber,pageSize);
        List<MemberDto> membersData  = members.stream().
                map(memberDtoConverter::convertMemberTODto).collect(Collectors.toList());
        response.put("members",membersData);
        requestResponseWithDetails.setMessage("Member retrieved successfully");
        requestResponseWithDetails.setTimestamp(LocalDateTime.now());
        requestResponseWithDetails.setStatus("200");
        requestResponseWithDetails.setDetails(response);
        return ResponseEntity.ok().body(requestResponseWithDetails);
    }
}
