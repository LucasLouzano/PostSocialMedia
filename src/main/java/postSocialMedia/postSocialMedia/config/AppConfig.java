package postSocialMedia.postSocialMedia.config;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import postSocialMedia.postSocialMedia.mapper.EmailMapper;
import postSocialMedia.postSocialMedia.mapper.PostsMapper;

@Configuration
public class AppConfig {

    @Bean
    public PostsMapper postsServiceMapper() {
        return Mappers.getMapper(PostsMapper.class);
    }
    @Bean
    public EmailMapper emailServiceMapper() {
        return Mappers.getMapper(EmailMapper.class);
    }
}
