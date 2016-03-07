package cs160.represent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.*;
import android.util.Log;

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

        int zipCode;
        Candidate[] candidates = getDummyRepresentatives();
        Intent intent = new Intent(this, CandidateListActivity.class);

        // Check to see if the user enabled their location
        Switch userCurrentLocation = (Switch) findViewById(R.id.useCurrentLocationSwitch);
        if (userCurrentLocation.isChecked()) {
            // use their GPS-based location to find representatives
            Log.d(TAG, "Using the user's location to find representatives");
            GPSTracker gps = new GPSTracker(this);

            if(gps.canGetLocation()) {
                // The user has GPS turned on in settings
                double longitude = gps.getLongitude();
                double latitude = gps.getLatitude();
                Log.d(TAG, "GPS found: Longitude= " + longitude + " and Latitude= " + latitude);
            } else {
                // Ask the user to enable GPS in settings
                Log.d(TAG, "Asking the user to enable GPS in settings");
                gps.showSettingsAlert();
            }

            // Stop continuous GPS updates
            //gps.stopUsingGPS();

        } else {
            // use their zip code to find representatives
            Log.d(TAG, "Using the input zip code to find representatives");
            EditText userZipCodeEditText = (EditText) findViewById(R.id.zipCodeInput);
        }
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
