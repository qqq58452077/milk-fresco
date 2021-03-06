package f.star.iota.milk.base;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {

    private final long[] mHints = new long[2];
    protected Context mContext;
    protected Context aContext;
    private Unbinder unbinder;

    //记录当前加载的是哪个Fragment
    protected BaseFragment currSearchFragment;

    protected void handleIntent(Intent intent) {

    }

    private void setFullScreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    protected boolean isFullScreen() {
        return false;
    }

    protected abstract void init();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isFullScreen()) {
            setFullScreen();
        }
        setContentView(getContentViewId());
        aContext = getApplicationContext();
        mContext = this;
        unbinder = ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        init();
        if (null != getIntent()) {
            handleIntent(getIntent());
        }
        setFirstFragment();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    protected abstract int getContentViewId();

    protected int getFragmentContainerId() {
        return 0;
    }

    protected void setFirstFragment() {

    }

    public void showFragment(BaseFragment fragment) {
        if (fragment != null) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction tx = fm.beginTransaction();
            tx.replace(getFragmentContainerId(), fragment);
            tx.commit();
            currSearchFragment = fragment;
        }
    }

    public void addFragment(BaseFragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .add(getFragmentContainerId(), fragment, fragment.getClass().getSimpleName())
                    .addToBackStack(fragment.getClass().getSimpleName())
                    .commitAllowingStateLoss();
        }
    }

    protected void exit() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            System.arraycopy(mHints, 1, mHints, 0, mHints.length - 1);
            mHints[mHints.length - 1] = SystemClock.uptimeMillis();
            Snackbar.make(findViewById(android.R.id.content), "", Snackbar.LENGTH_SHORT).setAction("再按一次退出", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.exit(0);
                }
            }).show();
            if (SystemClock.uptimeMillis() - mHints[0] <= 1600) {
                System.exit(0);
            }
        }
    }

    public void removeFragmentContainerChildrenViews() {
        ((ViewGroup) findViewById(getFragmentContainerId())).removeAllViews();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void dealEvent(RVBean rv) {
    }
}
