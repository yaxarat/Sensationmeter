package com.example.sensationmeter


import androidx.test.espresso.DataInteraction
import androidx.test.espresso.ViewInteraction
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import androidx.test.runner.AndroidJUnit4
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent

import androidx.test.InstrumentationRegistry.getInstrumentation
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*

import com.example.sensationmeter.R

import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.anything
import org.hamcrest.Matchers.`is`

@LargeTest
@RunWith(AndroidJUnit4::class)
class DrinkLogFragmentTest {

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
    fun drinkLogFragmentTest() {
        val materialButton = onView(
            allOf(
                withId(R.id.drink_button), withText("Drink Log"),
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
        materialButton.perform(click())

        val appCompatCheckBox = onView(
            allOf(
                withId(R.id.caffeine_checkBox), withText("Contains caffeine"),
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
        appCompatCheckBox.perform(click())

        val appCompatCheckBox2 = onView(
            allOf(
                withId(R.id.sugar_checkBox), withText("Contains sugar"),
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
        appCompatCheckBox2.perform(click())

        val appCompatCheckBox3 = onView(
            allOf(
                withId(R.id.alcohol_checkBox), withText("Contains alcohol"),
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
        appCompatCheckBox3.perform(click())

        val appCompatCheckBox4 = onView(
            allOf(
                withId(R.id.carbonation_checkBox), withText("Contains carbonation"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.host_fragment),
                        0
                    ),
                    6
                ),
                isDisplayed()
            )
        )
        appCompatCheckBox4.perform(click())

        val materialButton3 = onView(
            allOf(
                withId(R.id.save_button), withText("Save"),
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

        val textInputEditText = onView(
            allOf(
                withId(R.id.drinkVolume_editText),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.textInputLayoutDrink),
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
                withId(R.id.drinkVolume_editText), withText("1024"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.textInputLayoutDrink),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText2.perform(pressImeActionButton())

        val editText = onView(
            allOf(
                withId(R.id.drinkVolume_editText), withText("1024"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.textInputLayoutDrink),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        editText.check(matches(withText("1024")))

        val materialButton2 = onView(
            allOf(
                withId(R.id.save_button), withText("Save"),
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
