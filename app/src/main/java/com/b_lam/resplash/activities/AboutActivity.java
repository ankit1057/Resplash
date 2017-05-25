package com.b_lam.resplash.activities;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.b_lam.resplash.Resplash;
import com.b_lam.resplash.util.LocaleUtils;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.mikepenz.iconics.context.IconicsLayoutInflater;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.b_lam.resplash.R;

public class AboutActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.toolbar_about) Toolbar mToolbar;
    @BindView(R.id.version_name) TextView mVersionName;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LayoutInflaterCompat.setFactory(getLayoutInflater(), new IconicsLayoutInflater(getDelegate()));
        super.onCreate(savedInstanceState);

        LocaleUtils.loadLocale(this);

        setContentView(R.layout.activity_about);

        ButterKnife.bind(this);

        Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material, getTheme());
        upArrow.setColorFilter(Color.parseColor("#000000"), PorterDuff.Mode.SRC_ATOP);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.about_title));

        LinearLayout [] containers = new LinearLayout[] {
                (LinearLayout) findViewById(R.id.container_about_unsplash),
                (LinearLayout) findViewById(R.id.container_about_app),
                (LinearLayout) findViewById(R.id.container_about_version),
                (LinearLayout) findViewById(R.id.container_about_intro),
                (LinearLayout) findViewById(R.id.container_about_github),
                (LinearLayout) findViewById(R.id.container_about_rate),
                (LinearLayout) findViewById(R.id.container_about_donate),
                (LinearLayout) findViewById(R.id.container_about_bug),
                (LinearLayout) findViewById(R.id.container_about_author),
                (LinearLayout) findViewById(R.id.container_about_website),
                (LinearLayout) findViewById(R.id.container_about_instagram),
                (LinearLayout) findViewById(R.id.container_about_library1),
                (LinearLayout) findViewById(R.id.container_about_library2),
                (LinearLayout) findViewById(R.id.container_about_library3),
                (LinearLayout) findViewById(R.id.container_about_library4),
                (LinearLayout) findViewById(R.id.container_about_library5),
                (LinearLayout) findViewById(R.id.container_about_library6),
                (LinearLayout) findViewById(R.id.container_about_library7),
                (LinearLayout) findViewById(R.id.container_about_library8),
                (LinearLayout) findViewById(R.id.container_about_library9),
                (LinearLayout) findViewById(R.id.container_about_library10)};
        for (LinearLayout r : containers) {
            r.setOnClickListener(this);
        }

        PackageManager manager = getApplicationContext().getPackageManager();

        try {
            PackageInfo info = manager.getPackageInfo(getApplicationContext().getPackageName(), 0);
            mVersionName.setText(info.versionName);

        }catch (PackageManager.NameNotFoundException e){
           return;
        }

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        mFirebaseAnalytics.logEvent(Resplash.FIREBASE_EVENT_VIEW_ABOUT, null);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.container_about_unsplash:
                goToURL("https://unsplash.com/" + Resplash.UNSPLASH_UTM_PARAMETERS);
                break;

            case R.id.container_about_intro:
                startActivity(new Intent(AboutActivity.this, IntroActivity.class));
                break;

            case R.id.container_about_github:
                goToURL("https://github.com/b-lam/Resplash");
                break;

            case R.id.container_about_rate:
                mFirebaseAnalytics.logEvent(Resplash.FIREBASE_EVENT_RATE_FROM_APP, null);
                goToURL("https://play.google.com/store/apps/details?id=com.b_lam.resplash");
                break;

            case R.id.container_about_donate:
                startActivity(new Intent(AboutActivity.this, DonateActivity.class));
                break;

            case R.id.container_about_bug:
                goToURL("https://github.com/b-lam/Resplash/issues");
                break;

            case R.id.container_about_website:
                goToURL("http://b-lam.github.io/");
                break;

            case R.id.container_about_instagram:
                goToURL("https://www.instagram.com/brandon.c.lam/");
                break;

            case R.id.container_about_library1:
                goToURL("https://github.com/square/retrofit");
                break;

            case R.id.container_about_library2:
                goToURL("https://github.com/bumptech/glide");
                break;

            case R.id.container_about_library3:
                goToURL("https://github.com/mikepenz/FastAdapter");
                break;

            case R.id.container_about_library4:
                goToURL("https://github.com/mikepenz/Android-Iconics");
                break;

            case R.id.container_about_library5:
                goToURL("https://github.com/mikepenz/MaterialDrawer");
                break;

            case R.id.container_about_library6:
                goToURL("https://github.com/DavidPacioianu/InkPageIndicator");
                break;

            case R.id.container_about_library7:
                goToURL("https://github.com/JakeWharton/butterknife");
                break;

            case R.id.container_about_library8:
                goToURL("https://github.com/xiprox/ErrorView");
                break;

            case R.id.container_about_library9:
                goToURL("https://github.com/chrisbanes/PhotoView");
                break;

            case R.id.container_about_library10:
                goToURL("https://github.com/Clans/FloatingActionButton");
                break;
        }
    }

    public void goToURL(String link) {
        Uri uri = Uri.parse(link);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }
}
