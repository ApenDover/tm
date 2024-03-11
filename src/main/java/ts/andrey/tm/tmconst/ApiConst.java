package ts.andrey.tm.tmconst;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ApiConst {

    public static final String API = "/api";

    public static final String ADMIN_TEST = "/testUser";

    public static final String USER_TEST = "/testAdmin";

    public static final String ALL_TM_POSTS = "/tmposts";

    public static final String ADMIN_TEST_API = API + USER_TEST;

    public static final String USER_TEST_API = API + ADMIN_TEST;

    public static final String ALL_TM_POSTS_API = API + ALL_TM_POSTS;

}
