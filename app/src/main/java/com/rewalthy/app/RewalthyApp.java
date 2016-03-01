package com.rewalthy.app;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.facebook.FacebookSdk;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.predsco.extras.Constants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormatSymbols;
import java.util.Set;

import io.fabric.sdk.android.Fabric;


/**
 * Created by snyxius on 9/14/2015.
 */


public class RewalthyApp extends Application {

    public static PredscoApp mInstance;
    private static Context context;
    public static Toast toast;

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        context = this;
        mInstance = this;
        FacebookSdk.sdkInitialize(getApplicationContext());
        initImageLoader(getApplicationContext());
    }

    public static PredscoApp getInstance() {
        return mInstance;
    }


    public static Context getAppContext() {
        return mInstance.getApplicationContext();
    }

    public static void showAToast(String st) {
        try {
            toast.getView().isShown();
            toast.setGravity(Gravity.CENTER, Constants.DEFAULT_INT, Constants.DEFAULT_INT);
            toast.setText(st);
            //toast = Toast.makeText(context, st, Toast.LENGTH_SHORT);
        } catch (Exception e) { // invisible if exception
            toast = Toast.makeText(context, st, Toast.LENGTH_SHORT);
        }
        toast.show();
    }

    public static String getMonth(int month) {
        return new DateFormatSymbols().getMonths()[month];
    }


    // UNIVERSAL IMAGE LOADER SETUP
    public static void initImageLoader(Context context) {
        // This configuration tuning is custom. You can tune every option, you may tune some of them,
        // or you can create default configuration by
        //  ImageLoaderConfiguration.createDefault(this);
        // method.
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.writeDebugLogs(); // Remove for release app

        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config.build());
    }


    /********************************************************************/
    /**
     * @param inputStream
     *            from create authentication api
     * @return response String value
     * @throws IOException
     */
    /********************************************************************/
    public static String convertInputStreamToString(InputStream inputStream)
            throws IOException {
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while ((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }

    /**************************************************************************************/
    /**
     * @return bool value based on the internet connection availability
     */
    /**************************************************************************************/
    public static boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager
                .getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    public static void saveToPreferences(Context context, String preferenceName, String preferenceValue) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(preferenceName, preferenceValue);
        editor.apply();
    }

    public static void saveToPreferences(Context context, String preferenceName, int preferenceValue) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(preferenceName, preferenceValue);
        editor.apply();
    }
    public static void saveToPreferences(Context context, String preferenceName, boolean preferenceValue) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(preferenceName, preferenceValue);
        editor.apply();
    }
    public static void saveToPreferences(Context context, String preferenceName, Float preferenceValue) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(preferenceName, preferenceValue);
        editor.apply();
    }
    public static void saveToPreferences(Context context, String preferenceName, Set<String> preferenceValue) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet(preferenceName, preferenceValue);
        editor.apply();
    }


    public static Set<String> readFromPreferences(Context context, String preferenceName, Set<String> defaultValue) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        return sharedPreferences.getStringSet(preferenceName, defaultValue);
    }


    public static String readFromPreferences(Context context, String preferenceName, String defaultValue) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        return sharedPreferences.getString(preferenceName, defaultValue);
    }

    public static int readFromPreferences(Context context, String preferenceName, int defaultValue) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        return sharedPreferences.getInt(preferenceName, defaultValue);
    }
    public static boolean readFromPreferences(Context context, String preferenceName, boolean defaultValue) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        return sharedPreferences.getBoolean(preferenceName, defaultValue);
    }
    public static float readFromPreferences(Context context, String preferenceName, float defaultValue) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        return sharedPreferences.getFloat(preferenceName, defaultValue);
    }

    public static void clearSharedPrefData(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }



    public static String replace(String str) {
        return str.replaceAll(" ", "%20");
    }
}
