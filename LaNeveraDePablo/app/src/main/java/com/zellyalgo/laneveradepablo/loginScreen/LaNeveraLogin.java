package com.zellyalgo.laneveradepablo.loginScreen;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.plus.Plus;
import com.zellyalgo.laneveradepablo.R;
import com.zellyalgo.laneveradepablo.slideFridges.BigFridge;
import com.zellyalgo.laneveradepablo.slideFridges.LittleFridge;
import com.zellyalgo.laneveradepablo.slideFridges.ThreeDCarousel;
import com.zellyalgo.laneveradepablo.utils.OnSwipeTouchListener;

public class LaNeveraLogin extends FragmentActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        View.OnClickListener {

    /* Request code used to invoke sign in user interactions. */
    private static final int RC_SIGN_IN = 0;
    private static final String TAG = "LaNeveraDePablo";

    /* Client used to interact with Google APIs. */
    private GoogleApiClient mGoogleApiClient;

    /* Is there a ConnectionResult resolution in progress? */
    private boolean mIsResolving = false;

    /* Should we automatically resolve ConnectionResults when possible? */
    private boolean mShouldResolve = false;
    //boolean to control when have to happen de animations
    private boolean mShowingBack = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_la_nevera_login);
        // Build GoogleApiClient with access to basic profile
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API)
                .addScope(new Scope(Scopes.PROFILE))
                .build();
        findViewById(R.id.pager).setVisibility(View.GONE);
        if(mShouldResolve){
            findViewById(R.id.sign_in_button).setVisibility(View.GONE);
            if (savedInstanceState == null) {
                rellenarListaNeveras();
            }
            findViewById(R.id.pager).setVisibility(View.VISIBLE);
        }
        findViewById(R.id.sign_in_button).setOnClickListener(this);
        findViewById(R.id.sign_out_button).setOnClickListener(this);
        ViewPager mPager = (ViewPager) findViewById(R.id.pager);
        ViewPagerAdapter mPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);

        //mPager.setClipChildren(false);
        mPager.setPageMargin(
                getResources().getDimensionPixelOffset(R.dimen.viewpager_margin));
        mPager.setOffscreenPageLimit(9);
        mPager.setPageTransformer(true, new ThreeDCarousel());
        gestionarTamaños(mPager);
    }

    private void gestionarTamaños(ViewPager mPager) {
        int currentElement = mPager.getCurrentItem();
        int numMaxItems = mPager.getAdapter().getCount();
        Log.d(TAG, "currentElement" + currentElement);
        Log.d(TAG, "numMaxItems" + numMaxItems);
        View prev, next;
        if(currentElement > 0) {
            prev = mPager.getChildAt(currentElement - 1);
            //prev.setPadding(40, 40, 40, 40);
        }
        if(currentElement < numMaxItems) {
            next = mPager.getChildAt(currentElement + 1);
            //Log.d(TAG, next.toString());
            //next.setPadding(40, 40, 40, 40);
            //TextView text = (TextView)next.findViewById(R.id.textFragment);

        }
    }

    public void rellenarListaNeveras (){

        /*getFragmentManager()
                .beginTransaction()
                .add(R.id.prevFridge, new LittleFridge())
                .add(R.id.fridge, new BigFridge())
                .add(R.id.nextFridge, new LittleFridge())
                .commit();*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_la_nevera_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.sign_in_button) {
            onSignInClicked();
        }
        if (v.getId() == R.id.sign_out_button) {
            onSignOutClicked();
        }
    }

    private void onSignOutClicked() {
        if (mGoogleApiClient.isConnected()) {
            Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
            mGoogleApiClient.disconnect();
            Toast toast = Toast.makeText(this, "TE HAS DECONECTADO", Toast.LENGTH_SHORT);
            toast.show();

            findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
            findViewById(R.id.pager).setVisibility(View.GONE);
        }
    }

    private void onSignInClicked() {
        // User clicked the sign-in button, so begin the sign-in process and automatically
        // attempt to resolve any errors that occur.
        mShouldResolve = true;
        mGoogleApiClient.connect();

        // Show a message to the user that we are signing in.
        Toast toast = Toast.makeText(this, "ESTAS CONECTADO", Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void onConnected(Bundle bundle) {
        // onConnected indicates that an account was selected on the device, that the selected
        // account has granted any requested permissions to our app and that we were able to
        // establish a service connection to Google Play services.
        Log.d(TAG, "onConnected:" + bundle);
        findViewById(R.id.sign_in_button).setVisibility(View.GONE);
        findViewById(R.id.pager).setVisibility(View.VISIBLE);
        rellenarListaNeveras();
        mShouldResolve = false;
        if (Plus.AccountApi.getAccountName(mGoogleApiClient) != null) {
            String email = Plus.AccountApi.getAccountName(mGoogleApiClient);;
            Toast toast = Toast.makeText(this, email, Toast.LENGTH_SHORT);
            toast.show();
        } else {
            Toast toast = Toast.makeText(this, "NO HAY PERSONA?", Toast.LENGTH_SHORT);
            toast.show();
        }

        // Show the signed-in UI

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult:" + requestCode + ":" + resultCode + ":" + data);

        if (requestCode == RC_SIGN_IN) {
            // If the error resolution was not successful we should not resolve further.
            if (resultCode != RESULT_OK) {
                mShouldResolve = false;
            }

            mIsResolving = false;
            mGoogleApiClient.connect();
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        // Could not connect to Google Play Services.  The user needs to select an account,
        // grant permissions or resolve an error in order to sign in. Refer to the javadoc for
        // ConnectionResult to see possible error codes.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);

        if (!mIsResolving && mShouldResolve) {
            if (connectionResult.hasResolution()) {
                try {
                    connectionResult.startResolutionForResult(this, RC_SIGN_IN);
                    mIsResolving = true;
                } catch (IntentSender.SendIntentException e) {
                    Log.e(TAG, "Could not resolve ConnectionResult.", e);
                    mIsResolving = false;
                    mGoogleApiClient.connect();
                }
            } else {
                // Could not resolve the connection result, show the user an
                // error dialog.
                showErrorDialog(connectionResult);
            }
        } else {

        }
    }

    private void showErrorDialog(ConnectionResult connectionResult) {
    }
}
