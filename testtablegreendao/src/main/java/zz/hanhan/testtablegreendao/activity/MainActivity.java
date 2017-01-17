package zz.hanhan.testtablegreendao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import zz.hanhan.testtablegreendao.R;
import zz.hanhan.testtablegreendao.adapter.MyFragmentPagerAdapter;
import zz.hanhan.testtablegreendao.entity.TableInfo;
import zz.hanhan.testtablegreendao.fragment.MyFragment;
import zz.hanhan.testtablegreendao.util.TableInfoManger;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private static final int REQUESTCODE = 1;
    private static final int RESULTOK = 2;
    private ImageView add_channel_iv;
    private ViewPager viewpager;

    private ArrayList<MyFragment> fragments;
    private MyFragmentPagerAdapter adapter;
    private TabLayout tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        //初始化数据库
        TableInfoManger.initDB();
        Toast.makeText(MainActivity.this, "initDB完成", Toast.LENGTH_SHORT).show();

        tabs = (TabLayout) findViewById(R.id.tabs);
        add_channel_iv = (ImageView) findViewById(R.id.add_channel_iv);
        viewpager = (ViewPager) findViewById(R.id.viewpager);

        add_channel_iv.setOnClickListener(this);

        initData();
    }

    private void initData() {
        List<String> tableNames = new ArrayList<>();
        List<TableInfo> tableInfos = TableInfoManger.loadMine();
        for (TableInfo tableInfo : tableInfos) {
            tableNames.add(tableInfo.getNewsChannelName());
        }

        //初始化数据
        fragments = new ArrayList<>();
        for (int i = 0; i < tableInfos.size(); i++) {
            MyFragment myFragment = new MyFragment(tableNames.get(i), "内容" + i);
            fragments.add(myFragment);
        }


        //设置ViewPager的适配器
        adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        viewpager.setAdapter(adapter);
        //关联ViewPager
        tabs.setupWithViewPager(viewpager);
        //设置固定的
//        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabs.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    /*
        点击加号+
     */
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, SelectTableActivity.class);
        startActivityForResult(intent, REQUESTCODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {

            case RESULTOK:
                initData();

                break;
            default:
                break;
        }
    }
}
