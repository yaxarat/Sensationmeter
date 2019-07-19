package com.example.sensationmeter


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
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
class SettingsLoginFragmentTest {

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
    fun settingsFragmentTest() {
        val floatingActionButton = onView(
            allOf(
                withId(R.id.settings_fab),
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
        floatingActionButton.perform(click())

        val textInputEditText = onView(
            allOf(
                withId(R.id.passwordCheck_editText),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.check_textInputLayout),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText.perform(replaceText("password"), closeSoftKeyboard())

        val textInputEditText2 = onView(
            allOf(
                withId(R.id.passwordCheck_editText), withText("password"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.check_textInputLayout),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText2.perform(pressImeActionButton())

        val materialButton = onView(
            allOf(
                withId(R.id.check_button), withText("Submit"),
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
        materialButton.perform(click())

        val textInputEditText3 = onView(
            allOf(
                withId(R.id.uid_editText),
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
        textInputEditText3.perform(replaceText("pass"), closeSoftKeyboard())

        val textInputEditText4 = onView(
            allOf(
                withId(R.id.password_editText),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.pass_textInputLayout),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText4.perform(replaceText("password"), closeSoftKeyboard())

        val textInputEditText5 = onView(
            allOf(
                withId(R.id.password_editText), withText("password"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.pass_textInputLayout),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText5.perform(pressImeActionButton())

        val materialButton2 = onView(
            allOf(
                withId(R.id.check_button), withText("Save"),
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
        materialButton2.perform(click())
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
