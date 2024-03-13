package ts.andrey.tm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ts.andrey.tm.dto.TmPostDto;
import ts.andrey.tm.service.impl.TmPostService;
import ts.andrey.tm.service.impl.UserInfoService;
import ts.andrey.tm.tmconst.ApiConst;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiConst.API)
public class TmController {

    private final TmPostService tmPostService;

    private final UserInfoService userInfoService;

    @PostMapping(ApiConst.ALL_TM_POSTS)
    public ResponseEntity<TmPostDto> addPost(@RequestBody TmPostDto postDto, Authentication authentication) {
        final var response = tmPostService.saveHeadPost(postDto, userInfoService.unpackUserInfo(authentication));
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @GetMapping(ApiConst.ALL_TM_POSTS)
    public ResponseEntity<List<TmPostDto>> readPost(Authentication authentication) {
        return ResponseEntity.ok(tmPostService.readAllTmPostForUser(userInfoService.unpackUserInfo(authentication)));
    }

}
