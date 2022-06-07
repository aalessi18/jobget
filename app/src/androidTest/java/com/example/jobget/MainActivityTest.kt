package com.example.jobget


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainActivityTest() {
        onView(
            allOf(
                withId(R.id.tv_heading),
                withParent(
                    allOf(
                        withId(R.id.cl_expenses),
                        withParent(withId(R.id.cl_balance_container))
                    )
                ),
                isDisplayed()
            )
        )

        onView(
            allOf(
                withId(R.id.tv_heading),
                withParent(
                    allOf(
                        withId(R.id.cl_income),
                        withParent(withId(R.id.cl_balance_container))
                    )
                ),
                isDisplayed()
            )
        )

        onView(
            allOf(
                withId(R.id.tv_heading),
                withParent(
                    allOf(
                        withId(R.id.cl_balance),
                        withParent(withId(R.id.cl_balance_container))
                    )
                ),
                isDisplayed()
            )
        )

        onView(
            allOf(
                withId(R.id.lpi_balance_indicator),
                isDisplayed()
            )
        )

        onView(
            allOf(
                withId(R.id.fab_button),
                isDisplayed()
            )
        )
    }
}
