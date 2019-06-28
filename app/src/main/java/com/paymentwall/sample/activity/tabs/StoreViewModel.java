package com.paymentwall.sample.activity.tabs;

import com.paymentwall.android.fasterpaysdk.FasterPay;
import com.paymentwall.android.fasterpaysdk.Form;
import com.paymentwall.android.fasterpaysdk.SubscriptionsForm;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import java.util.UUID;

public class StoreViewModel extends AndroidViewModel {

    private FasterPay mFasterPay = new FasterPay("e416c4de8ffd2b198d83713b8d232fab", true);

    public StoreViewModel(@NonNull Application application) {
        super(application);
    }

    private MutableLiveData<Integer> purchased = new MutableLiveData<>();

    public Intent checkout(Context context, String price, String desc, String name, int cardId) {
        Form form = mFasterPay.form()
            .amount(price)
            .currency("USD")
            .description(name + " " + desc)
            .merchantOrderId(UUID.randomUUID().toString())
            .successUrl("com.paymentwall.sample://song.com?cardId=" + cardId);

        return mFasterPay.prepareCheckout(context, form);
    }

    public Intent subscriptions(Context context, String price, String desc, String name, int cardId, String recurringName,
                                String recurringPeriod, String recurringTrialAmount, String recurringTrialPeriod) {
        SubscriptionsForm subscriptionsForm = mFasterPay.subscription()
            .amount(price)
            .currency("USD")
            .description(name + " " + desc)
            .merchantOrderId(UUID.randomUUID().toString())
            .successUrl("com.paymentwall.sample://subscription.com?cardId=" + cardId)
            .recurringName(recurringName)
            .recurringSkuId(UUID.randomUUID().toString())
            .recurringPeriod(recurringPeriod)
            .recurringTrialAmount(price)
            .recurringTrialAmount(recurringTrialAmount)
            .recurringTrialPeriod(recurringTrialPeriod);

        return mFasterPay.prepareCheckout(context, subscriptionsForm);
    }

    public void addToPurchaseList(int cardId) {
        purchased.setValue(cardId);
    }

    public MutableLiveData<Integer> getPurchased() {
        return purchased;
    }
}
