

package ahmed.atwa.popularmovies.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import dagger.android.support.AndroidSupportInjection

/**
 * Created by Ahmed Atwa on 10/19/18.
 */

abstract class BaseFragment<T : ViewDataBinding, V : BaseViewModel> : androidx.fragment.app.Fragment() {

    var mActivity: BaseActivity<T, V>? = null
    lateinit var mRootView: View
    lateinit var mViewDataBinding: T
    lateinit var mViewModel: V

    abstract fun getBindingVariable(): Int
    abstract fun getLayoutId(): Int
    abstract fun getViewModel(): V
    abstract fun getLifeCycleOwner(): LifecycleOwner


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is BaseActivity<*, *>) {
            val activity = context
            mActivity = activity as BaseActivity<T, V>?
            mActivity?.onFragmentAttached()
        }
    }

    override fun onDetach() {
        mActivity = null
        super.onDetach()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        performDependencyInjection()
        super.onCreate(savedInstanceState)
        mViewModel = getViewModel()
        setHasOptionsMenu(false)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        mRootView = mViewDataBinding.root
        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewDataBinding.setVariable(getBindingVariable(), getViewModel())
        mViewDataBinding.lifecycleOwner = getLifeCycleOwner()
        mViewDataBinding.executePendingBindings()

    }

    private fun performDependencyInjection() {
        AndroidSupportInjection.inject(this)
    }

    fun getBaseActivity(): BaseActivity<T, V>? = mActivity

    fun getViewDataBinding(): T = mViewDataBinding

    fun showLoading() = mActivity?.showLoading()

    fun hideLoading() = mActivity?.hideLoading()

    fun hideKeyboard() = mActivity?.hideKeyboard()

    fun isNetworkConnected(): Boolean = mActivity != null && mActivity!!.isNetworkConnected()

    interface Callback {
        fun onFragmentAttached()
        fun onFragmentDetached(tag: String)
    }

    fun showMessage(message : String){
        mActivity?.showMessage(message)
    }

    fun onError(message: String?) {
        mActivity?.onError(message)
    }




}