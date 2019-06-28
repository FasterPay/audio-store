package com.paymentwall.sample.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.paymentwall.sample.R;
import com.paymentwall.sample.activity.tabs.TabsStore;
import com.mikhaellopez.circularimageview.CircularImageView;

import static com.paymentwall.sample.activity.tabs.TabsStore.ALBUM_NAME;
import static com.paymentwall.sample.activity.tabs.TabsStore.CARD;
import static com.paymentwall.sample.activity.tabs.TabsStore.DESCRIPTION;
import static com.paymentwall.sample.activity.tabs.TabsStore.IMAGE;
import static com.paymentwall.sample.activity.tabs.TabsStore.PRICE;

public class DialogPaymentSuccessFragment extends DialogFragment {

    private View root_view;
    private TextView nameTxt, descTxt, amountTxt;
    private CircularImageView circleAlbumImg;
    private Button checkoutBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root_view = inflater.inflate(R.layout.dialog_payment_success, container, false);
        nameTxt = root_view.findViewById(R.id.albumName);
        descTxt = root_view.findViewById(R.id.albumDesc);
        amountTxt = root_view.findViewById(R.id.albumAmount);
        circleAlbumImg = root_view.findViewById(R.id.albumImage);
        checkoutBtn = root_view.findViewById(R.id.checkoutBtn);

        ((FloatingActionButton) root_view.findViewById(R.id.fab)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        Bundle bundle = getArguments();
        final String name = bundle.getString(ALBUM_NAME);
        final String desc = bundle.getString(DESCRIPTION);
        final String price = bundle.getString(PRICE);
        int image = bundle.getInt(IMAGE);
        int cardId = bundle.getInt(CARD);
        checkoutBtn.setEnabled(true);

        nameTxt.setText(name);
        descTxt.setText(desc);
        amountTxt.setText(price);
        circleAlbumImg.setImageResource(image);

        checkoutBtn.setOnClickListener(view -> {
            ((TabsStore) getActivity()).checkout(price, desc, name, cardId);
            dismiss();
        });

        return root_view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}