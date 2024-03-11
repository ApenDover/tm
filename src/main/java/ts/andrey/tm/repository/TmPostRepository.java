package ts.andrey.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ts.andrey.tm.entity.TmPostEntity;
import ts.andrey.tm.entity.UserInfoEntity;

import java.util.List;

public interface TmPostRepository extends JpaRepository<TmPostEntity, Integer> {

    List<TmPostEntity> findAllByOwnerId(int id);

}
