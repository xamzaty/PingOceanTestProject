package utils

import android.app.AlertDialog
import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.pingoceantestproject.R

object UiUtils {
    fun showViews(vararg views: View) {
        views.forEach { view -> view.visibility = View.VISIBLE }
    }

    fun hideViews(vararg views: View, visibility: Int = View.GONE) {
        views.forEach { view -> view.visibility = visibility }
    }

    fun getDimen(context: Context, resId: Int): Int {
        return context.resources.getDimension(resId).toInt()
    }

    fun getColor(context: Context, resId: Int): Int {
        return ContextCompat.getColor(context, resId)
    }

    fun Fragment.toTheNextFragment(fragment: Int) {
        findNavController().navigate(fragment)
    }

    fun Fragment.createAlertDialog(context: Context, message: Int, title: Int) {
        AlertDialog.Builder(context, R.style.Base_ThemeOverlay_AppCompat_Dialog)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK") {_, _ ->
                Coroutines.main {
                    findNavController().popBackStack()
                }
            }
            .setNegativeButton("Cancel", null).create().show()
    }
}