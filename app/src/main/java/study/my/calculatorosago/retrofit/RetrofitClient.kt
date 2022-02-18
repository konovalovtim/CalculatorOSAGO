package study.my.calculatorosago.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://mock.sravni-team.ru/mobile/internship/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    var service = retrofit.create(RetrofitServices::class.java)




}