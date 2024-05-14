package postSocialMedia.postSocialMedia.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import postSocialMedia.postSocialMedia.dto.*;
import postSocialMedia.postSocialMedia.model.Posts;

import java.util.List;

@Mapper
public interface PostsMapper {

    PostsMapper CONVERTER = Mappers.getMapper(PostsMapper.class);
    PostsDTO postsToPostsDTO(Posts posts);
    List<ListPostsDTO> postsDtoToListPostsDto(List<PostsDTO> postsDTO);
    ListPostsDTO postsDtoToListPostsDto(PostsDTO postsDTO);
    PostsByIdResponse mapToDto(PostsDTO postsDTO);
    PostRequestDTO postsDtoPostsDto(PostsDTO postsDTO);
    PostsDTOInfo mapPostToDto(PostsDTO postsDTO);


}
