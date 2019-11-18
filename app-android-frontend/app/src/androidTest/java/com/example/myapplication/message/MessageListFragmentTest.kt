package com.example.myapplication.message


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
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MessageListFragmentTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(LoginActivity::class.java)

    @Test
    fun messageListFragmentTest() {
        val bottomNavigationItemView = onView(
            allOf(
                withId(R.id.navigation_messages), withContentDescription("Chat"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_view),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        bottomNavigationItemView.perform(click())

        val linearLayout = onView(
            allOf(
                withId(R.id.layout_chatbox),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_host_fragment),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        linearLayout.check(matches(isDisplayed()))

        val button = onView(
            allOf(
                withId(R.id.button_chatbox_send),
                childAtPosition(
                    allOf(
                        withId(R.id.layout_chatbox),
                        childAtPosition(
                            IsInstanceOf.instanceOf(android.view.ViewGroup::class.java),
                            2
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        button.check(matches(isDisplayed()))

        val textView = onView(
            allOf(
                withText("Messages"),
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
        textView.check(matches(withText("Messages")))

        val appCompatEditText = onView(
            allOf(
                withId(R.id.edittext_chatbox),
                childAtPosition(
                    allOf(
                        withId(R.id.layout_chatbox),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            2
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatEditText.perform(replaceText("hi"), closeSoftKeyboard())

        val editText2 = onView(
            allOf(
                withId(R.id.edittext_chatbox), withText("hi"),
                childAtPosition(
                    allOf(
                        withId(R.id.layout_chatbox),
                        childAtPosition(
                            IsInstanceOf.instanceOf(android.view.ViewGroup::class.java),
                            2
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        editText2.check(matches(withText("hi")))

        val materialButton = onView(
            allOf(
                withId(R.id.button_chatbox_send), withText("SEND"),
                childAtPosition(
                    allOf(
                        withId(R.id.layout_chatbox),
                        childAtPosition(
                            withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                            2
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        materialButton.perform(click())

        val textView2 = onView(
            allOf(
                withId(R.id.text_message_body), withText("hi"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.reyclerview_message_list),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("hi")))
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
