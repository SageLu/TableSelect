package zz.hanhan.testtablegreendao;

import android.app.Application;
import android.content.Context;

import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.query.QueryBuilder;

import zz.hanhan.testtablegreendao.entity.DaoMaster;
import zz.hanhan.testtablegreendao.entity.DaoSession;
import zz.hanhan.testtablegreendao.entity.TableInfoDao;
import zz.hanhan.testtablegreendao.util.Constants;

/**
 * Created by lenovo on 2017/1/15.
 */

public class App extends Application {
    private static Context mContext;
    private static DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        setGreenDaoDatabase();

    }

    private void setGreenDaoDatabase() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, Constants.DB_NAME);
        Database db = helper.getWritableDb();
        DaoMaster daoMaster = new DaoMaster(db);
        mDaoSession = daoMaster.newSession();
        // 在 QueryBuilder 类中内置两个 Flag 用于方便输出执行的 SQL 语句与传递参数的值
        QueryBuilder.LOG_SQL = BuildConfig.DEBUG;
        QueryBuilder.LOG_VALUES = BuildConfig.DEBUG;

    }

    public static TableInfoDao getTableInfoDao() {
        return mDaoSession.getTableInfoDao();
    }

    public static Context getAppContext() {

        return mContext;
    }
}
