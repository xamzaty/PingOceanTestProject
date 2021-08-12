package utils

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment

object VibrationUtil {

    @RequiresApi(Build.VERSION_CODES.Q)
    fun Context.vibrate(duration: Long) {
        val vib = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vib.vibrate(VibrationEffect.createOneShot(duration, VibrationEffect.EFFECT_TICK))
        } else {
            @Suppress("DEPRECATION")
            vib.vibrate(duration)
        }
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    fun Fragment.vibrate(duration: Long) {
        context?.vibrate(duration)
    }

    fun Context.strongVibration(duration: Long) {
        val vib = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vib.vibrate(VibrationEffect.createOneShot(duration, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            @Suppress("DEPRECATION")
            vib.vibrate(duration)
        }
    }
}