package zz.hanhan.testtablegreendao.util;

import java.util.Arrays;
import java.util.List;

import zz.hanhan.testtablegreendao.App;
import zz.hanhan.testtablegreendao.R;
import zz.hanhan.testtablegreendao.entity.TableInfo;
import zz.hanhan.testtablegreendao.entity.TableInfoDao;

/**
 * Created by lenovo on 2017/1/16.
 */

public class TableInfoManger {
    public static void initDB() {
        if (!MyUtils.getSharedPreferences().getBoolean(Constants.INIT_DB, false)) {
            TableInfoDao dao = App.getTableInfoDao();
            List<String> tableName = Arrays.asList(App.getAppContext().getResources().getStringArray(R.array.news_channel_name));
            List<String> tableId = Arrays.asList(App.getAppContext().getResources().getStringArray(R.array.news_channel_id));
            for (int i = 0; i < tableName.size(); i++) {
                TableInfo tableInfo = new TableInfo(i + 1, tableName.get(i), tableId.get(i), Constants.getType(tableId.get(i)), i <= 3, i, i == 0);
                dao.insert(tableInfo);
            }
            MyUtils.getSharedPreferences().edit().putBoolean(Constants.INIT_DB, true).apply();
        }
    }

    public static List<TableInfo> loadMine() {
        List<TableInfo> tableInfoList = App.getTableInfoDao().queryBuilder().where(TableInfoDao.Properties.NewsChannelSelect.eq(true))
                .orderAsc(TableInfoDao.Properties.NewsChannelIndex).build().list();
        return tableInfoList;
    }

    public static List<TableInfo> loadMore() {
        List<TableInfo> tableInfoList = App.getTableInfoDao().queryBuilder().where(TableInfoDao.Properties.NewsChannelSelect.eq(false))
                .orderAsc(TableInfoDao.Properties.NewsChannelIndex).build().list();
        return tableInfoList;
    }

    /**
     * 查询的是more里边被选择的item之前的item的list集合（没有被选中）
     *
     * @param channelIndex
     * @return
     */
    public static List<TableInfo> loadTableIndexLtAndIsUnselect(int channelIndex) {
        List<TableInfo> ltTable = App.getTableInfoDao().queryBuilder()
                .where(TableInfoDao.Properties.NewsChannelIndex.lt(channelIndex), TableInfoDao.Properties.NewsChannelSelect.eq(false)).build().list();

        return ltTable;
    }

    /**
     * 大于item的索引list
     *
     * @param channelIndex
     * @return
     */
    public static List<TableInfo> loadTableIndexGt(int channelIndex) {
        List<TableInfo> gtTable = App.getTableInfoDao().queryBuilder()
                .where(TableInfoDao.Properties.NewsChannelIndex.gt(channelIndex)).build().list();
        return gtTable;

    }

    /**
     * 数据库中所有的数据返回
     *
     * @return
     */
    public static int getAllSize() {
        return App.getTableInfoDao().loadAll().size();
    }

    /**
     * 更新某一个实体
     *
     * @param tableInfo
     */
    public static void upData(TableInfo tableInfo) {
        App.getTableInfoDao().update(tableInfo);

    }

    public static int getTableInfoSelectSize() {
        int count = (int) App.getTableInfoDao().queryBuilder().where(TableInfoDao.Properties.NewsChannelSelect.eq(true)).buildCount().count();

        return count;
    }
}
