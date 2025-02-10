package com.ruzibekov.data

import android.media.MediaPlayer

fun MediaPlayer.getPositionForSliders() = currentPosition.toFloat() / duration.toFloat()