package cs160.represent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    void findRepresentatives() {
        int zipCode = 94704;
        Candidate[] candidates = getDummyRepresentatives();
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
