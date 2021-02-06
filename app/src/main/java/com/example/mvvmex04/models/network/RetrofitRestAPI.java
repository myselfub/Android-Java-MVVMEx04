package com.example.mvvmex04.models.network;

import com.example.mvvmex04.models.model.JsonResultModel;
import com.example.mvvmex04.models.model.VentilationTimeModel;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface RetrofitRestAPI {
    @GET("/air/ventilation/time")
    Observable<JsonResultModel<VentilationTimeModel>> ventilationtime();
}
