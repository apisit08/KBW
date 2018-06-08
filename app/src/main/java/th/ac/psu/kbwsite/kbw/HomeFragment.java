package th.ac.psu.kbwsite.kbw;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener{

    private CardView IT,Exam,Doc,Eval,Train,Rent;


    public HomeFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        //defind cardview
        IT = (CardView) view.findViewById(R.id.it_card);
        Exam = (CardView) view.findViewById(R.id.exam_card);
        Doc = (CardView) view.findViewById(R.id.doc_card);
        Eval = (CardView) view.findViewById(R.id.eval_card);
        Train = (CardView) view.findViewById(R.id.train_card);
        Rent = (CardView) view.findViewById(R.id.rent_card);
        // add click listener
        IT.setOnClickListener(this);
        Exam.setOnClickListener(this);
        Doc.setOnClickListener(this);
        Eval.setOnClickListener(this);
        Train.setOnClickListener(this);
        Rent.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View v) {

        Intent i ;
        switch (v.getId()){
            case R.id.it_card : i = new Intent(HomeFragment.this.getActivity(),LoginITActivity.class);startActivity(i); break;
            case R.id.exam_card : i = new Intent(HomeFragment.this.getActivity(),Coming.class);startActivity(i); break;
            case R.id.doc_card : i = new Intent(HomeFragment.this.getActivity(),Coming.class);startActivity(i); break;
            case R.id.rent_card : i = new Intent(HomeFragment.this.getActivity(),Coming.class);startActivity(i); break;
            case R.id.train_card : i = new Intent(HomeFragment.this.getActivity(),Coming.class);startActivity(i); break;

        }
    }
}
