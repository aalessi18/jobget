package com.example.jobget.fragment


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.example.jobget.MainActivity
import com.example.jobget.R
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4ClassRunner::class)
class AddTransactionFragmentDialogTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun addTransactionFragmentDialogTest() {
        onView(
            allOf(
                withId(R.id.fab_button),
                isDisplayed()
            )
        ).perform(click())

        onView(
            allOf(
                withId(R.id.tv_dialog_title),
                isDisplayed()
            )
        )

        onView(
            allOf(
                withId(R.id.sp_transaction_type),
                isDisplayed()
            )
        )

        onView(
            allOf(
                withId(R.id.cl_dollar_container),
                isDisplayed()
            )
        )

        onView(
            allOf(
                withId(R.id.btn_cancel),
                isDisplayed()
            )
        )

        onView(
            allOf(
                withId(R.id.btn_add),
                isDisplayed()
            )
        )
    }
}
