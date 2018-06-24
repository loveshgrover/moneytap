package com.colony.loveshgrover.moneytap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.colony.loveshgrover.moneytap.adapter.SearchResultRecyclerAdapter;
import com.colony.loveshgrover.moneytap.events.GetResult;
import com.colony.loveshgrover.moneytap.model.Result;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.awt.font.TextAttribute;
import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

public class MainActivity extends AppCompatActivity {

    String searchOn;

    private RecyclerView searchResultRecyclerView;
    SearchResultRecyclerAdapter searchResultRecyclerAdapter;
    private Result result = new Result();
    private ProgressBar progressBar;
    private TextView noDataFound;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Result result) {
        this.result.getQuery().getPages().addAll(result.getQuery().getPages());
        searchResultRecyclerAdapter.notifyDataSetChanged();
        searchResultRecyclerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        if (result == null || result.getQuery().getPages() == null || result.getQuery().getPages().size() == 0) {
            noDataFound.setVisibility(View.VISIBLE);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_main);
        if (getIntent().hasExtra("key") && (searchOn = getIntent().getStringExtra("key")).length() > 0) {
        } else {
            Toast.makeText(this, "Please search", Toast.LENGTH_SHORT).show();
            finish();
        }
        new GetResult(searchOn);

        noDataFound = findViewById(R.id.nodatafound);
        progressBar = findViewById(R.id.progress);
        searchResultRecyclerView = findViewById(R.id.recyclerViewSearchResult);


        searchResultRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        searchResultRecyclerView.setLayoutManager(layoutManager);

        searchResultRecyclerAdapter = new SearchResultRecyclerAdapter(result.getQuery().getPages(), getApplicationContext());
        searchResultRecyclerView.setAdapter(searchResultRecyclerAdapter);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
