package com.rasgo.combiuser.providers

import com.rasgo.combiuser.api.IFCMApi
import com.rasgo.combiuser.api.RetrofitClient
import com.rasgo.combiuser.models.FCMBody
import com.rasgo.combiuser.models.FCMResponse
import retrofit2.Call

class NotificationProvider {

    private val URL = "https://fcm.googleapis.com"

    fun sendNotification(body: FCMBody): Call<FCMResponse> {
        return RetrofitClient.getClient(URL).create(IFCMApi::class.java).send(body)
    }

}