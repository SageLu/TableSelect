package zz.hanhan.testtablegreendao.util;

/**
 * Created by lenovo on 2017/1/15.
 */

public class Constants {
    public static final String DB_NAME = "SageTable";
    public static final String SHARES_TESTGREENDAO ="test_greendao" ;
    public static final String INIT_DB = "init_db";
    // 头条id
    public static final String HEADLINE_ID = "T1348647909107";
    // 头条TYPE
    public static final String HEADLINE_TYPE = "headline";
    // 房产id
    public static final String HOUSE_ID = "5YyX5Lqs";
    // 房产TYPE
    public static final String HOUSE_TYPE = "house";
    // 其他TYPE
    public static final String OTHER_TYPE = "list";
    /**
     * 新闻id获取类型
     *
     * @param id 新闻id
     * @return 新闻类型
     */
    public static String getType(String id) {
        switch (id) {
            case HEADLINE_ID:
                return HEADLINE_TYPE;
            case HOUSE_ID:
                return HOUSE_TYPE;
            default:
                break;
        }
        return OTHER_TYPE;
    }
}
