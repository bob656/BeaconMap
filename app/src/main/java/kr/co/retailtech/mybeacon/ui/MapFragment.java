package kr.co.retailtech.mybeacon.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import kr.co.retailtech.mybeacon.ui.viewmodel.BeaConInfoViewModel;
import kr.co.retailtech.mybeacon.ui.viewmodel.BeaConInfoViewModelFactory;
import kr.co.retailtech.mybeacon.utility.BeaconUtil;
import kr.co.retailtech.mybeacon.utility.InjectorUtils;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MapFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class MapFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match

    public BeaConInfoViewModel beaConInfoViewModel;
    private BeaconUtil beaconUtil;

    private Matrix matrix = new Matrix();

    public MapFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    ImageView positionMap1, positionMap2, positionMap3;

    ImageView imageMap;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        BeaConInfoViewModelFactory factory = InjectorUtils.getBeaConMainViewModelFactory(getActivity().getApplicationContext());
        beaConInfoViewModel = ViewModelProviders.of(getActivity(), factory).get(BeaConInfoViewModel.class);
        beaconUtil = new BeaconUtil(getContext());

//        Double position = 20d;

        imageMap = view.findViewById(R.id.imageMap);

        positionMap1 = view.findViewById(R.id.imageView);
        positionMap1.setX(100);
        positionMap1.setY(100);

        positionMap2 = view.findViewById(R.id.imageView2);
        positionMap2.setX(100);
        positionMap2.setY(940);

        positionMap3 = view.findViewById(R.id.imageView3);
        positionMap3.setX(220);
        positionMap3.setY(320);

        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int width = view.getMeasuredWidth();
        int height = view.getMeasuredHeight();




        Log.d("TAG", "width==>"+ width+"  height===> " + height);
        beaconUtil.getPostionMutableLiveData().observe(this, positions -> {
            double x = positions[0];
            double y = positions[1];

            Log.d("TAG",  positions[0] +":"+ positions[1] +":"+x+":"+y);
            imageMap.setX((float)x);
            imageMap.setY((float)y);
            imageMap.findFocus();

        });
        return view;
    }



    @Override
    public void onResume() {
        super.onResume();
        beaconUtil.startBeacon();
    }

    @Override
    public void onPause() {
        beaconUtil.stopBeacon();
        super.onPause();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
