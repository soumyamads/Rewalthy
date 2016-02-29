package com.rewalthy.activity;

        import android.content.Intent;
        import android.graphics.Typeface;
        import android.os.Bundle;
        import android.os.Handler;
        import android.support.v7.app.AppCompatActivity;
        import android.view.animation.Animation;

        import com.rewalthy.R;


/**
 * Created by snyxius on 9/8/2015.
 */
public class SplashActivity extends AppCompatActivity {
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;
    Typeface custom_font;
    Animation shake;

    //    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
                //Apply splash exit (fade out) and main entry (fade in) animation transitions.
//                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }


        }, SPLASH_TIME_OUT);

    }



}