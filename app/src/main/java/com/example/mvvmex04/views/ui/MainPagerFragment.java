package com.example.mvvmex04.views.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmex04.R;
import com.example.mvvmex04.databinding.ActivityCardBinding;
import com.example.mvvmex04.models.model.VentilationTimeModel;
import com.example.mvvmex04.viewmodels.MainViewModel;
import com.example.mvvmex04.views.adapter.RecyclerViewAdapter;

import java.util.ArrayList;

public class MainPagerFragment extends Fragment implements LifecycleOwner {

    private MainViewModel viewModel;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ViewModelProvider.AndroidViewModelFactory viewModelFactory;
    private ActivityCardBinding activityCardBinding;
    private ViewModelStore viewModelStore = new ViewModelStore();
    int contentLayoutId;

    private MainPagerFragment() {
    }

    public MainPagerFragment(int contentLayoutId) {
        super(contentLayoutId);
        this.contentLayoutId = contentLayoutId;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = null;
        if (contentLayoutId == 0) {
            rootView = (ViewGroup) inflater.inflate(R.layout.activity_card, container, false);
        } else if (contentLayoutId == 1) {
            rootView = (ViewGroup) inflater.inflate(R.layout.activity_card, container, false);
            RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.rv_main);
            recyclerView.setBackgroundColor(Color.rgb(255, 0, 0));
        } else if (contentLayoutId == 2) {
            rootView = (ViewGroup) inflater.inflate(R.layout.activity_card, container, false);
            RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.rv_main);
            recyclerView.setBackgroundColor(Color.rgb(0, 255, 0));
        } else {
            rootView = (ViewGroup) inflater.inflate(R.layout.activity_card, container, false);
            RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.rv_main);
            recyclerView.setBackgroundColor(Color.rgb(0, 0, 255));
        }

        if (viewModelFactory == null) {
            viewModelFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication());
        }

        recyclerView = rootView.findViewById(R.id.rv_main);
//        viewModel = ViewModelProviders.of(context).get(MainViewModel.class);
//        viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MainViewModel.class);
        viewModel = new ViewModelProvider(this, viewModelFactory).get(MainViewModel.class);
        activityCardBinding = DataBindingUtil.setContentView(getActivity(), R.layout.activity_card);
        activityCardBinding.setLifecycleOwner(this);
//        cardBinding.setMainViewModel(viewModel);
        viewModel.getMutableLiveData().observe(getViewLifecycleOwner(), ventilationObserver);

        return rootView;
    }

    Observer<ArrayList<VentilationTimeModel>> ventilationObserver = new Observer<ArrayList<VentilationTimeModel>>() {
        @Override
        public void onChanged(ArrayList<VentilationTimeModel> arrayList) {
            recyclerViewAdapter = new RecyclerViewAdapter(getActivity(), arrayList);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            activityCardBinding.setMainViewModel(viewModel);
        }
    };
}