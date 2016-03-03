package com.fyber.fyberapp.ui.fragments;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.widget.EditText;

import com.fyber.fyberapp.R;
import com.fyber.fyberapp.ui.activites.OffersRequestActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class OffersRequestActivityTest {


    @Rule
    public ActivityTestRule<OffersRequestActivity> activityTestRule =
            new ActivityTestRule<>(OffersRequestActivity.class);

    @Test
    public void shouldShowErrorMessageWhenApiKeyIsEmpty() {
        onView(withId(R.id.editText_api_key)).perform(clearText());
        onView(withId(R.id.button_find_offers)).perform(click());
        onView(withId(R.id.editText_api_key)).check(matches(
                withError(R.string.error_empty_api_key)
        ));
    }

    @Test
    public void shouldShowErrorMessageWhenUidIsEmpty() {
        onView(withId(R.id.editText_uid)).perform(clearText());
        onView(withId(R.id.button_find_offers)).perform(click());
        onView(withId(R.id.editText_uid)).check(matches(
                withError(R.string.error_empty_uid)
        ));
    }

    @Test
    public void shouldShowErrorMessageWhenAppIdIsEmpty() {
        onView(withId(R.id.editText_app_id)).perform(clearText());
        onView(withId(R.id.button_find_offers)).perform(click());
        onView(withId(R.id.editText_app_id)).check(matches(
                withError(R.string.error_empty_app_id)
        ));
    }

    @Test
    public void shouldShowErrorMessageWhenPub0IsEmpty() {
        onView(withId(R.id.editText_pub0)).perform(clearText());
        onView(withId(R.id.button_find_offers)).perform(click());
        onView(withId(R.id.editText_pub0)).check(matches(
                withError(R.string.error_empty_pub0)
        ));
    }

    @Test
    public void shouldOpenOfferListActivityWhenClickGetOffersButton() {
        onView(withId(R.id.editText_api_key)).perform(typeText("apikey"));
        onView(withId(R.id.editText_app_id)).perform(typeText("appid"));
        onView(withId(R.id.editText_uid)).perform(typeText("uid"));
        onView(withId(R.id.editText_pub0)).perform(typeText("pub0"));
        onView(withId(R.id.button_find_offers)).perform(click());

        onView(withId(R.id.recycle_view_offers)).check(matches(isDisplayed()));

    }

    @Test
    public void testBackButtonIfItCloseTheActivity() {

        onView(withId(R.id.editText_api_key)).perform(typeText("apikey"));
        onView(withId(R.id.editText_app_id)).perform(typeText("appid"));
        onView(withId(R.id.editText_uid)).perform(typeText("uid"));
        onView(withId(R.id.editText_pub0)).perform(typeText("pub0"));
        onView(withId(R.id.button_find_offers)).perform(click());
        onView(withId(R.id.recycle_view_offers)).check(matches(isDisplayed()));

        onView(withContentDescription("Navigate up")).perform(click());

        onView(withId(R.id.editText_app_id)).check(matches(isDisplayed()));
    }

    @Test
    public void testSaveAndRestoreStateWhenRotate() {
        onView(withId(R.id.editText_app_id)).perform(clearText());
        onView(withId(R.id.editText_app_id)).perform(typeText("apiId"));
        rotateScreen();
        onView(withId(R.id.editText_app_id))
                .check(matches(withText("apiId")));
    }

    private void rotateScreen() {

        int orientation
                = activityTestRule.getActivity().getResources().getConfiguration().orientation;

        activityTestRule.getActivity().setRequestedOrientation(
                (orientation == Configuration.ORIENTATION_PORTRAIT) ?
                        ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE :
                        ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }


    private String getString(int resId){
        return getInstrumentation().getTargetContext().getString(resId);
    }

    private static Matcher<View> withError(final int resourceId) {
        return new TypeSafeMatcher<View>() {

            @Override
            public void describeTo(Description description) {

            }

            @Override
            public boolean matchesSafely(View view) {
                if (!(view instanceof EditText)) {
                    return false;
                }
                EditText editText = (EditText) view;
                return editText.getError().toString().equals(view.getResources().getString(resourceId));
            }

        };
    }
}