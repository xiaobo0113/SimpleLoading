package top.gangshanghua.simpleloading

import android.os.Bundle
import androidx.core.view.postDelayed
import top.gangshanghua.lib_loading.LoadingActivity

class MainActivity : LoadingActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        handleLoading(true)
        window.decorView.postDelayed(3000L) {
            handleLoading(false)
        }
    }

}