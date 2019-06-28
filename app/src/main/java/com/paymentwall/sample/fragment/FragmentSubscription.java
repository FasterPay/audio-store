package com.paymentwall.sample.fragment;

import com.paymentwall.sample.DemoApplication;
import com.paymentwall.sample.R;
import com.paymentwall.sample.activity.tabs.ControllerFactory;
import com.paymentwall.sample.activity.tabs.StoreViewModel;

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

public class FragmentSubscription extends Fragment {
    public FragmentSubscription() {
    }

    public static FragmentSubscription newInstance() {
        FragmentSubscription fragment = new FragmentSubscription();
        return fragment;
    }

    private StoreViewModel mStoreViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_shopping_cart_card, container, false);
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
