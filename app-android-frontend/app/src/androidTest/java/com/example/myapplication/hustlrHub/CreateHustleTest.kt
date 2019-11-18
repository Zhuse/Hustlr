package com.example.myapplication.hustlrHub


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.example.myapplication.R
import com.example.myapplication.auth.LoginActivity
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * UI Test for Creating New Hustles.
 */
@LargeTest
@RunWith(AndroidJUnit4::class)
class CreateHustleTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(LoginActivity::class.java)

    /**
     * Check UI components when creating new hustle.
     */
    @Test
    fun createHustleTest() {
        val bottomNavigationItemView = onView(
            allOf(
                withId(R.id.navigation_hustlr_hub), withContentDescription("My Hustlr Hub"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_view),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        bottomNavigationItemView.perform(click())

        val textView = onView(
            allOf(
                withText("My Hustlr Hub"),
                childAtPosition(
                    allOf(
                        withId(R.id.action_bar),
                        childAtPosition(
                            withId(R.id.action_bar_container),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textView.check(matches(withText("My Hustlr Hub")))

        val imageButton = onView(
            allOf(
                withId(R.id.createNewHustleButton),
                childAtPosition(
                    allOf(
                        withId(R.id.frameLayout2),
                        childAtPosition(
                            withId(R.id.nav_host_fragment),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        imageButton.check(matches(isDisplayed()))

        val floatingActionButton = onView(
            allOf(
                withId(R.id.createNewHustleButton),
                childAtPosition(
                    allOf(
                        withId(R.id.frameLayout2),
                        childAtPosition(
                            withId(R.id.nav_host_fragment),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        floatingActionButton.perform(click())

        val button = onView(
            allOf(
                withId(R.id.createNewHustleButton),
                childAtPosition(
                    allOf(
                        withId(R.id.frameLayout3),
                        childAtPosition(
                            withId(R.id.nav_host_fragment),
                            0
                        )
                    ),
                    8
                ),
                isDisplayed()
            )
        )
        button.check(matches(isDisplayed()))

        val appCompatEditText = onView(
            allOf(
                withId(R.id.titleText),
                childAtPosition(
                    allOf(
                        withId(R.id.frameLayout3),
                        childAtPosition(
                            withId(R.id.nav_host_fragment),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatEditText.perform(replaceText("Test"), closeSoftKeyboard())

        val appCompatEditText2 = onView(
            allOf(
                withId(R.id.descriptionText),
                childAtPosition(
                    allOf(
                        withId(R.id.frameLayout3),
                        childAtPosition(
                            withId(R.id.nav_host_fragment),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatEditText2.perform(replaceText("Test"), closeSoftKeyboard())

        val appCompatEditText3 = onView(
            allOf(
                withId(R.id.locationText),
                childAtPosition(
                    allOf(
                        withId(R.id.frameLayout3),
                        childAtPosition(
                            withId(R.id.nav_host_fragment),
                            0
                        )
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatEditText3.perform(replaceText("Test"), closeSoftKeyboard())

        val appCompatEditText4 = onView(
            allOf(
                withId(R.id.offerPriceValue),
                childAtPosition(
                    allOf(
                        withId(R.id.frameLayout3),
                        childAtPosition(
                            withId(R.id.nav_host_fragment),
                            0
                        )
                    ),
                    5
                ),
                isDisplayed()
            )
        )
        appCompatEditText4.perform(replaceText("5"), closeSoftKeyboard())

        val chip = onView(
            allOf(
                withText("other"),
                childAtPosition(
                    allOf(
                        withId(R.id.categoriesChipGroup),
                        childAtPosition(
                            withId(R.id.frameLayout3),
                            7
                        )
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        chip.perform(click())

        val materialButton = onView(
            allOf(
                withId(R.id.createNewHustleButton), withText("Submit New Hustle"),
                childAtPosition(
                    allOf(
                        withId(R.id.frameLayout3),
                        childAtPosition(
                            withId(R.id.nav_host_fragment),
                            0
                        )
                    ),
                    8
                ),
                isDisplayed()
            )
        )
        materialButton.perform(click())
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
