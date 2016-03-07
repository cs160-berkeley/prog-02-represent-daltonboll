package cs160.represent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

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

        // Grab the Candidate which we are viewing
        Intent intent = getIntent();
        Candidate candidate = (Candidate) intent.getExtras().getSerializable("candidate");
        Log.d(TAG, "Received candidate: " + candidate.name);
    }
}
