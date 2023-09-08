package com.emre.mapper;

import com.emre.dto.request.RegisterRequestDto;
import com.emre.dto.response.RegisterResponseDto;
import com.emre.repository.entity.Auth;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

//entity'leri dto'ya dönüştürüyor.
//unmappedTargetPolicy = ReportingPolicy.IGNORE amacı eşleşmeyenleri görmezlikten gel diyoruz.
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface IAuthMapper {
    //instance(örneğini)'ını kendi üretmesi için yazıyoruz
    IAuthMapper INSTANCE = Mappers.getMapper(IAuthMapper.class);

    /**dto dan auth'a çevirme işlemi yaptık.*/
    Auth fromRegisterRequestDto(final RegisterRequestDto dto);
    @Mapping(source = "id", target = "authid")   //id ile authid'nin adları uymadığı için elle eşleştirdim
    RegisterResponseDto fromAuth(final Auth auth);
}
