package com.example.geek.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.SearchView;

import com.example.geek.R;
import com.example.geek.base.BaseActivity;
import com.example.geek.fragment.AboutFragment;
import com.example.geek.fragment.CollectFragment;
import com.example.geek.fragment.GankFragment;
import com.example.geek.fragment.GoldFragment;
import com.example.geek.fragment.SettingFragment;
import com.example.geek.fragment.V2exFragment;
import com.example.geek.fragment.WeChatFragment;
import com.example.geek.fragment.ZhiHuDailyNewsFragment;
import com.example.geek.presenter.MainPresenter;
import com.example.geek.view.MainView;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<MainView, MainPresenter> {

    @BindView(R.id.myToolBar)
    Toolbar myToolBar;
    @BindView(R.id.myFrameLayout)
    FrameLayout myFrameLayout;
    @BindView(R.id.myNavigationView)
    NavigationView myNavigationView;
    @BindView(R.id.myDrawerLayout)
    DrawerLayout myDrawerLayout;
    @BindView(R.id.search_view)
    MaterialSearchView searchView;
    private ArrayList<Fragment> fragments ;
    private ArrayList<Integer> titles ;
    private FragmentManager manager;
    private final int TYPE_ZHIHU = 0;
    private final int TYPE_WECHAT = 1;
    private final int TYPE_GANK = 2;
    private final int TYPE_GOLD = 3;
    private final int TYPE_V2EX = 4;
    private final int TYPE_COLLECT = 5;
    private final int TYPE_SETTINGS = 6;
    private final int TYPE_ABOUT = 7;
    private int newsPotition;
    private MenuItem item;

    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        manager = getSupportFragmentManager();
        myToolBar.setTitleTextColor(getResources().getColor(R.color.color_ffffff));
        setSupportActionBar(myToolBar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, myDrawerLayout, myToolBar, R.string.app_name, R.string.srl_content_empty);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.color_ffffff));
        myDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        setFragment();
        addZhihu();

    }

    private void addZhihu() {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.myFrameLayout, fragments.get(0));
        transaction.commit();

        myToolBar.setTitle(titles.get(0));
    }

    private void setFragment() {
        fragments=new ArrayList<>();
        fragments.add(new ZhiHuDailyNewsFragment());
        fragments.add(new WeChatFragment());
        fragments.add(new GankFragment());
        fragments.add(new GoldFragment());
        fragments.add(new V2exFragment());
        fragments.add(new CollectFragment());
        fragments.add(new SettingFragment());
        fragments.add(new AboutFragment());
titles=new ArrayList<>();
        titles.add(R.string.zhihu);
        titles.add(R.string.wechat);
        titles.add(R.string.gank);
        titles.add(R.string.gold);
        titles.add(R.string.v2ex);
        titles.add(R.string.collect);
        titles.add(R.string.settings);
        titles.add(R.string.about);
    }

    @Override
    protected void initListenter() {
        myNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                if (itemId != R.id.info_title && itemId != R.id.option_title) {
                    menuItem.setChecked(true);
                    switch (menuItem.getItemId()) {
                        case R.id.zhihu:
                            switchFragment(TYPE_ZHIHU);

                            break;
                        case R.id.wechat:
                            switchFragment(TYPE_WECHAT);

                            break;
                        case R.id.gank:
                            switchFragment(TYPE_GANK);
                            break;
                        case R.id.gold:
                            switchFragment(TYPE_GOLD);
                            break;
                        case R.id.v2ex:
                            switchFragment(TYPE_V2EX);
                            break;
                        case R.id.collect:
                            switchFragment(TYPE_COLLECT);
                            break;
                        case R.id.settings:
                            switchFragment(TYPE_SETTINGS);
                            break;
                        case R.id.about:
                            switchFragment(TYPE_ABOUT);
                            break;

                    }

                } else {
                    menuItem.setChecked(false);
                }

                myDrawerLayout.closeDrawer(Gravity.LEFT);
                return false;
            }
        });
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Do some magic
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Do some magic
                return false;
            }
        });

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //Do some magic
            }

            @Override
            public void onSearchViewClosed() {
                //Do some magic
            }
        });
    }

    private void switchFragment(int type) {
        Fragment fragment = fragments.get(type);
        Fragment fragment1 = fragments.get(newsPotition);
        FragmentTransaction beginTransaction = manager.beginTransaction();

        if (!fragment.isAdded()) {
            beginTransaction.add(R.id.myFrameLayout, fragment);
        }
        beginTransaction.hide(fragment1);
        beginTransaction.show(fragment);

        beginTransaction.commit();
        newsPotition = type;
        if(type==TYPE_WECHAT||type==TYPE_GANK){
item.setVisible(true);
        }else {
            item.setVisible(false);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);

        item = menu.findItem(R.id.action_search);
        item.setVisible(false);
        searchView.setMenuItem(item);

        return true;
    }

    @Override
    public void onBackPressed() {
        if (searchView.isSearchOpen()) {
            searchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }
}
