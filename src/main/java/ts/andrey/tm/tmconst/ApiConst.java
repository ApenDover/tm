package ts.andrey.tm.tmconst;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ApiConst {

    public static final String API = "/api";

    public static final String ADMIN_TEST = "/testUser";

    public static final String USER_TEST = "/testADMIN";

    public static final String ALL_CREATE_POST = "/create/tmpost";

    public static final String ALL_GET_POST = "/read/tmpost";

    public static final String ADMIN_TEST_API = API + USER_TEST;

    public static final String USER_TEST_API = API + ADMIN_TEST;

    public static final String ALL_CREATE_POST_API = API + ALL_CREATE_POST;

    public static final String ALL_GET_POST_API = API + ALL_GET_POST;


}
