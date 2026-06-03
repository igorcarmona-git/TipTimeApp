package com.example.tiptimeapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.hamcrest.CoreMatchers.containsString
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    // Configura a regra para iniciar a MainActivity antes de cada teste
    @get:Rule
    val activity = ActivityScenarioRule(MainActivity::class.java)

    // Verifica se o contexto do aplicativo é o esperado
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.tiptimeapp", appContext.packageName)
    }

    // Verifica se o resultado da gorjeta está correto para um custo de serviço de 50
    @Test
    fun tipResult() {
        onView(withId(R.id.etCostOfServiceInput))
            .perform(typeText("50.00"))
            .perform(ViewActions.closeSoftKeyboard())

        onView(withId(R.id.bt_calculate))
            .perform(ViewActions.click())

        onView(withId(R.id.tipResult))
            .check(matches(withText(containsString("10"))))
    }

}