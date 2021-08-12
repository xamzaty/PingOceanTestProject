package utils

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

object FragmentExtensions {

    fun Fragment.createSnackbar(
        text: String,
        @BaseTransientBottomBar.Duration length: Int = Snackbar.LENGTH_SHORT
    ) {
        view?.run { Snackbar.make(this, text, length).show() }
    }

    fun Context.createToast(message: String, isLongToast: Boolean) {
        Toast.makeText(this, message, if (isLongToast) Toast.LENGTH_LONG else Toast.LENGTH_SHORT)
            .show()
    }

    fun Fragment.createToast(message: String, isLongToast: Boolean) {
        context?.createToast(message, isLongToast)
    }

    fun Fragment.nextFragment(newFragment: Int) = findNavController().navigate(newFragment)

    fun Fragment.vibratePhone() {
        val vibrator = context?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= 26) {
            vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            vibrator.vibrate(200)
        }
    }

    fun Fragment.statusBarColor(@ColorRes color: Int, isLightTheme: Boolean = true) {
        activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        activity?.window?.statusBarColor = ContextCompat.getColor(requireContext(), color)
    }
}