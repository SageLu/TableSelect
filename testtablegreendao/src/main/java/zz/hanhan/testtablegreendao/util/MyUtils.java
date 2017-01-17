package zz.hanhan.testtablegreendao.util;

import android.content.Context;
import android.content.SharedPreferences;

import zz.hanhan.testtablegreendao.App;

/**
 * Created by lenovo on 2017/1/15.
 */

public class MyUtils {
    public static SharedPreferences getSharedPreferences() {
        return App.getAppContext()
                .getSharedPreferences(Constants.SHARES_TESTGREENDAO, Context.MODE_PRIVATE);
    }


}
