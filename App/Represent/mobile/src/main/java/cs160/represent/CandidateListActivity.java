package cs160.represent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class CandidateListActivity extends AppCompatActivity {

    private static final String TAG = "CandidateListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate_list);

        Intent intent = getIntent();
        int zipCode = intent.getIntExtra("zipCode", 0);
        Log.d(TAG, "Received zip code: " + zipCode);
    }
}
