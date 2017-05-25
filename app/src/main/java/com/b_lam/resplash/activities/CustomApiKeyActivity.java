package com.b_lam.resplash.activities;

import android.provider.Settings;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.b_lam.resplash.R;
import com.b_lam.resplash.Resplash;
import com.b_lam.resplash.data.tools.CustomApiManager;
import com.b_lam.resplash.util.LocaleUtils;
import com.mikepenz.iconics.context.IconicsLayoutInflater;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomApiKeyActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.custom_api_key_close_btn) ImageButton mCloseButton;
    @BindView(R.id.custom_api_key_save_btn) Button mSaveButton;
    @BindView(R.id.custom_api_key_edit_text) EditText mApiKeyEditText;
    @BindView(R.id.custom_api_secret_edit_text) EditText mApiSecretEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LayoutInflaterCompat.setFactory(getLayoutInflater(), new IconicsLayoutInflater(getDelegate()));
        super.onCreate(savedInstanceState);

        LocaleUtils.loadLocale(this);

        setContentView(R.layout.activity_custom_api_key);

        ButterKnife.bind(this);

        mCloseButton.setOnClickListener(this);
        mSaveButton.setOnClickListener(this);

        init();
    }

    public void init(){
        if (!TextUtils.isEmpty(CustomApiManager.getInstance(this).getCustomApiKey())){
            mApiKeyEditText.setText(CustomApiManager.getInstance(this).getCustomApiKey());
        }

        if (!TextUtils.isEmpty(CustomApiManager.getInstance(this).getCustomApiSecret())){
            mApiSecretEditText.setText(CustomApiManager.getInstance(this).getCustomApiSecret());
        }
    }

    @Override
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.custom_api_key_close_btn:
                finish();
                break;

            case R.id.custom_api_key_save_btn:
                CustomApiManager.getInstance(this).setCustomApi(this, mApiKeyEditText.getText().toString(), mApiSecretEditText.getText().toString());
                Toast.makeText(this, getString(R.string.saved), Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }
}
