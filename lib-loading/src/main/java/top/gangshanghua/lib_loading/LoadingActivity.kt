package top.gangshanghua.lib_loading

import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

abstract class LoadingActivity : AppCompatActivity(), Loading {
    override var mLoading = 0
    override val mHandler = Handler(Looper.getMainLooper())
    override var mHidePending = false
}