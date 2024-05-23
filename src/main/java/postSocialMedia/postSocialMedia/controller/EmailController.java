package postSocialMedia.postSocialMedia.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import postSocialMedia.postSocialMedia.dto.EmailDTOInfo;
import postSocialMedia.postSocialMedia.dto.EmailDto;
import postSocialMedia.postSocialMedia.mapper.EmailMapper;
import postSocialMedia.postSocialMedia.model.EmailModel;
import postSocialMedia.postSocialMedia.service.impl.EmailService;

@RestController
public class EmailController {
    @Autowired
    private EmailService emailService;
    @Autowired
    private EmailMapper emailMapper;

    @PostMapping("/sending-email")
    public ResponseEntity<EmailDTOInfo> sendingEmail(@RequestBody @Valid EmailModel emailModel) {
        EmailDto emailDto = emailService.sendEmail(emailModel);
        EmailDTOInfo emailDTOInfo = emailMapper.mapEmailToDto(emailDto);
        return new ResponseEntity<>(emailDTOInfo, HttpStatus.CREATED);
    }
}



