package cs160.represent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Dalton on 3/6/16.
 * Credit for list view: http://www.mkyong.com/android/android-listview-example/
 *   and: https://github.com/codepath/android_guides/wiki/Using-an-ArrayAdapter-with-ListView
 */
public class CandidateArrayAdapter extends ArrayAdapter<Candidate> {
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
        TextView partyView = (TextView) convertView.findViewById(R.id.candidateParty);
        TextView emailView = (TextView) convertView.findViewById(R.id.candidateEmail);
        TextView websiteView = (TextView) convertView.findViewById(R.id.candidateWebsite);
        TextView twitterHandleView = (TextView) convertView.findViewById(R.id.candidateTwitterHandle);
        TextView latestTweetView = (TextView) convertView.findViewById(R.id.candidateLatestTweet);
        ImageView image = (ImageView) convertView.findViewById(R.id.candidateImage);

        Candidate currentCandidate = candidates[index];

        String imageTitle = currentCandidate.imageTitle;

        nameView.setText(currentCandidate.position + " " + currentCandidate.name);
        partyView.setText(currentCandidate.party);
        partyView.setTextColor(currentCandidate.getPartyColor());
        emailView.setText(currentCandidate.email);
        websiteView.setText(currentCandidate.website);
        twitterHandleView.setText(currentCandidate.twitterHandle);
        latestTweetView.setText(currentCandidate.latestTweet);
        image.setImageResource(context.getResources().getIdentifier(imageTitle, "drawable", context.getPackageName()));

        return convertView;
    }
}