package com.example.mvvmex04.models.repository;

import com.example.mvvmex04.models.model.VentilationTimeModel;
import com.example.mvvmex04.models.network.RetrofitRestAPI;
import com.example.mvvmex04.models.network.RetrofitRestAPIService;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class VentilationTimeRepository {
    private RetrofitRestAPI retrofitRestAPIService;
    private CompositeDisposable disposable;
    private ArrayList<VentilationTimeModel> arrayList;

    public VentilationTimeRepository() {
        retrofitRestAPIService = RetrofitRestAPIService.getInstance().getRetrofitRestAPI(null);
    }

    public VentilationTimeRepository(String url) {
        retrofitRestAPIService = RetrofitRestAPIService.getInstance().getRetrofitRestAPI(url);
    }

    public ArrayList<VentilationTimeModel> callVentilationTime() {
        if (disposable == null || disposable.isDisposed()) {
//            disposable.clear();
            disposable = new CompositeDisposable();
        }
        disposable.add(retrofitRestAPIService.ventilationtime()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> onNext(result.getData()),
                        e -> onError(e), () -> onComplete()));
        return arrayList;
    }

    private void onNext(ArrayList<VentilationTimeModel> list) {
        if (list != null) {
            arrayList = list;
        }
    }

    private void onError(Throwable t) {
        t.printStackTrace();
    }

    private void onComplete() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}