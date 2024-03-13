package ts.andrey.tm.service;

import ts.andrey.tm.entity.UserInfoEntity;

public interface UserService {

    UserInfoEntity getUserByName(String name);

}
