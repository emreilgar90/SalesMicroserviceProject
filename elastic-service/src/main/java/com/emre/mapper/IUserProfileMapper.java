package com.emre.mapper;

import com.emre.dto.request.UserProfileSaveRequestDto;
import com.emre.repository.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel="spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserProfileMapper {

    IUserProfileMapper INSTANCE = Mappers.getMapper(IUserProfileMapper.class);


    /**UserProfileSaveRequestDto dan UserProfile'a convert işlemi yaptım !**/
    UserProfile fromUserProfileDto(final UserProfileSaveRequestDto dto);
}
