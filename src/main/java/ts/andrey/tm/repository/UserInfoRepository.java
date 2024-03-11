package ts.andrey.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ts.andrey.tm.entity.UserInfoEntity;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfoEntity, Integer> {

    Optional<UserInfoEntity> findUserInfoByName(String name);

}
