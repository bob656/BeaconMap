package kr.co.retailtech.mybeacon.ui.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import kr.co.retailtech.mybeacon.data.CommonRepository;

public class BeaConInfoViewModelFactory extends ViewModelProvider.NewInstanceFactory{
    private final CommonRepository beaconRepository;

    public BeaConInfoViewModelFactory(CommonRepository beaconRepository) {
        this.beaconRepository = beaconRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //noinspection unchecked
        return (T) new BeaConInfoViewModel(beaconRepository);
    }
}
