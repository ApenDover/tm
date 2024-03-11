package ts.andrey.tm.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import ts.andrey.tm.dto.TmPostDto;
import ts.andrey.tm.entity.TmPostEntity;
import ts.andrey.tm.entity.UserInfoEntity;
import ts.andrey.tm.mapper.TmPostMapper;
import ts.andrey.tm.repository.TmPostRepository;
import ts.andrey.tm.service.PostService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class TmPostService implements PostService {

    private final TmPostRepository tmPostRepository;

    private final TmPostMapper tmPostMapper;

    /**
     * Сохраняет в базу TmPost со всеми вложенными TmPost рекурсивно
     *
     * @param tmPostDto
     * @param owner
     * @return
     */
    public TmPostDto saveHeadPost(TmPostDto tmPostDto, UserInfoEntity owner) {

        final var postDtoEntity = tmPostMapper.toEntity(tmPostDto);
        final var childList = postDtoEntity.getChildPosts();
        postDtoEntity.setChildPosts(new ArrayList<>());

        if (Objects.isNull(tmPostDto.getHeadId())) {
            postDtoEntity.setOwner(owner);
        }

        final var savedEntity = tmPostRepository.save(postDtoEntity);

        if (!CollectionUtils.isEmpty(childList)) {
            savedEntity.setChildPosts(childList.stream().map(childPost -> {
                childPost.setHead(savedEntity);
                return childProcess(childPost);
            }).toList());
        }

        return tmPostMapper.toDto(savedEntity);
    }

    /**
     * Отдает все имеющиеся у пользователя TmPost
     *
     * @param userInfo
     * @return
     */
    public List<TmPostDto> readAllTmPostForUser(UserInfoEntity userInfo) {
        final var listTmPost = tmPostRepository.findAllByOwnerId(userInfo.getId());
        return listTmPost.stream().map(tmPostMapper::toDto).toList();
    }

    private TmPostEntity childProcess(TmPostEntity tmPostEntity) {
        final var childList = tmPostEntity.getChildPosts();
        tmPostEntity.setChildPosts(new ArrayList<>());
        final var head = tmPostRepository.save(tmPostEntity);
        if (!CollectionUtils.isEmpty(childList)) {
            head.setChildPosts(childList.stream().map(childPost -> {
                childPost.setHead(head);
                return childProcess(childPost);
            }).toList());
        }
        return head;
    }

}
