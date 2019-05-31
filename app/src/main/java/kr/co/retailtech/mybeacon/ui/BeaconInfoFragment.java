package kr.co.retailtech.mybeacon.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import kr.co.retailtech.mybeacon.network.model.ServiceRequest;
import kr.co.retailtech.mybeacon.ui.viewmodel.BeaConInfoViewModel;
import kr.co.retailtech.mybeacon.ui.viewmodel.BeaConInfoViewModelFactory;
import kr.co.retailtech.mybeacon.utility.InjectorUtils;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BeaconInfoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BeaconInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BeaconInfoFragment extends Fragment implements View.OnClickListener{

    private static final String LOG_TAG = BeaconInfoFragment.class.getSimpleName();

    // TODO: Rename and change types of parameters
    // 첫번째 Beacon Info
    TextView txtBeaconInfo1;
    TextView txtFirstX, txtFirstY;

    // 두번째 Beacon Info
    TextView txtBeaconInfo2;
    TextView txtSecondX, txtSecondY;

    // 세번째 Beacon Info
    TextView txtBeaconInfo3;
    TextView txtThirdX, txtThirdY;

    Button btOpen, btClose, btDataInfo;

    private BeaConInfoViewModel beaConInfoViewModel;

    public BeaconInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BeaconInfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BeaconInfoFragment newInstance(String param1, String param2) {
        BeaconInfoFragment fragment = new BeaconInfoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_beacon_info, container, false);
        txtBeaconInfo1 = view.findViewById(R.id.txtBeaconInfo1);
        txtFirstX = view.findViewById(R.id.txtFirstX);
        txtFirstY = view.findViewById(R.id.txtFirstY);

        txtBeaconInfo2 = view.findViewById(R.id.txtBeaconInfo2);
        txtSecondX = view.findViewById(R.id.txtSecondX);
        txtSecondY = view.findViewById(R.id.txtSecondY);

        txtBeaconInfo3 = view.findViewById(R.id.txtBeaconInfo3);
        txtThirdX = view.findViewById(R.id.txtThirdX);
        txtThirdY = view.findViewById(R.id.txtThirdY);

        btOpen = view.findViewById(R.id.btOpen);
        btOpen.setOnClickListener(this);
        btClose = view.findViewById(R.id.btClose);
        btClose.setOnClickListener(this);
        btDataInfo = view.findViewById(R.id.btDataInfo);
        btDataInfo.setOnClickListener(this);
        BeaConInfoViewModelFactory factory = InjectorUtils.getBeaConMainViewModelFactory(getActivity().getApplicationContext());
        beaConInfoViewModel = ViewModelProviders.of(getActivity(), factory).get(BeaConInfoViewModel.class);


        return view;
    }



    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btOpen:
         //       beaconUtil.startBeacon();
                break;
            case R.id.btClose:
           //     beaconUtil.stopBeacon();
                break;
            case R.id.btDataInfo:
                postRequest();
                break;
        }
    }
    private void postRequest() {
        ServiceRequest serviceRequest = new ServiceRequest();
        beaConInfoViewModel.beaConPostRequest(serviceRequest);
        beaConInfoViewModel.linePostRequest(serviceRequest);
        beaConInfoViewModel.zonePostRequest(serviceRequest);
        beaConInfoViewModel.mapPostRequest(serviceRequest);

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
