package com.sapna.myfriends;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Sapna on 8/6/2016.
 */
public class AddActivity extends FragmentActivity {
    private final String TAG = AddActivity.class.getSimpleName();
    private TextView mNameTextView, mEmailTextView, mPhoneTextView;
    private Button mButton;
    private ContentResolver contentResolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.add_edit );
        getActionBar().setDisplayHomeAsUpEnabled( true );

        mNameTextView = (TextView) findViewById( R.id.friendName );
        mEmailTextView = (TextView) findViewById( R.id.friendEmail );
        mPhoneTextView = (TextView) findViewById( R.id.friendPhone );

        contentResolver = AddActivity.this.getContentResolver();

        mButton = (Button) findViewById( R.id.saveButton );
        mButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (isValid()) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put( FriendsContract.FriendsColumns.FRIENDS_NAME, mNameTextView.getText().toString() );
                    contentValues.put( FriendsContract.FriendsColumns.FRIENDS_EMAIL, mEmailTextView.getText().toString() );
                    contentValues.put( FriendsContract.FriendsColumns.FRIENDS_PHONE, mPhoneTextView.getText().toString() );
                    Uri returned = contentResolver.insert( FriendsContract.URI_TABLE, contentValues );
                    Log.d( TAG, "record_id returned is " + returned.toString() );
                    Intent intent = new Intent( AddActivity.this, MainActivity.class );
                    intent.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP );
                    startActivity( intent );
                    finish();
                } else {
                    Toast.makeText( getApplicationContext(), "Please enter some valid data", Toast.LENGTH_LONG ).show();

                }
            }
        } );
    }

    private boolean isValid() {
        if (mNameTextView.getText().toString().length() == 0 ||
                mEmailTextView.getText().toString().length() == 0 ||
                mPhoneTextView.getText().toString().length() == 0)
            return false;
        else
            return true;
    }

    private boolean someDataEntered() {
        if (mNameTextView.getText().toString().length() > 0 ||
                mEmailTextView.getText().toString().length() > 0 ||
                mPhoneTextView.getText().toString().length() > 0)
            return true;
        else
            return false;
    }


    @Override
    public void onBackPressed() {
        if (someDataEntered()) {
            FriendsDialog friendsDialog = new FriendsDialog();
            Bundle args = new Bundle();
            args.putString( FriendsDialog.DIALOG_TYPE, FriendsDialog.CONFIRM_EXIT );
            friendsDialog.setArguments( args );
            friendsDialog.show( getSupportFragmentManager(), "confirm-exit" );
        } else
            super.onBackPressed();
    }
}
