package co.artist.movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import co.artist.movies.view.fragment.GenreListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        launchFragment(GenreListFragment(),getString(R.string.genre_list))
    }

    private fun launchFragment(fragment: Fragment, fragmentTitle: String) {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.main_layout, fragment, fragmentTitle)
            .addToBackStack(getString(R.string.home))
            .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left)
            .commitAllowingStateLoss()
    }

    fun launchGenreListFragment(){
        launchFragment(GenreListFragment(),getString(R.string.genre_list))
    }
}
