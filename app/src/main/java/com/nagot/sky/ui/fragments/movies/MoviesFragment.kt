package com.nagot.sky.ui.fragments.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.nagot.sky.R
import com.nagot.sky.base.BaseFragment
import com.nagot.sky.base.extensions.gone
import com.nagot.sky.base.extensions.setupSnackbar
import com.nagot.sky.base.extensions.visible
import com.nagot.sky.factory.ViewModelFactory
import com.nagot.sky.ui.widgets.GridSpacingItemDecoration
import kotlinx.android.synthetic.main.fragment_movies.*
import javax.inject.Inject


class MoviesFragment : BaseFragment() {

    @Inject
    lateinit var mViewModelFactory: ViewModelFactory

    companion object {

        const val MOVIES_FRAGMENT_TAG = "MoviesFragment"
    }

    private val mViewModel by viewModels<MoviesViewModel> {
        mViewModelFactory
    }

    private val mAdapter: MoviesAdapter by lazy {
        MoviesAdapter()
    }

    override fun layoutRes(): Int {
        return R.layout.fragment_movies
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(false)

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        refresh_view.setOnRefreshListener {
            mViewModel.refresh()
        }

        setupRecyclerView()

        setupViews()

        setupSnackbar()
    }

    override fun onResume() {
        super.onResume()

        setupSearchView()
    }

    private fun setupRecyclerView() {

        movies_list.apply {
            adapter = mAdapter
            layoutManager = GridLayoutManager(context, 3)
            addItemDecoration(GridSpacingItemDecoration(3, 40, true))
        }

        mAdapter.onSearchEmpty = { displayError ->

            tv_error.apply {

                if (displayError) {
                    visible()
                } else {
                    gone()
                }
            }
        }
    }

    private fun setupSearchView() {

        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                mAdapter.filter.filter(newText)

                return false
            }
        })
    }

    private fun setupViews() {

        mViewModel.dataLoading.observe(this@MoviesFragment, Observer { dataLoading ->

            if (dataLoading) {
                loading_view.visible()
            } else {
                loading_view.gone()
                refresh_view.isRefreshing = false
            }
        })

        mViewModel.movies.observe(this@MoviesFragment, Observer { moviesList ->

            mAdapter.setMoviesList(moviesList)
        })
    }

    private fun setupSnackbar() {

        view?.setupSnackbar(this, mViewModel.snackbarText, Snackbar.LENGTH_SHORT)
    }
}