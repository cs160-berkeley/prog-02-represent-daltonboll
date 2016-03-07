package cs160.represent;

import android.content.Context;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Dalton on 3/6/16.
 * Credit for list view: http://www.mkyong.com/android/android-listview-example/
 *   and: https://github.com/codepath/android_guides/wiki/Using-an-ArrayAdapter-with-ListView
 */
public class CandidateArrayAdapter extends ArrayAdapter<Candidate> {
    private static final String TAG = "CandidateArrayAdapter";
    private final Context context;
    private final Candidate[] candidates;

    public CandidateArrayAdapter(Context context, Candidate[] candidates) {
        super(context, R.layout.individual_candidate_list_view, candidates);
        this.context = context;
        this.candidates = candidates;
    }

    @Override
    public View getView(int index, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // Check if the existing rowView is being reused, else inflate the view
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.individual_candidate_list_view, parent, false);
        }

        TextView nameView = (TextView) convertView.findViewById(R.id.candidateName);
        TextView partyView = (TextView) convertView.findViewById(R.id.candidatePartyInfo);
        TextView emailView = (TextView) convertView.findViewById(R.id.candidateEmail);
        TextView websiteView = (TextView) convertView.findViewById(R.id.candidateWebsite);
        TextView twitterHandleView = (TextView) convertView.findViewById(R.id.candidateTwitterHandle);
        TextView latestTweetView = (TextView) convertView.findViewById(R.id.candidateLatestTweet);
        ImageView image = (ImageView) convertView.findViewById(R.id.candidateImage);

        Candidate currentCandidate = candidates[index];

        String imageTitle = currentCandidate.imageTitle;
        image.setImageResource(context.getResources().getIdentifier(imageTitle, "drawable", context.getPackageName()));

        nameView.setText(currentCandidate.name);
        partyView.setText(currentCandidate.position + " | " + currentCandidate.party);
        emailView.setText(currentCandidate.email);
        websiteView.setText(currentCandidate.website);

        twitterHandleView.setText(Html.fromHtml("<a href=\"http://twitter.com/" + currentCandidate.twitterHandle + "\">" + currentCandidate.twitterHandle + "</a>"));
        twitterHandleView.setMovementMethod(LinkMovementMethod.getInstance());
        latestTweetView.setText("-- \"" + currentCandidate.latestTweet + "\"");

        // Add an onClickListener for the "More Info" button
        addButtonListener(currentCandidate, convertView);

        return convertView;
    }

    // Add an onClickListener for each candidate "More Info" button
    public void addButtonListener(final Candidate currentCandidate, View convertView) {
        Button button = (Button) convertView.findViewById(R.id.moreInfoButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Log.d(TAG, "More info button clicked for " + currentCandidate.name);
            }
        });
    }
}