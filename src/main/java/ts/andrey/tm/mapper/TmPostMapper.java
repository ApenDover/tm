package ts.andrey.tm.mapper;


import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ts.andrey.tm.entity.TmPostEntity;
import ts.andrey.tm.dto.TmPostDto;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface TmPostMapper {

    @Mapping(target = "headId", source = "head", qualifiedByName = "postToInt")
    TmPostDto toDto(TmPostEntity tmPostEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "head", source = "headId", qualifiedByName = "intToPost")
    TmPostEntity toEntity(TmPostDto tmPostDto);

    @Named("postToInt")
    default Integer postToInt(TmPostEntity tmPostEntity) {
        return tmPostEntity != null ? tmPostEntity.getId() : null;
    }

    @Named("intToPost")
    default TmPostEntity intToPost(Integer id) {
        if (id == null) {
            return null;
        }
        return TmPostEntity.builder()
                .id(id)
                .build();
    }


}