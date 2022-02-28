package study.my.calculatorosago.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitServices {
    @GET("rationDetail")
    fun getFactorList(): Call<ResultList>

    @POST("startCalculation")
    fun getOffersList(): Call<ResultListOffers>
}