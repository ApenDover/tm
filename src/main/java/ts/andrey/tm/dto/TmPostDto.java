package ts.andrey.tm.dto;

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

    private int id;

    private Integer headId;

    private String title;

    private String message;

    private List<TmPostDto> childPosts;

}