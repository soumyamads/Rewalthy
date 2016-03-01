package com.rewalthy.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.predsco.R;
import com.predsco.app.PredscoApp;
import com.predsco.extras.Constants;
import com.predsco.extras.Keys;
import com.predsco.utils.AutoScrollViewPager;
import com.predsco.utils.CirclePageIndicator;


public class Intro extends AppCompatActivity implements View.OnClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        AutoScrollViewPager viewPager = (AutoScrollViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new ViewPagerAdapter(R.array.icons,R.array.hints));

        viewPager.setInterval(Constants.SPLASH_TIME_OUT);
        viewPager.startAutoScroll();


        CirclePageIndicator mIndicator  = (CirclePageIndicator) findViewById(R.id.indicator);
        mIndicator.setViewPager(viewPager);

        findViewById(R.id.signIn).setOnClickListener(this);
        findViewById(R.id.registerLayout).setOnClickListener(this);


  //      viewPager.setPageTransformer(true, new CustomPageTransformer());

    }


    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){

            case R.id.registerLayout:
                PredscoApp.saveToPreferences(getApplicationContext(), Keys.intro, true);
                intent=new Intent(Intro.this,CreateAccount.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.fade_out);
                Intro.this.finish();
                break;
            case R.id.signIn:
                PredscoApp.saveToPreferences(getApplicationContext(), Keys.intro, true);
                intent = new Intent(Intro.this,Login.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.fade_out);
                Intro.this.finish();
                break;
        }
    }


    public class ViewPagerAdapter extends PagerAdapter {

        private int iconResId, hintArrayResId;

        public ViewPagerAdapter(int iconResId, int hintArrayResId) {

            this.iconResId = iconResId;
            this.hintArrayResId = hintArrayResId;
        }

        @Override
        public int getCount() {
            return getResources().getIntArray(iconResId).length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            Drawable icon = getResources().obtainTypedArray(iconResId).getDrawable(position);
            String hint = getResources().getStringArray(hintArrayResId)[position];

            View itemView = getLayoutInflater().inflate(R.layout.viewpager_item, container, false);

            ImageView iconView = (ImageView) itemView.findViewById(R.id.landing_img_slide);
            TextView hintView = (TextView)itemView.findViewById(R.id.landing_txt_hint);


            iconView.setImageDrawable(icon);
            hintView.setText(hint);

            container.addView(itemView);

            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout) object);

        }
    }


    public class CustomPageTransformer implements ViewPager.PageTransformer {


        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();

            View imageView = view.findViewById(R.id.landing_img_slide);
            View contentView = view.findViewById(R.id.landing_txt_hint);


            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left
            } else if (position <= 0) { // [-1,0]
                // This page is moving out to the left

                // Counteract the default swipe
                setTranslationX(view,pageWidth * -position);
                if (contentView != null) {
                    // But swipe the contentView
                    setTranslationX(contentView, pageWidth * position);


                    setAlpha(contentView,1 + position);
                    }

                if (imageView != null) {
                    // Fade the image in
                    setAlpha(imageView,1 + position);
                }

            } else if (position <= 1) { // (0,1]
                // This page is moving in from the right

                // Counteract the default swipe
                setTranslationX(view, pageWidth * -position);
                if (contentView != null) {
                    // But swipe the contentView
                    setTranslationX(contentView, pageWidth * position);


                    setAlpha(contentView, 1 - position);


                }
                if (imageView != null) {
                    // Fade the image out
                    setAlpha(imageView,1 - position);
                }

            }
        }
    }


    /**
     * Sets the alpha for the view. The alpha will be applied only if the running android device OS is greater than honeycomb.
     * @param view - view to which alpha to be applied.
     * @param alpha - alpha value.
     */
    private void setAlpha(View view, float alpha) {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB && ! Constants.isSliderAnimation) {
            view.setAlpha(alpha);
        }
    }

    /**
     * Sets the translationX for the view. The translation value will be applied only if the running android device OS is greater than honeycomb.
     * @param view - view to which alpha to be applied.
     * @param translationX - translationX value.
     */
    private void setTranslationX(View view, float translationX) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB && ! Constants.isSliderAnimation) {
            view.setTranslationX(translationX);
        }
    }

    public void onSaveInstanceState(Bundle outstate) {

        if(outstate != null) {
            outstate.putBoolean(Constants.SAVING_STATE_SLIDER_ANIMATION,Constants.isSliderAnimation);
        }

        super.onSaveInstanceState(outstate);
    }

    public void onRestoreInstanceState(Bundle inState) {

        if(inState != null) {
            Constants.isSliderAnimation = inState.getBoolean(Constants.SAVING_STATE_SLIDER_ANIMATION,false);
        }
        super.onRestoreInstanceState(inState);

    }
}
