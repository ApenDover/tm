package ts.andrey.tm.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class TmPostDto {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Integer headId;

    private String title;

    private String message;

    private List<TmPostDto> childPosts;

}