package top.gangshanghua.lib_loading

import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment

interface Loading {

    companion object {
        // we must use a tag to find the specified fragment.
        // you can not hold an instance of DialogFragment to call dialog.show()/dialog.hide().
        // for if you rotate the screen, then activity will be re-created, then you will
        // hold another new instance of DialogFragment, at this time you call hide() without show()
        // called before, exception will be thrown.
        // eg: Activity#onCreate -> dialog=DialogFragment() -> dialog.show() ->
        //     Activity#onCreate -> dialog=DialogFragment() -> dialog.hide() -> crash
        private const val TAG_LOADING_DIALOG_FRAGMENT = "TAG_LOADING_DIALOG_FRAGMENT"
    }

    var mLoading: Int
    var mHidePending: Boolean
    val mHandler: Handler

    fun handleLoading(show: Boolean) {
        if (show) {
            mHandler.post { showLoading() }
        } else {
            mHandler.post { hideLoading() }
        }
    }

    fun onDialogShown(dialog: DialogFragment) {
        if (mHidePending) {
            mHidePending = false
            dialog.dismiss()
        }
    }

    private fun showLoading() {
        if (mLoading++ > 0) {
            return
        }

        if (this is AppCompatActivity) {
            val fragment = supportFragmentManager.findFragmentByTag(TAG_LOADING_DIALOG_FRAGMENT)
            if (fragment == null) {
                SimpleLoadingDialog.newInstance()
                    .show(supportFragmentManager, TAG_LOADING_DIALOG_FRAGMENT)
            }
        }
    }

    private fun hideLoading() {
        if (mLoading-- > 1) {
            return
        }

        if (this is AppCompatActivity) {
            val dialog = supportFragmentManager.findFragmentByTag(TAG_LOADING_DIALOG_FRAGMENT)
            if (null != dialog && dialog.isResumed) {
                (dialog as DialogFragment).dismiss()
            } else {
                mHidePending = true
            }
        }
    }

}