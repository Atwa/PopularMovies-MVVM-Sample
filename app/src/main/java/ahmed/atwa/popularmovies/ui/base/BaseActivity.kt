

package ahmed.atwa.popularmovies.ui.base

import ahmed.atwa.popularmovies.R
import ahmed.atwa.popularmovies.utils.AppUtils
import android.annotation.TargetApi
import android.app.ProgressDialog
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.android.material.snackbar.Snackbar
import dagger.android.AndroidInjection
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

/**
 * Created by Ahmed Atwa on 10/19/18.
 */

abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel> : AppCompatActivity(), BaseFragment.Callback {

    var mProgressDialog: ProgressDialog? = null
    private lateinit var mViewDataBinding: T
    private var mViewModel: V? = null

    fun getViewDataBinding(): T = mViewDataBinding


    abstract fun getBindingVariable(): Int
    abstract fun getLayoutId(): Int
    abstract fun getViewModel(): V

    override fun onFragmentAttached() {}
    override fun onFragmentDetached(tag: String) {}

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        performDependencyInjection()
        super.onCreate(savedInstanceState)
        performDataBinding()
    }

    private fun performDependencyInjection() {
        AndroidInjection.inject(this)
    }

    private fun performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        this.mViewModel = if (mViewModel == null) getViewModel() else mViewModel
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel)
        mViewDataBinding.executePendingBindings()
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun hasPermission(permission: String): Boolean {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M || checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun requestPermissionsSafely(permissions: Array<String>, requestCode: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode)
        }
    }

    fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm?.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    fun hideLoading() {
        if (mProgressDialog?.isShowing!!) {
            mProgressDialog?.cancel()
        }
    }

    fun showLoading() {
        hideLoading()
        mProgressDialog = AppUtils.showLoadingDialog(this)
    }

    fun isNetworkConnected(): Boolean {
        return AppUtils.isNetworkConnected(applicationContext)
    }

    fun showMessage(message : String){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }

    fun onError(message: String?) {
        if (message != null) {
            showSnackBar(message)
        } else {
            showSnackBar(getString(R.string.some_error))
        }
    }

    private fun showSnackBar(message: String) {
        val snackbar = Snackbar.make(findViewById(android.R.id.content),
                message, Snackbar.LENGTH_SHORT)
        val sbView = snackbar.view
        val textView = sbView
                .findViewById<View>(R.id.snackbar_text) as TextView
        textView.setTextColor(ContextCompat.getColor(this, R.color.white))
        snackbar.show()
    }



}