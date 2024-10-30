package edu.temple.dicethrow

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


/*
Our DieThrow application has been refactored to move the dieRoll() logic
into the ViewModel instead of the Fragment.
Study the DieViewModel, ButtonFragment, and DieFragment classes to
see the changes.

Follow the requirements below to have this app function
in both portrait and landscape configurations.
The Activity layout files for both Portrait and Landscape are already provided
*/

class MainActivity : AppCompatActivity(), ButtonFragment.ButtonInterface {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* TODO 1: Load fragment(s)
            - Show only Button Fragment if portrait
            - show both fragments if Landscape
          */

        if (savedInstanceState == null) {
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                // Show only Button Fragment if portrait
                supportFragmentManager.beginTransaction()
                    .add(R.id.container1, ButtonFragment())
                    .commit()
            } else {
                // show both fragments if Landscape
                supportFragmentManager.beginTransaction()
                    .add(R.id.container1, ButtonFragment())
                    .add(R.id.container2, DieFragment())
                    .commit()
            }
        } else {
            if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                val currentFragment = supportFragmentManager.findFragmentById(R.id.container1)

                if (currentFragment is ButtonFragment) {
                    // ButtonFragment, add DieFragment to container2
                    supportFragmentManager.beginTransaction()
                        .add(R.id.container2, DieFragment())
                        .commit()
                } else if (currentFragment is DieFragment) {
                    // DieFragment, move it to container2 & add ButtonFragment to container1
                    supportFragmentManager.beginTransaction()
                        .remove(currentFragment)
                        .add(R.id.container1, ButtonFragment())
                        .add(R.id.container2, DieFragment())
                        .commit()
                }
            }
        }
    }

    /* TODO 2: switch fragments if portrait (no need to switch fragments if Landscape)
        */
    override fun buttonClicked() {
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container1, DieFragment())
                .addToBackStack(null)
                .commit()
        }
    }
}