package com.example.mvvmex04.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmex04.models.model.VentilationTimeModel;
import com.example.mvvmex04.models.repository.VentilationTimeRepository;

import java.util.ArrayList;

public class MainViewModel extends ViewModel {

    private MutableLiveData<ArrayList<VentilationTimeModel>> mutableLiveData;
    private VentilationTimeRepository ventilationTimeRepository;

    public MainViewModel() {
        if (mutableLiveData == null) {
            mutableLiveData = new MutableLiveData<>();
        }
        if (ventilationTimeRepository == null) {
            ventilationTimeRepository = new VentilationTimeRepository();
        }
        ArrayList<VentilationTimeModel> ventilationTimeModels = ventilationTimeRepository.callVentilationTime();
        mutableLiveData.setValue(ventilationTimeModels);
    }

    public MutableLiveData<ArrayList<VentilationTimeModel>> getMutableLiveData() {
        return mutableLiveData;
    }
}