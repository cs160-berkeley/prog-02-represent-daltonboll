package cs160.represent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Dalton on 3/6/16.
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

        View rowView = inflater.inflate(R.layout.individual_candidate_list_view, parent, false);
        TextView nameView = (TextView) rowView.findViewById(R.id.candidateName);
        TextView partyView = (TextView) rowView.findViewById(R.id.candidateParty);

        Candidate currentCandidate = candidates[index];
        nameView.setText(currentCandidate.name);
        partyView.setText(currentCandidate.party);

        return rowView;
    }
}