package com.example.administrator.tabtest;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.administrator.tabtest.fragment.BaseFragment;
import com.example.administrator.tabtest.fragment.FragmentFactory;
import com.example.administrator.tabtest.fragment.Home1Fra;
import com.example.administrator.tabtest.fragment.Home2Fra;
import com.example.administrator.tabtest.fragment.Home3Fra;
import com.example.administrator.tabtest.fragment.Home4Fra;
import com.example.administrator.tabtest.fragment.Home5Fra;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static String TAG = MainActivity.class.getSimpleName();

    private ImageButton mBt0, mBt1, mBt4;
    private View mBt2, mBt3;
    private View mRedMark; // 我的消息红色标记
    private View mRedMarkFq;// 粉圈红色标记
    private View statusBar;

    private static int mCurrntTabInt = -1;
    private static String mCurrentFragmentTag;
    private static BaseFragment mCurrentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initViews();

        switchTabChoosed(0);
        switchContent(Home1Fra.FRAGMENT_TAG);

    }

    private void initViews() {
        statusBar = (View) findViewById(R.id.status_bar);
        mBt0 = (ImageButton) findViewById(R.id.tab_iv_0);
        mBt1 = (ImageButton) findViewById(R.id.tab_iv_1);
        mBt2 = findViewById(R.id.tab_iv_2);
        mBt3 = findViewById(R.id.tab_iv_3);
        mBt4 = (ImageButton) findViewById(R.id.tab_iv_4);
        mRedMark = findViewById(R.id.red_mark);
        mRedMarkFq = findViewById(R.id.red_mark0);

        mBt0.setOnClickListener(this);
        mBt1.setOnClickListener(this);
        mBt2.setOnClickListener(this);
        mBt3.setOnClickListener(this);
        mBt4.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.tab_iv_0:
                switchTabChoosed(0);
                switchContent(Home1Fra.FRAGMENT_TAG);
                break;
            case R.id.tab_iv_1:
                switchTabChoosed(1);
                switchContent(Home2Fra.FRAGMENT_TAG);
                break;
            case R.id.tab_iv_2:
                switchTabChoosed(2);
                switchContent(Home3Fra.FRAGMENT_TAG);
                break;
            case R.id.tab_iv_3:
                switchTabChoosed(3);
                switchContent(Home4Fra.FRAGMENT_TAG);
                break;
            case R.id.tab_iv_4:
                switchTabChoosed(4);
                switchContent(Home5Fra.FRAGMENT_TAG);
                break;
            default:
                Log.e(TAG, "tabs 5 or -1");
                break;
        }
    }

    public void switchTabChoosed(int tab) {
        mCurrntTabInt = tab;
        switch (tab) {
            case 0:
                mBt0.setSelected(true);
                mBt1.setSelected(false);
                mBt2.setSelected(false);
                mBt3.setSelected(false);
                mBt4.setSelected(false);
                break;
            case 1:
                mBt0.setSelected(false);
                mBt1.setSelected(true);
                mBt2.setSelected(false);
                mBt3.setSelected(false);
                mBt4.setSelected(false);
                break;
            case 2:
                mBt0.setSelected(false);
                mBt1.setSelected(false);
                mBt2.setSelected(true);
                mBt3.setSelected(false);
                mBt4.setSelected(false);
                break;
            case 3:
                mBt0.setSelected(false);
                mBt1.setSelected(false);
                mBt2.setSelected(false);
                mBt3.setSelected(true);
                mBt4.setSelected(false);
                break;
            case 4:
                mBt0.setSelected(false);
                mBt1.setSelected(false);
                mBt2.setSelected(false);
                mBt3.setSelected(false);
                mBt4.setSelected(true);
                break;
            default:
                break;
        }
    }



    private BaseFragment toFragment;

    /**
     * 切换Fragment
     *
     * @throws Exception
     */
    public void switchContent(String tag) {
        Log.d(TAG, "switchContent tag " + tag);

        mCurrentFragmentTag = tag;

        toFragment = (BaseFragment) getSupportFragmentManager()
                .findFragmentByTag(tag);

        // 强制刷新Home2Fra界面
        if (toFragment != null
                && tag.equals(Home2Fra.FRAGMENT_TAG)) {
            if (toFragment.isVisible()) {
                FragmentTransaction fmt = getSupportFragmentManager()
                        .beginTransaction();
                fmt.remove(toFragment);
                toFragment = null;
            }
        }

        // 强制刷新Home2Fra界面
        if (toFragment != null && tag.equals(Home2Fra.FRAGMENT_TAG)) {
            if (toFragment.isVisible()) {
                FragmentTransaction fmt = getSupportFragmentManager()
                        .beginTransaction();
                fmt.remove(toFragment);
                toFragment = null;
            }
        }

        // 强制刷Home2Fra界面
        if (toFragment != null
                && tag.equals(Home2Fra.FRAGMENT_TAG)) {
            if (toFragment.isVisible()) {
                FragmentTransaction fmt = getSupportFragmentManager()
                        .beginTransaction();
                fmt.remove(toFragment);
                toFragment = null;
            }
        }

        if (toFragment == null) {
            Log.d(TAG, "toFragment == null " + tag);
            toFragment = FragmentFactory.getFragmentByTag(tag);
            if (toFragment == null) {
                throw new NullPointerException(
                        "you should create a new Fragment by Tag" + tag);
            }

            FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                    .beginTransaction();
            fragmentTransaction
                    .add(R.id.lay_content_container, toFragment, tag);
            if (mCurrentFragment != null) {
                fragmentTransaction.hide(mCurrentFragment);
            }
            fragmentTransaction.commit();
            mCurrentFragment = toFragment;
        } else {
            if (mCurrentFragment == toFragment) {
                return;
            }
            if (!toFragment.isAdded()) {
                Log.d(TAG, "!toFragment.isAdded() " + tag);
                FragmentTransaction fmt = getSupportFragmentManager()
                        .beginTransaction();
                if (mCurrentFragment != null) {
                    fmt.hide(mCurrentFragment);
                }
                fmt.add(R.id.lay_content_container, toFragment, tag);
                fmt.commit();
                mCurrentFragment = toFragment;
            } else {
                Log.d(TAG, "toFragment.isAdded() " + tag);
                if (toFragment.isHidden()) {
                    Log.d(TAG,
                            "toFragment.isHidden() " + tag + toFragment.getId());
                }
                FragmentTransaction fmt = getSupportFragmentManager()
                        .beginTransaction();
                if (mCurrentFragment != null) {
                    Log.d(TAG, "mCurrentFragment != null "
                            + mCurrentFragment.getTag());
                    fmt.hide(mCurrentFragment);
                } else {
                    Log.d(TAG, "mCurrentFragment == null ");
                }
                fmt.show(toFragment).commit();
                mCurrentFragment = toFragment;
            }
        }

    }


    private static Boolean isQuit = false;
    private Timer timer = new Timer();

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!isQuit) {
                isQuit = true;
                Toast.makeText(getBaseContext(),
                        R.string.back_more_quit, Toast.LENGTH_LONG).show();
                TimerTask task = null;
                task = new TimerTask() {
                    @Override
                    public void run() {
                        isQuit = false;
                    }
                };
                timer.schedule(task, 2000);
            } else {
                finish();
                System.exit(0);
            }
        }
        return false;
    }


    @Override
    protected void onDestroy() {
        mCurrentFragmentTag = null;
        mCurrntTabInt = -1;
        super.onDestroy();
    }

    @Override
    public void finish() {
        mCurrentFragmentTag = null;
        mCurrntTabInt = -1;
        super.finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void freshUI(int newsCount) {
        if (newsCount > 0)
            mRedMark.setVisibility(View.VISIBLE);
        else
            mRedMark.setVisibility(View.GONE);
    }

    private void freshFqUI(int newsCount) {
        if (newsCount > 0)
            mRedMarkFq.setVisibility(View.VISIBLE);
        else
            mRedMarkFq.setVisibility(View.GONE);
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
