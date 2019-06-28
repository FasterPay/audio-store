package com.paymentwall.sample.fragment;

import com.paymentwall.sample.DemoApplication;
import com.paymentwall.sample.R;
import com.paymentwall.sample.activity.tabs.ControllerFactory;
import com.paymentwall.sample.activity.tabs.StoreViewModel;
import com.paymentwall.sample.utils.Tools;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class FragmentTabsStore extends Fragment {

    private StoreViewModel mStoreViewModel;

    public FragmentTabsStore() {
    }

    public static FragmentTabsStore newInstance() {
        FragmentTabsStore fragment = new FragmentTabsStore();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_tabs_store, container, false);

        Tools.displayImageOriginal(getActivity(), (ImageView) root.findViewById(R.id.image_1), R.drawable.image_8);
        Tools.displayImageOriginal(getActivity(), (ImageView) root.findViewById(R.id.image_2), R.drawable.image_9);
        Tools.displayImageOriginal(getActivity(), (ImageView) root.findViewById(R.id.image_3), R.drawable.image_15);
        Tools.displayImageOriginal(getActivity(), (ImageView) root.findViewById(R.id.image_4), R.drawable.image_14);
        Tools.displayImageOriginal(getActivity(), (ImageView) root.findViewById(R.id.image_5), R.drawable.image_12);
        Tools.displayImageOriginal(getActivity(), (ImageView) root.findViewById(R.id.image_6), R.drawable.image_2);
        Tools.displayImageOriginal(getActivity(), (ImageView) root.findViewById(R.id.image_7), R.drawable.image_5);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ControllerFactory factory = ((DemoApplication) getActivity().getApplication()).mControllerFactory;
        mStoreViewModel = ViewModelProviders.of(getActivity(), factory).get(StoreViewModel.class);

        mStoreViewModel.getPurchased().observe(getActivity(), purchasedList -> {
            showDialogPaymentSuccess();
        });
    }

    private void showDialogPaymentSuccess() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        DialogSuccessPayment newFragment = new DialogSuccessPayment();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.add(android.R.id.content, newFragment).addToBackStack(null).commit();
    }
}