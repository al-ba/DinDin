package ir.lb7.dindin

import androidx.multidex.MultiDexApplication
import com.airbnb.mvrx.Mavericks
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FoodApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        Mavericks.initialize(this)
    }
}