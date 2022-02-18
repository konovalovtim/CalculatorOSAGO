package study.my.calculatorosago.retrofit

import retrofit2.Call
import retrofit2.http.GET

interface RetrofitServices {
    @GET("osago/rationDetail")
    fun getFactorList(): Call<ResultList>
}