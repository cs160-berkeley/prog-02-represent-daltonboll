package cs160.represent;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.*;
import android.util.Log;
import android.app.AlertDialog;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(Color.parseColor("#51b7e3"));

        Switch locationSwitch = (Switch) findViewById(R.id.useCurrentLocationSwitch);

        // Set up a listener for the switch when the user toggle's location on and off
        if (locationSwitch != null) {
            locationSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    EditText userZipCodeEditText = (EditText) findViewById(R.id.zipCodeInput);

                    if (isChecked) {
                        //do stuff when Switch is ON
                        System.out.println("The switch is on!");
                        userZipCodeEditText.setEnabled(false); // grey out the zip code entry
                    } else {
                        //do stuff when Switch if OFF
                        System.out.println("The switch is off!");
                        userZipCodeEditText.setEnabled(true); // re-enable the zip code entry
                    }
                }
            });
        }
    }

    /* Called when the user clicks the "Find Representatives!" button. */
    public void loadRepresentatives(View view) {
        Log.d(TAG, "Find Representatives! button was clicked");

        int zipCode = 94704;
        Intent intent = new Intent(this, CandidateListActivity.class);

        // Check to see if the user enabled their location
        Switch userCurrentLocation = (Switch) findViewById(R.id.useCurrentLocationSwitch);
        if (userCurrentLocation.isChecked()) {
            // use their GPS-based location to find representatives
            Log.d(TAG, "Using the user's location to find representatives");

            // TODO: use Google's location services to find the user's location

            // Start the CandidateListActivity view
            intent.putExtra("zipCode", zipCode); // Pass the zipcode to the view
            this.startActivity(intent);
        } else {
            // use their zip code to find representatives
            Log.d(TAG, "Using the input zip code to find representatives");
            EditText userZipCodeEditText = (EditText) findViewById(R.id.zipCodeInput);

            try {
                int inputZipCode = Integer.parseInt(userZipCodeEditText.getText().toString());
                Log.d(TAG, "The user input zip code is: " + inputZipCode);

                if (!isValidZipCode(inputZipCode)) {
                    Log.d(TAG, "The user hasn't entered a valid zip code");
                    displayDialog("Please enter a valid 5-character Zip Code (e.g. 94704)");
                } else {
                    // The user has entered a valid zip code
                    zipCode = inputZipCode;

                    // Start the CandidateListActivity view
                    intent.putExtra("zipCode", zipCode); // Pass the zipcode to the view
                    this.startActivity(intent);
                }
            } catch (NumberFormatException e) {
                Log.d(TAG, "The user hasn't entered a zip code yet");
                displayDialog("Please enter a valid Zip Code (e.g. 94704)");
            }
        }
    }

    boolean isValidZipCode(int zipCode) {
        int ZIP_CODE_LENGTH = 5;
        String zipCodeString = String.valueOf(zipCode);

        return zipCodeString.length() == ZIP_CODE_LENGTH;
    }

    /*
     * Credit: http://www.mkyong.com/android/android-alert-dialog-example/
     */
    void displayDialog(String message) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // set title
        alertDialogBuilder.setTitle("Wait a minute!");

        // set dialog message
        alertDialogBuilder
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, close the dialog
                        dialog.cancel();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

}
