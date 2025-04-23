package com.yawn.study.memeber.mapper;

import com.yawn.study.memeber.dto.MemberPostDto;
import com.yawn.study.memeber.entity.Member;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    Member toMember(MemberPostDto memberPostDto);

}
