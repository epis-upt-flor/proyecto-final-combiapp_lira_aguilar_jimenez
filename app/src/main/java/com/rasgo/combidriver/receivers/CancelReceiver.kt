package com.rasgo.combidriver.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.rasgo.combidriver.activities.MapActivity
import com.rasgo.combidriver.activities.MapTripActivity
import com.rasgo.combidriver.providers.BookingProvider

class CancelReceiver: BroadcastReceiver() {

    val bookingProvider = BookingProvider()

    override fun onReceive(context: Context, intent: Intent) {

        val idBooking = intent.extras?.getString("idBooking")
        cancelBooking(idBooking!!)
    }

    private fun cancelBooking(idBooking: String) {
        bookingProvider.updateStatus(idBooking, "cancel").addOnCompleteListener {

            if (it.isSuccessful) {
                Log.d("RECEIVER", "EL VIAJE FUE CANCELADO")
            }
            else {
                Log.d("RECEIVER", "NO SE PUDO ACTUALIZAR EL ESTADO DEL VIAJE")
            }
        }
    }

}