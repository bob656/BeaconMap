package kr.co.retailtech.mybeacon.utility;

import android.content.Context;

import kr.co.retailtech.mybeacon.data.CommonRepository;
import kr.co.retailtech.mybeacon.data.database.CommonDao;
import kr.co.retailtech.mybeacon.data.database.CommonDatabase;
import kr.co.retailtech.mybeacon.network.CommonNetworkDataSource;
import kr.co.retailtech.mybeacon.ui.viewmodel.BeaConInfoViewModelFactory;

public class InjectorUtils {
    public static CommonRepository getRepository(Context context) {
        // Get all we need
        CommonDao commonDao = CommonDatabase.getInstance(context).commonDao();
        AppExecutors executors = AppExecutors.getInstance();
        CommonNetworkDataSource networkDataSource = CommonNetworkDataSource.getInstance(executors);

        return CommonRepository.getInstance(commonDao, networkDataSource, executors);
    }

    public static BeaConInfoViewModelFactory getBeaConMainViewModelFactory(Context context){
        CommonRepository repository = getRepository(context);
        return new BeaConInfoViewModelFactory(repository);
    }
}
