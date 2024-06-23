package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.view.SourcesAdapter;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.example.myapplication.model.Sources;
import com.example.myapplication.service.impl.ApiServiceImpl;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;


public class MainActivity extends AppCompatActivity implements OnSourceClickListener {

    private ActivityMainBinding binding;
    private RecyclerView recyclerView;
    private SourcesAdapter sourcesAdapter;
    private ApiServiceImpl apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        recyclerView = binding.recyclerViewSources;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        apiService = new ApiServiceImpl();
        sourcesAdapter = new SourcesAdapter(null, this); // Initialize adapter with null data and set click listener
        recyclerView.setAdapter(sourcesAdapter);
        fetchSources(sourcesAdapter);
    }

    public void fetchSources(SourcesAdapter sourcesAdapter) {
        Call<List<Sources>> call = apiService.getSources();
        call.enqueue(new Callback<List<Sources>>() {
            @Override
            public void onResponse(Call<List<Sources>> call, Response<List<Sources>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Sources> sourcesList = response.body();
                    sourcesAdapter.setSourcesList(sourcesList); // Update adapter data
                } else {
                }
            }

            @Override
            public void onFailure(Call<List<Sources>> call, Throwable t) {
            }
        });
    }

    @Override
    public void onSourceClick(Sources source) {
        if (source.getName().equals("Розшук")) {
            Call<Void> call = apiService.startSync(source);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(@NotNull Call<Void> call, @NotNull Response<Void> response) {
                    fetchSources(sourcesAdapter);
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    fetchSources(sourcesAdapter);
                }
            });
        }
    }
}
