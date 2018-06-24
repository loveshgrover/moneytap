package com.colony.loveshgrover.moneytap.restinterface;

import com.colony.loveshgrover.moneytap.model.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {


    @GET("/w/api.php/")
    Call<Result> getResult(@Query("action") String action, @Query("format") String format, @Query("prop") String prop, @Query("generator") String generator, @Query("formatversion") String formatversion
            , @Query("piprop") String piprop, @Query("pithumbsize") String pithumbsize, @Query("pilimit") String pilimit, @Query("wbptterms") String wbptterms, @Query("gpssearch") String gpssearch);


}
