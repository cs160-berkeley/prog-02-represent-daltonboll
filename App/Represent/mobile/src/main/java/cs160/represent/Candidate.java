package cs160.represent;
import android.graphics.Color;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Dalton on 3/6/16.
 */
public class Candidate {

    String name;
    String twitterHandle;
    String email;
    String website;
    String party;
    String position;
    String endOfTerm;
    String imageTitle;

    List<String> committees;
    List<String> bills;

    // TODO: Use the Tweet class
    //Tweet latestTweet;
    String latestTweet;

    // For access to party colors
    Map<String, Integer> partyToColor = new HashMap<String, Integer>();

    public Candidate(String name) {
        this.name = name;
        initializeHashMaps();
    }

    public Candidate() {
        initializeHashMaps();
    }

    private void initializeHashMaps() {
        partyToColor.put("Democrat", Color.parseColor("#FF556DBE"));
        partyToColor.put("Republican", Color.parseColor("#c26363"));
        partyToColor.put("Independent", Color.parseColor("#FF5EB97F"));
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTwitterHandle(String twitterHandle) {
        this.twitterHandle = twitterHandle;
    }

    public void setEmailAddress(String email) {
        this.email = email;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setEndOfTerm(String endOfTerm) {
        this.endOfTerm = endOfTerm;
    }

    public void setPartyToDemocrat() {
        this.party = "Democrat";
    }

    public void setPartyToRepublican() {
        this.party = "Republican";
    }

    public void setPartyToIndependent() {
        this.party = "Independent";
    }

    public void setPositionToSenator() {
        this.position = "Senator";
    }

    public void setPositionToRepresentative() {
        this.position = "Representative";
    }

    public void setLatestTweet(String tweetText) {
        this.latestTweet = tweetText;
    }

    public void addCommittee(String committeeName) {
        if (this.committees == null) {
            this.committees = new ArrayList<String>();
        }

        this.committees.add(committeeName);
    }

    public void addSponsoredBill(String sponsoredBill) {
        if (this.bills == null) {
            this.bills = new ArrayList<String>();
        }

        this.bills.add(sponsoredBill);
    }

    public List<String> getCommittees() {
        return this.committees;
    }

    public List<String> getSponsoredBills() {
        return this.bills;
    }

    public int getPartyColor() {
        int color = this.partyToColor.get(this.party);
        return color;
    }

}
