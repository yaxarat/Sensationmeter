package com.example.sensationmeter


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class VoidLogFragmentTest {

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
    fun voidLogFragmentTest() {
        val materialButton = onView(
            allOf(
                withId(R.id.void_button), withText("Void Log"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.host_fragment),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        materialButton.perform(click())

        val textInputEditText = onView(
            allOf(
                withId(R.id.void_editText),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.uid_textInputLayout),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText.perform(replaceText("1024"), closeSoftKeyboard())

        val textInputEditText2 = onView(
            allOf(
                withId(R.id.void_editText), withText("1024"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.uid_textInputLayout),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText2.perform(pressImeActionButton())

        val materialButton2 = onView(
            allOf(
                withId(R.id.home_button), withText("Home"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.host_fragment),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        materialButton2.perform(click())

        val materialButton3 = onView(
            allOf(
                withId(R.id.work_button), withText("Work"),
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
        materialButton3.perform(click())

        val materialButton4 = onView(
            allOf(
                withId(R.id.school_button), withText("School"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.host_fragment),
                        0
                    ),
                    4
                ),
                isDisplayed()
            )
        )
        materialButton4.perform(click())

        val materialButton5 = onView(
            allOf(
                withId(R.id.other_button), withText("Other Location"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.host_fragment),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        materialButton5.perform(click())

        val editText = onView(
            allOf(
                withId(R.id.void_editText), withText("1024"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.uid_textInputLayout),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        editText.check(ViewAssertions.matches(withText("1024")))

        val materialButton6 = onView(
            allOf(
                withId(R.id.save_button), withText("Save"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.host_fragment),
                        0
                    ),
                    5
                ),
                isDisplayed()
            )
        )
        materialButton6.perform(click())
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
