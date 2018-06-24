package com.colony.loveshgrover.moneytap.events;

import com.colony.loveshgrover.moneytap.model.Result;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.colony.loveshgrover.moneytap.services.BaseService.getApi;

public class GetResult {

    public GetResult(String search) {
        Call<Result> location = getApi().getResult("query", "json", "pageimages|Cpageterms|pageterms",
                "prefixsearch", "2", "thumbnail",
                "50", "10", "description", search);
        location.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.code() == 200) {
                    EventBus.getDefault().post(((Result) response.body()));
                } else {
                    EventBus.getDefault().post(new Result());
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                EventBus.getDefault().post(null);
            }
        });
    }
}
