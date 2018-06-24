package com.colony.loveshgrover.moneytap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.colony.loveshgrover.moneytap.model.Page;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class DetailsActivity extends AppCompatActivity {


    Page page;
    ImageView detailImageView;
    TextView detailTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        if (getIntent().hasExtra("page") && (page = (Page) getIntent().getExtras().get("page")) != null) {
        } else {
            Toast.makeText(this, "No Page", Toast.LENGTH_SHORT).show();
            finish();
        }

        detailImageView = findViewById(R.id.image_view_details);
        detailTextView = findViewById(R.id.text_view_details);


        if (page.getThumbnail() != null && page.getThumbnail().getSource() != null) {
            Glide.with(this).load(page.getThumbnail().getSource())
                    .apply(bitmapTransform(new CropCircleTransformation()))
                    .into(detailImageView);

        }
        String pageDetails = " ";
        if (page.getTerms() != null) {

            for (String data : page.getTerms().getDescription()) {
                pageDetails += data + ",";
            }
        }
        pageDetails = pageDetails.substring(0, pageDetails.length() - 1);
        pageDetails.trim();
        if (pageDetails.isEmpty()) {
            pageDetails = "No Data Found";
        }
        detailTextView.setText(pageDetails);


    }
}
