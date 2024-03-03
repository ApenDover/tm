package ts.andrey.tm.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ts.andrey.tm.dto.TmPostDto;
import ts.andrey.tm.service.CrudPostService;
import ts.andrey.tm.tmconst.ApiConst;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(ApiConst.API)
public class TmController {

    private final CrudPostService crudPostService;

    @PostMapping(ApiConst.ALL_CREATE_POST)
    public String addPost(@RequestBody TmPostDto postDto, Authentication authentication) {
        log.info("request to {}: {}", ApiConst.ALL_CREATE_POST, postDto);
        crudPostService.publishPost(postDto, authentication);
        return "added";
    }

    @GetMapping(ApiConst.ALL_GET_POST)
    public List<TmPostDto> readPost(Authentication authentication) {
        log.info("request to {}", ApiConst.ALL_GET_POST);
        return crudPostService.readPost(authentication);
    }

}
