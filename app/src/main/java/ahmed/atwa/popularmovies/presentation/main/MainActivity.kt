package ahmed.atwa.popularmovies.presentation.main

import ahmed.atwa.popularmovies.R
import ahmed.atwa.popularmovies.domain.MovieEntity
import ahmed.atwa.popularmovies.data.remote.TrailerRemote
import ahmed.atwa.popularmovies.presentation.base.BaseActivity
import ahmed.atwa.popularmovies.presentation.detail.DetailFragment
import ahmed.atwa.popularmovies.presentation.movies.MoviesFragment
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

class MainActivity : BaseActivity< MainViewModel>(), HasSupportFragmentInjector,
        MoviesFragment.MainFragmentListener, DetailFragment.DetailFragmentListener {


    @Inject
    internal lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var mMainViewModel: MainViewModel

    private lateinit var mContext: Context

    override fun getLayoutId(): Int = R.layout.activity_main
    override fun getViewModel(): MainViewModel = mMainViewModel


    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentDispatchingAndroidInjector


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        setUp()
    }

    private fun setUp() {
        val mMainFragment = MoviesFragment()
        mMainFragment.mListener = this
        replaceFragment(mMainFragment)
    }


    private fun replaceFragment(fragment: Fragment) {
        if (fragment is DetailFragment)
            supportFragmentManager.beginTransaction().replace(R.id.container, fragment).addToBackStack("details").commit()
        else {
            supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
        }
    }

    override fun onMovieSelected(movie: MovieEntity) {
        val mDetailFragment = DetailFragment(movie)
        mDetailFragment.mListener = this
        replaceFragment(mDetailFragment)
    }

    override fun onTrailerSelected(trailerRemote: TrailerRemote) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:${trailerRemote.key}"))
            startActivity(intent)
        } catch (ex: ActivityNotFoundException) {
            val intent = Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://www.youtube.com/watch?v=${trailerRemote.key}"))
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        setUp()
    }


}

