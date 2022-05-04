package com.rcorchero.randomusers.ui.utils

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import com.rcorchero.randomusers.R

fun Context.makePhoneCall(phone: String) =
    try {
        val callIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phone"))
        startActivity(callIntent)
    } catch (e: ActivityNotFoundException) {
        Toast.makeText(this, getString(R.string.intent_error_dialer), Toast.LENGTH_SHORT).show()
    }

fun Context.sendEmail(email: String) =
    try {
        val emailIntent = Intent(Intent.ACTION_SEND)
        emailIntent.type = "*/*"
        emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
        startActivity(emailIntent)
    } catch (e: ActivityNotFoundException) {
        Toast.makeText(this, getString(R.string.intent_error_email), Toast.LENGTH_SHORT).show()
    }

fun Context.openLocation(latitude: Double, longitude: Double) =
    try {
        val gmmIntentUri = Uri.parse("geo:$latitude,$longitude")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        startActivity(mapIntent)
    } catch (e: ActivityNotFoundException) {
        Toast.makeText(this, getString(R.string.intent_error_geo), Toast.LENGTH_SHORT).show()
    }