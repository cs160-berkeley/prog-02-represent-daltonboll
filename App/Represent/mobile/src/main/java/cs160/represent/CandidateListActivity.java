package cs160.represent;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

// Credit for list view: http://www.mkyong.com/android/android-listview-example/
//   and: https://github.com/codepath/android_guides/wiki/Using-an-ArrayAdapter-with-ListView
public class CandidateListActivity extends ListActivity {

    private static final String TAG = "CandidateListActivity";
    private Candidate[] candidates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_candidate_list);

        // Get a list of candidates for this area
        // TODO: make sure to use external APIs to grab this data
        Intent intent = getIntent();
        int zipCode = intent.getIntExtra("zipCode", 94704);
        Log.d(TAG, "Received zip code: " + zipCode);
        candidates = getDummyRepresentatives();

        // Initialize the ListView logic
        setListAdapter(new CandidateArrayAdapter(this, candidates));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        //get selected items
        String selectedValue = (String) getListAdapter().getItem(position);
        Toast.makeText(this, selectedValue, Toast.LENGTH_SHORT).show();
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
        trump.setLatestTweet("Tonight is gonna be huuuuge!");
        trump.addCommittee("Budget Committee");
        trump.addCommittee("Agriculture Committee");
        trump.addSponsoredBill("Education Begins At Home Act (1/16/09");
        trump.addSponsoredBill("Ready To Learn Act (1/14/09");
        trump.addSponsoredBill("Prevention First Act (1/06/09");
        trump.imageTitle = "donald_trump";
        candidates[0] = trump;

        clinton.setPositionToSenator();
        clinton.setPartyToDemocrat();
        clinton.setEndOfTerm("06/2016");
        clinton.setEmailAddress("hillaryc@gmail.com");
        clinton.setWebsite("hillaryclinton.com");
        clinton.setTwitterHandle("@HillaryClinton");
        clinton.setLatestTweet("Let's get some votes tonight!");
        clinton.addCommittee("Economy Committee");
        clinton.addCommittee("Infrastructure Committee");
        clinton.addSponsoredBill("Infrastructure First Act (1/12/09");
        clinton.addSponsoredBill("Ground Up Now Act (4/13/10");
        clinton.addSponsoredBill("Transportation for Tomorrow Act (12/10/11)");
        clinton.imageTitle = "hillary_clinton";
        candidates[1] = clinton;

        sanders.setPositionToSenator();
        sanders.setPartyToIndependent();
        sanders.setEndOfTerm("07/2016");
        sanders.setEmailAddress("bernie@gmail.com");
        sanders.setWebsite("berniesanders.com");
        sanders.setTwitterHandle("@BernieSanders");
        sanders.setLatestTweet("We are gonna rock it tonight!");
        sanders.addCommittee("Rally Committee");
        sanders.addCommittee("Business Committee");
        sanders.addSponsoredBill("Every Student Succeeds Act (12/15/16)");
        sanders.addSponsoredBill("Assault Weapons Ban (4/13/2016");
        sanders.addSponsoredBill("Adoptive Family Relief Act (2/11/2015");
        sanders.imageTitle = "bernie_sanders";
        candidates[2] = sanders;

        return candidates;
    }
}
