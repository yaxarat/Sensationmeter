package com.example.sensationmeter


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.ViewAction
import android.widget.SeekBar
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.action.Press
import androidx.test.espresso.action.CoordinatesProvider
import androidx.test.espresso.action.Tap
import androidx.test.espresso.action.GeneralClickAction
import androidx.test.espresso.assertion.ViewAssertions


@LargeTest
@RunWith(AndroidJUnit4::class)
class MeterFragmentTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Rule
    @JvmField
    var mGrantPermissionRule =
        GrantPermissionRule.grant(
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE"
        )

    @Test
    fun meterFragmentTest() {
        val materialButton = onView(
            allOf(
                withId(R.id.meter_button), withText("Sensation Meter"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.host_fragment),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        materialButton.perform(click())

        val seekBar = onView(
            allOf(
                withId(R.id.progress_seekBar),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.host_fragment),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        seekBar.check(matches(isDisplayed()))


        onView(withId(R.id.progress_seekBar)).perform(setProgress(10))
        onView(isRoot()).perform(waitFor(1000))

        val textView = onView(
            allOf(
                withId(R.id.progress_textView), isDisplayed()
            )
        )
        textView.check(matches(withText("10%")))
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

    private fun setProgress(progress: Int): ViewAction {
        return object : ViewAction {
            override fun perform(uiController: UiController, view: View) {
                val seekBar = view as SeekBar
                seekBar.progress = progress
            }

            override fun getDescription(): String {
                return "Set a progress on a SeekBar"
            }

            override fun getConstraints(): Matcher<View> {
                return isAssignableFrom(SeekBar::class.java)
            }
        }
    }

    fun waitFor(millis: Long): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return isRoot()
            }

            override fun getDescription(): String {
                return "Wait for $millis milliseconds."
            }

            override fun perform(uiController: UiController, view: View) {
                uiController.loopMainThreadForAtLeast(millis)
            }
        }
    }
}
