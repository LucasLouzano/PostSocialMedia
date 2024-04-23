package postSocialMedia.postSocialMedia.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import postSocialMedia.postSocialMedia.dto.PostRequestDTO;
import postSocialMedia.postSocialMedia.dto.PostsDTO;
import postSocialMedia.postSocialMedia.model.Posts;
@Mapper
public interface PostsMapper {

    PostsMapper CONVERTER = Mappers.getMapper(PostsMapper.class);
    PostsDTO postsToPostsDTO(Posts posts);
    PostRequestDTO postsDtoPostsDto(PostsDTO postsDTO);
}
