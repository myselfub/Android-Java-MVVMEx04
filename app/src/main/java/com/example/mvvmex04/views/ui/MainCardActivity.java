package com.example.mvvmex04.views.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmex04.R;
import com.example.mvvmex04.databinding.CardBinding;
import com.example.mvvmex04.models.model.User;
import com.example.mvvmex04.viewmodels.MainViewModel;
import com.example.mvvmex04.views.adapter.RecyclerViewAdapter;

import java.util.ArrayList;

public class MainCardActivity extends AppCompatActivity implements LifecycleOwner {

    private MainCardActivity context;
    private MainViewModel viewModel;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private CardBinding cardBinding;
    private ViewModelProvider.AndroidViewModelFactory viewModelFactory;
    private ViewModelStore viewModelStore = new ViewModelStore();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (viewModelFactory == null) {
            viewModelFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication());
        }

        setContentView(R.layout.activity_card);
        context = this;
        recyclerView = findViewById(R.id.rv_main);
//        viewModel = ViewModelProviders.of(context).get(MainViewModel.class);
//        viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MainViewModel.class);
        viewModel = new ViewModelProvider(this, viewModelFactory).get(MainViewModel.class);
//        viewModel.getUserMutableLiveData().observe(context, userListUpdateObserver);
    }


    Observer<ArrayList<User>> userListUpdateObserver = new Observer<ArrayList<User>>() {
        @Override
        public void onChanged(ArrayList<User> userArrayList) {
//            recyclerViewAdapter = new RecyclerViewAdapter(context, userArrayList);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(recyclerViewAdapter);
        }
    };
}