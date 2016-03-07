package cs160.represent;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.*;
import android.util.Log;
import android.app.AlertDialog;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Switch locationSwitch = (Switch) findViewById(R.id.useCurrentLocationSwitch);

        // Set up a listener for the switch when the user toggle's location on and off
        if (locationSwitch != null) {
            locationSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        //do stuff when Switch is ON
                        System.out.println("The switch is on!");
                    } else {
                        //do stuff when Switch if OFF
                        System.out.println("The switch is off!");
                    }
                }
            });
        }
    }

    /* Called when the user clicks the "Find Representatives!" button. */
    public void loadRepresentatives(View view) {
        Log.d(TAG, "Find Representatives! button was clicked");

        int zipCode = 94704;
        Candidate[] candidates = getDummyRepresentatives();
        Intent intent = new Intent(this, CandidateListActivity.class);

        // Check to see if the user enabled their location
        Switch userCurrentLocation = (Switch) findViewById(R.id.useCurrentLocationSwitch);
        if (userCurrentLocation.isChecked()) {
            // use their GPS-based location to find representatives
            Log.d(TAG, "Using the user's location to find representatives");

            // TODO: use Google's location services to find the user's location
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

    /* TODO: Implement this with API calls. */
    Candidate[] getRepresentativesByZipCode() {
        Candidate[] candidates = new Candidate[3];
        return candidates;
    }

    /* TODO: Implement this with API calls. */
    Candidate[] getRepresentativesByLocation() {
        Candidate[] candidates = new Candidate[3];
        return candidates;
    }

    Candidate[] getDummyRepresentatives() {
        Candidate[] candidates = new Candidate[3];

        Candidate trump = new Candidate("Donald Trump");
        Candidate clinton = new Candidate("Hillary Clinton");
        Candidate sanders = new Candidate("Bernie Sanders");

        trump.setPositionToRepresentative();
        trump.setPartyToRepublican();
        trump.setEndOfTerm("11/2016");
        trump.setEmailAddress("djtrump@gmail.com");
        trump.setWebsite("donaldjtrump.com");
        trump.setTwitterHandle("@realDonaldTrump");
        trump.addCommittee("Budget Committee");
        trump.addCommittee("Agriculture Committee");
        trump.addSponsoredBill("Education Begins At Home Act (1/16/09");
        trump.addSponsoredBill("Ready To Learn Act (1/14/09");
        trump.addSponsoredBill("Prevention First Act (1/06/09");
        candidates[0] = trump;

        clinton.setPositionToSenator();
        clinton.setPartyToDemocrat();
        clinton.setEndOfTerm("06/2016");
        clinton.setEmailAddress("hillaryc@gmail.com");
        clinton.setWebsite("hillaryclinton.com");
        clinton.setTwitterHandle("@HillaryClinton");
        clinton.addCommittee("Economy Committee");
        clinton.addCommittee("Infrastructure Committee");
        clinton.addSponsoredBill("Infrastructure First Act (1/12/09");
        clinton.addSponsoredBill("Ground Up Now Act (4/13/10");
        clinton.addSponsoredBill("Transportation for Tomorrow Act (12/10/11)");
        candidates[1] = clinton;

        sanders.setPositionToSenator();
        sanders.setPartyToIndependent();
        sanders.setEndOfTerm("07/2016");
        sanders.setEmailAddress("bernie@gmail.com");
        sanders.setWebsite("berniesanders.com");
        sanders.setTwitterHandle("@BernieSanders");
        sanders.addCommittee("Rally Committee");
        sanders.addCommittee("Business Committee");
        sanders.addSponsoredBill("Every Student Succeeds Act (12/15/16)");
        sanders.addSponsoredBill("Assault Weapons Ban (4/13/2016");
        sanders.addSponsoredBill("Adoptive Family Relief Act (2/11/2015");
        candidates[2] = sanders;

        return candidates;
    }

}
