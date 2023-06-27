package com.rasgo.combidriver.providers

import com.rasgo.combidriver.api.IFCMApi
import com.rasgo.combidriver.api.RetrofitClient
import com.rasgo.combidriver.models.FCMBody
import com.rasgo.combidriver.models.FCMResponse
import retrofit2.Call

class NotificationProvider {

    private val URL = "https://fcm.googleapis.com"

    fun sendNotification(body: FCMBody): Call<FCMResponse> {
        return RetrofitClient.getClient(URL).create(IFCMApi::class.java).send(body)
    }

}