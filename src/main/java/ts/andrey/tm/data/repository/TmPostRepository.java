package ts.andrey.tm.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ts.andrey.tm.data.entity.TmPost;
import ts.andrey.tm.data.entity.UserInfo;

import java.util.List;

@Repository
public interface TmPostRepository extends JpaRepository<TmPost, Integer> {

    @Query(value = "SELECT last_value FROM post_seq", nativeQuery = true)
    int getLastValFromSequence();

    List<TmPost> findAllByOwner(UserInfo userInfo);

}
