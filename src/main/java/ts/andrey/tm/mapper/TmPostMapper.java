package ts.andrey.tm.mapper;


import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ts.andrey.tm.data.entity.TmPost;
import ts.andrey.tm.dto.TmPostDto;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface TmPostMapper {

    @Mapping(target = "headId", source = "head", qualifiedByName = "postToInt")
    TmPostDto toDto(TmPost tmPost);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "head", source = "headId", qualifiedByName = "intToPost")
    TmPost toEntity(TmPostDto tmPostDto);

    @Named("postToInt")
    default Integer postToInt(TmPost tmPost) {
        return tmPost != null ? tmPost.getId() : null;
    }

    @Named("intToPost")
    default TmPost intToPost(Integer id) {
        if (id == null) {
            return null;
        }
        return TmPost.builder()
                .id(id)
                .build();
    }


}