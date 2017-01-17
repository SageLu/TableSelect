package zz.hanhan.testtablegreendao.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import zz.hanhan.testtablegreendao.R;
import zz.hanhan.testtablegreendao.adapter.TableRecyclerViewAdapter;
import zz.hanhan.testtablegreendao.entity.TableInfo;
import zz.hanhan.testtablegreendao.util.TableInfoManger;

public class SelectTableActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int RESULTOK = 2;
    private Toolbar toolbar;
    private RecyclerView table_mine_rv;
    private RecyclerView table_more_rv;
    TableRecyclerViewAdapter minAdapter;
    TableRecyclerViewAdapter moreAdapter;
    /**
     * 线程池
     */
    private ExecutorService mSingleThreadPool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_table);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        table_mine_rv = (RecyclerView) findViewById(R.id.table_mine_rv);
        table_more_rv = (RecyclerView) findViewById(R.id.table_more_rv);
        toolbar.setOnClickListener(this);
        initRecyclerViewMinAndMore();
    }

    private void initRecyclerViewMinAndMore() {
        List<TableInfo> minInfos = TableInfoManger.loadMine();
        List<TableInfo> moreInfos = TableInfoManger.loadMore();
        initRecyclerView(table_mine_rv, minInfos, true);
        initRecyclerView(table_more_rv, moreInfos, false);

    }

    private void initRecyclerView(RecyclerView recyclerView, List<TableInfo> infos, boolean isMin) {
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4, LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        if (isMin) {
            minAdapter = new TableRecyclerViewAdapter(this, infos);
            recyclerView.setAdapter(minAdapter);
            setMinOnItemClick();
        } else {
            moreAdapter = new TableRecyclerViewAdapter(this, infos);
            recyclerView.setAdapter(moreAdapter);
            setMoreOnItemClick();
        }

    }

    private void setMinOnItemClick() {
        minAdapter.setOnItemClickListener(new TableRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int positon, View view) {
                Toast.makeText(SelectTableActivity.this, "position==" + positon, Toast.LENGTH_SHORT).show();
                TableInfo tableInfo = minAdapter.getData().get(positon);
                Boolean isFixed = tableInfo.getNewsChannelFixed();
                if(!isFixed) {
                    minAdapter.delete(positon);
                    moreAdapter.add(moreAdapter.getItemCount(), tableInfo);
                    upDataDb(tableInfo, true);
                }


            }
        });

    }

    private void setMoreOnItemClick() {
        moreAdapter.setOnItemClickListener(new TableRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int positon, View view) {
                TableInfo tableInfo = moreAdapter.getData().get(positon);
                moreAdapter.delete(positon);
                minAdapter.add(minAdapter.getItemCount(), tableInfo);
                upDataDb(tableInfo, false);

            }


        });
    }

    /**
     * 更新数据
     *
     * @param tableInfo
     * @param isMin
     */
    private void upDataDb(final TableInfo tableInfo, final boolean isMin) {
        createThreadPool();
        mSingleThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                int channelIndex = tableInfo.getNewsChannelIndex();
                if (isMin) {
                    List<TableInfo> indexgT = TableInfoManger.loadTableIndexGt(channelIndex);
                    increaseOrReduceIndexAndUpdate(indexgT, false);

                    int targetIndex = TableInfoManger.getAllSize();
                    ChangeIsSelectAndIndex(targetIndex, false);


                } else {
                    List<TableInfo> indexLtAndIsUnselects = TableInfoManger.loadTableIndexLtAndIsUnselect(channelIndex);
                    increaseOrReduceIndexAndUpdate(indexLtAndIsUnselects, true);

                    int targetIndex = TableInfoManger.getTableInfoSelectSize();
                    ChangeIsSelectAndIndex(targetIndex, true);
                }

            }

            private void ChangeIsSelectAndIndex(int targetIndex, boolean isSelect) {
                tableInfo.setNewsChannelSelect(isSelect);
                tableInfo.setNewsChannelIndex(targetIndex);
                TableInfoManger.upData(tableInfo);

            }
        });






    }


    /**
     * 更新索引和更新数据
     *
     * @param indexLtAndIsUnselects
     * @param isIncrease
     */
    private void increaseOrReduceIndexAndUpdate(List<TableInfo> indexLtAndIsUnselects, boolean isIncrease) {
        for (TableInfo indexLtAndIsUnselect : indexLtAndIsUnselects) {
            increaseOrReduceIndex(isIncrease, indexLtAndIsUnselect);
            TableInfoManger.upData(indexLtAndIsUnselect);
        }
    }

    /**
     * 更新索引
     *
     * @param isIncrease
     * @param indexLtAndIsUnselect
     */
    private void increaseOrReduceIndex(boolean isIncrease, TableInfo indexLtAndIsUnselect) {
        int targetIndex;
        if (isIncrease) {
            targetIndex = indexLtAndIsUnselect.getNewsChannelIndex() + 1;
        } else {
            targetIndex = indexLtAndIsUnselect.getNewsChannelIndex() - 1;
        }
        indexLtAndIsUnselect.setNewsChannelIndex(targetIndex);
    }


    private void createThreadPool() {
        if (mSingleThreadPool == null) {
            mSingleThreadPool = Executors.newSingleThreadExecutor();
        }
    }


    /**
     * toolbar的点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        setResult(RESULTOK);
        finish();
    }

    @Override
    public void onBackPressed() {
        setResult(RESULTOK);
        super.onBackPressed();

    }
}
