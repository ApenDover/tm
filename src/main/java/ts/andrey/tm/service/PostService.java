package ts.andrey.tm.service;

import ts.andrey.tm.dto.TmPostDto;
import ts.andrey.tm.entity.UserInfoEntity;

import java.util.List;

public interface PostService {

    TmPostDto saveHeadPost(TmPostDto tmPostDto, UserInfoEntity owner);

    List<TmPostDto> readAllTmPostForUser(UserInfoEntity userInfo);

}
