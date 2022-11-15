package wordsapp

import androidx.fragment.app.testing.launchFragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import com.example.wordsapp.LetterListFragment
import com.example.wordsapp.R
import org.junit.Test

@Test
class NavigationTests {

    private val navController = TestNavHostController(
        ApplicationProvider.getApplicationContext()
    )

    val letterListScenario = launchFragmentInContainer<LetterListFragment>(
        themeResId = R.style.Theme_Words
    )

    letterListScenario.onFragment { fragment ->
    navController.setGraph(R.navigation.nav_graph)
    Navigation.setViewNavController(fragment.requireView(), navController)
    }

    onView(withId(R.id.recycler_view))
    .perform(
        RecyclerViewActions
        .actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click()))
    assertEquals(navController.currentDestination?.id, R.id.wordListFragment)
}
