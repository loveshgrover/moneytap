package com.colony.loveshgrover.moneytap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText searchEditText;
    private Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        searchEditText = findViewById(R.id.edit_text_search);
        searchButton = findViewById(R.id.button_search);

        searchButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_search:
                //start activity here
                Intent searchResult = new Intent(this, MainActivity.class);
                searchResult.putExtra("key", searchEditText.getText().toString());
                startActivity(searchResult);
                break;
        }
    }
}
