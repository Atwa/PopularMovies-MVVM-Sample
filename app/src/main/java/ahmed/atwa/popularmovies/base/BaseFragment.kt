package ahmed.atwa.popularmovies.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import dagger.android.support.AndroidSupportInjection

/**
 * Created by Ahmed Atwa on 10/19/18.
 */

abstract class BaseFragment< V : BaseViewModel> : androidx.fragment.app.Fragment() {

    var mActivity: BaseActivity<V>? = null
    abstract val viewModel: V

    abstract fun getLayoutId(): Int
    abstract fun getLifeCycleOwner(): LifecycleOwner

    /**
     *  Called in case of some data emitted from the liveData in viewModel
     */
    open fun renderViewState(data: Any) {}

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity<*>) {
            mActivity = context as BaseActivity<V>?
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
        setHasOptionsMenu(false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureObserver()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(getLayoutId(), container, false)
    }

    private fun configureObserver() {
        viewModel.uiState.observe(viewLifecycleOwner, Observer { viewState ->
            hideLoading()
            viewState?.let { renderViewState(it) }
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