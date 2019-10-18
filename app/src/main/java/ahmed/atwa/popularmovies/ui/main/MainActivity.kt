/*
 * *
 *  Created by Ahmed Atwa on  19/10/2018.
 * /
 */

/*
 * *
 *  Created by Ahmed Atwa on  19/10/2018.
 * /
 */

/*
 * *
 *  Created by Ahmed Atwa on  19/10/2018.
 * /
 */

/*
 * *
 *  Created by Ahmed Atwa on  19/10/2018.
 * /
 */

package ahmed.atwa.popularmovies.ui.main

import ahmed.atwa.popularmovies.BR
import ahmed.atwa.popularmovies.R
import ahmed.atwa.popularmovies.data.model.Movie
import ahmed.atwa.popularmovies.data.model.Trailer
import ahmed.atwa.popularmovies.databinding.ActivityMainBinding
import ahmed.atwa.popularmovies.ui.base.BaseActivity
import ahmed.atwa.popularmovies.ui.detail.DetailFragment
import ahmed.atwa.popularmovies.ui.movies.MoviesFragment
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * Created by Ahmed Atwa on 10/19/18.
 */

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), HasSupportFragmentInjector,
        MoviesFragment.MainFragmentListener, DetailFragment.DetailFragmentListener {


    @Inject
    internal lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
    /* @Inject
     internal lateinit var mViewModelFactory: ViewModelProvider.Factory*/
    @Inject
    lateinit var mMainViewModel: MainViewModel
    private lateinit var mActivityMainBinding: ActivityMainBinding

    private lateinit var mContext: Context

    override fun getBindingVariable(): Int = BR.viewModel
    override fun getLayoutId(): Int = R.layout.activity_main
    override fun getViewModel(): MainViewModel = mMainViewModel


    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentDispatchingAndroidInjector


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        mActivityMainBinding = getViewDataBinding()
        setUp()
    }

    private fun setUp() {
        val mMainFragment = MoviesFragment()
        mMainFragment.mListener = this
        replaceFragment(mMainFragment)
    }


    private fun replaceFragment(fragment: Fragment) {
        if (fragment is DetailFragment)
            supportFragmentManager.beginTransaction().replace(R.id.container, fragment).addToBackStack("test").commit()
        else {
            supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
        }
    }

    override fun onMovieSelected(movie: Movie) {
        val mDetailFragment = DetailFragment(movie)
        mDetailFragment.mListener = this
        replaceFragment(mDetailFragment)
    }

    override fun onTrailerSelected(trailer: Trailer) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:${trailer.key}"))
            startActivity(intent)
        } catch (ex: ActivityNotFoundException) {
            val intent = Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://www.youtube.com/watch?v=${trailer.key}"))
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        setUp()
    }


}

