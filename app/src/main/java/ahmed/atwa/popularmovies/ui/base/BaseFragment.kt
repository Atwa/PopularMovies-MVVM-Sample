package ahmed.atwa.popularmovies.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.ContentLoadingProgressBar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_movies.*

/**
 * Created by Ahmed Atwa on 10/19/18.
 */

abstract class BaseFragment<V : BaseViewModel> : androidx.fragment.app.Fragment() {

    var mActivity: BaseActivity<V>? = null
    lateinit var mViewModel: V

    abstract fun getLayoutId(): Int
    abstract fun getViewModel(): V
    abstract fun getLifeCycleOwner(): LifecycleOwner
    var loading: ContentLoadingProgressBar?
        get() {
            return loading
        }
        set(value) {
            loading = value
        }




    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is BaseActivity<*>) {
            val activity = context
            mActivity = activity as BaseActivity<V>?
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
        var view = inflater.inflate(getLayoutId(), container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewState()

    }


    protected open fun observeViewState() {
        getViewModel().uiState.observe(this, Observer {
            when (it) {
                is UIState.messageText -> {
                    showMessage(it.text);if (loading != null) {
                        loading!!.visibility = View.VISIBLE
                    }
                }
                is UIState.loading -> if (loading != null) {
                    loading!!.visibility = View.VISIBLE
                }
                is UIState.errorText -> {
                    onError(it.text);if (loading != null) {
                        loading!!.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    private fun performDependencyInjection() {
        AndroidSupportInjection.inject(this)
    }

    fun getBaseActivity(): BaseActivity<V>? = mActivity

    fun showLoading() = mActivity?.showLoading()

    fun hideLoading() = mActivity?.hideLoading()

    fun hideKeyboard() = mActivity?.hideKeyboard()

    fun isNetworkConnected(): Boolean = mActivity != null && mActivity!!.isNetworkConnected()

    interface Callback {
        fun onFragmentAttached()
        fun onFragmentDetached(tag: String)
    }

    fun showMessage(message: String) {
        mActivity?.showMessage(message)
    }

    fun onError(message: String?) {
        mActivity?.onError(message)
    }


}