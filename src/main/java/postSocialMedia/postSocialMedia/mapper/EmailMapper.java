package postSocialMedia.postSocialMedia.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import postSocialMedia.postSocialMedia.dto.EmailDTOInfo;
import postSocialMedia.postSocialMedia.dto.EmailDto;
import postSocialMedia.postSocialMedia.model.EmailModel;

@Mapper
public interface EmailMapper {

    EmailMapper INSTANCE = Mappers.getMapper(EmailMapper.class);
    EmailDto emailModelToEmailDto(EmailModel emailModel);
    EmailDTOInfo mapEmailToDto(EmailDto emailDto);
}
