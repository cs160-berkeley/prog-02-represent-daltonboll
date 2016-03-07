package cs160.represent;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class CandidateInfoActivity extends AppCompatActivity {

    private static final String TAG = "CandidateInfoActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_candidate_info);
        setTitle("");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(Color.parseColor("#51b7e3"));

        // Grab the Candidate which we are viewing
        Intent intent = getIntent();
        Candidate candidate = (Candidate) intent.getExtras().getSerializable("candidate");
        Log.d(TAG, "Received candidate: " + candidate.name);

        TextView nameView = (TextView) findViewById(R.id.candidateName);
        TextView partyView = (TextView) findViewById(R.id.candidatePartyInfo);
        TextView emailView = (TextView) findViewById(R.id.candidateEmail);
        TextView websiteView = (TextView) findViewById(R.id.candidateWebsite);
        TextView twitterHandleView = (TextView) findViewById(R.id.candidateTwitterHandle);
        TextView latestTweetView = (TextView) findViewById(R.id.candidateLatestTweet);
        ImageView image = (ImageView) findViewById(R.id.candidateImage);

        // Set the proper candidate image
        String imageTitle = candidate.imageTitle;
        image.setImageResource(this.getResources().getIdentifier(imageTitle, "drawable", this.getPackageName()));

        // Set the candidate's personal details
        nameView.setText(candidate.name);
        partyView.setText(candidate.position + " | " + candidate.party);
        emailView.setText(candidate.email);
        websiteView.setText(candidate.website);

        // Set the candidate's Twitter info
        twitterHandleView.setText(Html.fromHtml("<a href=\"http://twitter.com/" + candidate.twitterHandle + "\">" + candidate.twitterHandle + "</a>"));
        twitterHandleView.setMovementMethod(LinkMovementMethod.getInstance());
        latestTweetView.setText("-- \"" + candidate.latestTweet + "\"");
    }
}
