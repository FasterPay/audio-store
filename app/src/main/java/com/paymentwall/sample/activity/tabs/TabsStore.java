package com.paymentwall.sample.activity.tabs;

import com.paymentwall.sample.DemoApplication;
import com.paymentwall.sample.R;
import com.paymentwall.sample.fragment.DialogPaymentSuccessFragment;
import com.paymentwall.sample.fragment.FragmentSubscription;
import com.paymentwall.sample.fragment.FragmentTabsStore;
import com.paymentwall.sample.utils.Tools;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class TabsStore extends AppCompatActivity {

    public static final String ALBUM_NAME = "name";
    public static final String DESCRIPTION = "desc";
    public static final String IMAGE = "image";
    public static final String PRICE = "price";
    public static final String CARD = "card";

    private ViewPager view_pager;
    private TabLayout tab_layout;

    private StoreViewModel mStoreViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs_store);

        ControllerFactory factory = ((DemoApplication) getApplication()).mControllerFactory;
        mStoreViewModel = ViewModelProviders.of(this, factory).get(StoreViewModel.class);

        if (getIntent() != null && getIntent().getData() != null) {
            Log.d("DATPH", getIntent().getData().toString());
        }

        initToolbar();
        initComponent();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        Uri uri = intent.getData();
        if (uri != null) {
            try {
                String host = uri.getHost();
                int page = 0;
                switch (host) {
                    case "subscription.com":
                        page = 1;
                        break;
                }
                view_pager.setCurrentItem(page);
                String cardId = uri.getQueryParameter("cardId");
                mStoreViewModel.addToPurchaseList(Integer.parseInt(cardId));
            } catch (Exception e) {
                Log.d("DATPH", "exception is: " + e);
            }
        }
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Store");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this, R.color.blue_grey_600);
    }

    private void initComponent() {
        view_pager = (ViewPager) findViewById(R.id.view_pager);
        setupViewPager(view_pager);

        tab_layout = (TabLayout) findViewById(R.id.tab_layout);
        tab_layout.setupWithViewPager(view_pager);
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(FragmentTabsStore.newInstance(), "CHECKOUT");
        adapter.addFragment(FragmentSubscription.newInstance(), "SUBSCRIPTIONS");
        viewPager.setAdapter(adapter);
    }

    private class SectionsPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public SectionsPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    public void onCardClick(View view) {
        Log.d("DATPH activity", "card = " + view.getId());
        Bundle bundle = checkoutAlbum(view.getId());
        showDialogPaymentSuccess(bundle);
    }

    public void onSubscriptionClick(View view) {
        Log.d("DATPH activity", "subscription = " + view.getId());
        subscription(view.getId());
    }

    private Bundle checkoutAlbum(int id) {
        String albumName = "";
        String description = "";
        int image = R.drawable.image_1;
        String price = "0.00";
        int cardId = 0;
        switch (id) {
            case R.id.card1:
                albumName = "Mauris sagittis non elit";
                description = "Kodaline";
                image = R.drawable.image_8;
                price = "5.00";
                cardId = id;
                break;
            case R.id.card2:
                albumName = "Aliquam";
                description = "One Republic";
                image = R.drawable.image_9;
                price = "5.30";
                cardId = id;
                break;
            case R.id.card3:
                albumName = "Curabitur tempus";
                description = "Kodaline";
                image = R.drawable.image_15;
                price = "5.30";
                cardId = id;
                break;
            case R.id.card4:
                albumName = "Quisque";
                description = "Kodaline";
                image = R.drawable.image_14;
                price = "5.30";
                cardId = id;
                break;
            case R.id.card5:
                albumName = "Aliquam ac elit";
                description = "Kodaline";
                image = R.drawable.image_12;
                price = "5.30";
                cardId = id;
                break;
            case R.id.card6:
                albumName = "Suspendisse ornare";
                description = "Kodaline";
                image = R.drawable.image_2;
                price = "5.45";
                cardId = id;
                break;
            case R.id.card7:
                albumName = "Placerat vel ipsum";
                description = "Kodaline";
                image = R.drawable.image_5;
                price = "7.34";
                cardId = id;
                break;
        }
        Bundle bundle = new Bundle();
        bundle.putString(ALBUM_NAME, albumName);
        bundle.putString(DESCRIPTION, description);
        bundle.putInt(IMAGE, image);
        bundle.putString(PRICE, price);
        bundle.putInt(CARD, cardId);
        return bundle;
    }

    private void subscription(int id) {
        String albumName = "";
        String description = "";
        String price = "0.00";
        int cardId = 0;
        String recurringName = "";
        String recurringPeriod = "";
        String recurringTrialPeriod = "";
        String recurringTrialAmount = "";
        switch (id) {
            case R.id.cardPraesent:
                albumName = "Praesent Vitae";
                description = "Test product description";
                price = "3.00";
                cardId = id;
                recurringName = "Test FP Recurring";
                recurringPeriod = "1m";
                recurringTrialAmount = "3.00";
                recurringTrialPeriod = "3m";
                break;
            case R.id.cardMaecenas:
                albumName = "Maecenas Quis";
                description = "Test product description";
                price = "31.30";
                cardId = id;
                recurringName = "Test FP Recurring";
                recurringPeriod = "1m";
                recurringTrialAmount = "9.20";
                recurringTrialPeriod = "1m";
                break;
            case R.id.cardFringilla:
                albumName = "Praesent Vitae";
                description = "Test product description";
                price = "41.30";
                cardId = id;
                recurringName = "Test FP Recurring";
                recurringPeriod = "1m";
                recurringTrialAmount = "11.50";
                recurringTrialPeriod = "1m";
                break;
        }

        startActivity(mStoreViewModel.subscriptions(this, price, description, albumName, cardId, recurringName,
                                                    recurringPeriod, recurringTrialAmount, recurringTrialPeriod));
    }

    private void showDialogPaymentSuccess(Bundle bundle) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        DialogPaymentSuccessFragment newFragment = new DialogPaymentSuccessFragment();
        newFragment.setArguments(bundle);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.add(android.R.id.content, newFragment).addToBackStack(null).commit();
    }

    public void checkout(String price, String desc, String name, int cardId) {
        startActivity(mStoreViewModel.checkout(this, price, desc, name, cardId));
    }
}