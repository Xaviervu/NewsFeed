package ru.vegax.xavier.newsfeed.main

import android.content.Context
import android.widget.Toast

fun Context.toast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

