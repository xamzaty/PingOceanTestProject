package utils

import android.os.SystemClock
import android.view.View
import androidx.fragment.app.Fragment

class SafeClickListener (
    private val interval: Int = 1000,
    private val onSafeClick: (View) -> Unit
) : View.OnClickListener {

    private var lastClickTime: Long = 0L

    override fun onClick(v: View) {
        if (SystemClock.elapsedRealtime() - lastClickTime < interval) {
            return
        }
        lastClickTime = SystemClock.elapsedRealtime()
        onSafeClick(v)
    }

    fun View.setOnSafeClickListener(
        onSafeClick: (View) -> Unit
    ) {
        setOnClickListener(SafeClickListener { v ->
            onSafeClick(v)
        })
    }

    fun View.setOnSafeClickListener(
        interval: Int,
        onSafeClick: (View) -> Unit
    ) {
        setOnClickListener(SafeClickListener(interval) { v ->
            onSafeClick(v)
        })
    }

    fun Fragment.setOnSafeClickListener(interval: Int, onSafeClick: (View) -> Unit) {

    }
}