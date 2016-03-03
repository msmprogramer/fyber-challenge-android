package com.fyber.fyberapp.ui.activites;

import android.content.Intent;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.test.suitebuilder.annotation.LargeTest;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.fyber.fyberapp.R;
import com.fyber.fyberapp.model.Offer;
import com.fyber.fyberapp.model.OfferThumbnail;
import com.fyber.fyberapp.model.OffersRequest;
import com.fyber.fyberapp.mvp.interactor.FakeOffersInteractorImp;
import com.fyber.fyberapp.ui.fragments.OffersRequestFragment;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Collections;

import javax.inject.Inject;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollTo;
import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkArgument;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class OffersListActivityTest {

    private static String OFFER_TITLE = "fyber";

    private static String OFFER_TEASER = "fyber teaser";

    private static int OFFER_PAYOUT = 55;

    private static String OFFER_IMAGE = "http://cdn4.sponsorpay.com/assets/61533/clipular_square_175.png";


    @Inject
    OffersRequest offersRequest;

    @Rule
    public ActivityTestRule<OffersListActivity> activityTestRule =
            new ActivityTestRule<>(OffersListActivity.class, true, false);

    @Test
    public void listOffers_ShowEmptyOffersWhenOffersAreNull() {

        FakeOffersInteractorImp.setOffers(null);

        launchActivity();

        onView(withId(R.id.textView_empty_offers)).check(matches(isDisplayed()));
    }

    @Test
    public void listOffers_ShowEmptyOffersWhenOffersAreEmpty() {

        FakeOffersInteractorImp.setOffers(Collections.<Offer>emptyList());

        launchActivity();

        onView(withId(R.id.textView_empty_offers)).check(matches(isDisplayed()));
    }

    @Test
    public void listOffers_ShowOffers() {

        FakeOffersInteractorImp.setOffers(Collections.singletonList(createOffer(OFFER_IMAGE)));

        launchActivity();

        onView(withId(R.id.recycle_view_offers)).perform(scrollTo(hasDescendant(withText(OFFER_TEASER))));
        onView(withItemText(OFFER_TITLE)).check(matches(isDisplayed()));
        onView(withItemText(OFFER_TEASER)).check(matches(isDisplayed()));
        onView(withItemText(String.valueOf(OFFER_PAYOUT))).check(matches(isDisplayed()));
        onView(withId(R.id.imgView_offer_thumbnail)).check(matches(isDisplayed()));
        onView(withId(R.id.imgView_offer_thumbnail)).check(matches(hasDrawable()));
    }

    @Test
    public void listOffers_ShowOffersWhenThumbnailImageEmpty() {

        FakeOffersInteractorImp.setOffers(Collections.singletonList(createOffer("")));

        launchActivity();

        onView(withId(R.id.recycle_view_offers)).perform(scrollTo(hasDescendant(withText(OFFER_TEASER))));
        onView(withItemText(OFFER_TITLE)).check(matches(isDisplayed()));
        onView(withItemText(OFFER_TEASER)).check(matches(isDisplayed()));
        onView(withId(R.id.imgView_offer_thumbnail)).check(matches(isDisplayed()));
        onView(withId(R.id.imgView_offer_thumbnail)).check(matches(hasDrawable()));
    }


    private void launchActivity() {
        Intent startIntent = new Intent();
        startIntent.putExtra(OffersRequestFragment.EXTRA_OFFER_REQUEST, offersRequest);
        activityTestRule.launchActivity(startIntent);
    }

    private Offer createOffer(String thumbnailUrl) {

        OfferThumbnail offerThumbnail = new OfferThumbnail();
        offerThumbnail.setHires(thumbnailUrl);

        Offer offer = new Offer();
        offer.setTitle(OFFER_TITLE);
        offer.setTeaser(OFFER_TEASER);
        offer.setPayout(OFFER_PAYOUT);
        offer.setThumbnail(offerThumbnail);

        return offer;
    }


    private static Matcher<View> withItemText(final String itemText) {
        checkArgument(!TextUtils.isEmpty(itemText), "itemText cannot be null or empty");
        return new TypeSafeMatcher<View>() {
            @Override
            public boolean matchesSafely(View item) {
                return allOf(
                        isDescendantOfA(isAssignableFrom(RecyclerView.class)),
                        withText(itemText)).matches(item);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("is isDescendantOfA RV with text " + itemText);
            }
        };
    }

    private static  BoundedMatcher<View, ImageView> hasDrawable() {
        return new BoundedMatcher<View, ImageView>(ImageView.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("has drawable");
            }

            @Override
            public boolean matchesSafely(ImageView imageView) {
                return imageView.getDrawable() != null;
            }
        };
    }
}