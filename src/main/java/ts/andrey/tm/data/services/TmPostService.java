package ts.andrey.tm.data.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import ts.andrey.tm.data.entity.TmPost;
import ts.andrey.tm.data.entity.UserInfo;
import ts.andrey.tm.data.repository.TmPostRepository;
import ts.andrey.tm.dto.TmPostDto;
import ts.andrey.tm.mapper.TmPostMapper;

import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class TmPostService {

    private final TmPostRepository tmPostRepository;

    private final TmPostMapper tmPostMapper;

    public void saveHeadPost(TmPostDto postDto, UserInfo owner) {
        final var id = tmPostRepository.getLastValFromSequence() + 1;
        if (!CollectionUtils.isEmpty(postDto.getChildPosts())) {
            postDto.getChildPosts().forEach(p -> p.setHeadId(id));
        }
        final var post = tmPostMapper.toEntity(postDto);
        final var child = post.getChildPosts();
        post.setChildPosts(null);
        if (Objects.isNull(post.getHead())) {
            post.setOwner(owner);
        }
        tmPostRepository.save(post);
        child.forEach(this::saveChildPost);
    }

    private void saveChildPost(TmPost childPost) {
        if (!CollectionUtils.isEmpty(childPost.getChildPosts())) {
            final var childList = childPost.getChildPosts();
            childPost.setChildPosts(null);
            final var head = tmPostRepository.save(childPost);
            childList.forEach(tmPost -> {
                tmPost.setHead(TmPost.builder()
                        .id(head.getId())
                        .build());
                saveChildPost(tmPost);
            });
        }
        tmPostRepository.save(childPost);
    }

    public List<TmPostDto> readAllTmPostForUser(UserInfo userInfo) {
        final var listTmPost = tmPostRepository.findAllByOwner(userInfo);
        return listTmPost.stream().map(tmPostMapper::toDto).toList();
    }
}
