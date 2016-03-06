package com.fyber.fyberapp.adpater;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fyber.fyberapp.R;
import com.fyber.fyberapp.model.Offer;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class OffersAdapter extends RecyclerView.Adapter<OffersAdapter.OfferViewHolder> {
    private List<Offer> offers;
    private Context context;
    public OffersAdapter(Context context, List<Offer> offers) {
        this.context = context;
        setList(offers);
    }

    @Override
    public OfferViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View offerView = inflater.inflate(R.layout.offer_item, parent, false);
        OfferViewHolder holder = new OfferViewHolder(offerView);
        return holder;
    }

    @Override
    public void onBindViewHolder(OfferViewHolder offerViewHolder, int position) {
        Offer offer = offers.get(position);

        offerViewHolder.textViewTitle.setText(
                offer.getTitle() != null? offer.getTitle() : ""
        );

        offerViewHolder.textViewTeaser.setText(
                offer.getTeaser() != null? offer.getTeaser() : ""
        );

        offerViewHolder.textViewPayout.setText(
                String.valueOf(offer.getPayout() != null? offer.getPayout() : 0)
        );

        String offerThumbnailUrl = offer.getOfferThumbnail() != null?
                offer.getOfferThumbnail().getHires() : null;

        if (offerThumbnailUrl != null && !offerThumbnailUrl.isEmpty()) {
            Picasso.with(context).load(offerThumbnailUrl)
                    .placeholder(R.drawable.ic_fyber_icon).error(R.drawable.ic_fyber_icon)
                    .into(offerViewHolder.imageViewOfferThumbnail
                    );
        } else {
            offerViewHolder.imageViewOfferThumbnail.
                    setImageResource(R.drawable.ic_fyber_icon
                    );
        }
    }

    public void replaceData(List<Offer> offers) {
        setList(offers);
        notifyDataSetChanged();
    }

    private void setList(@NonNull List<Offer> offers) {
       this.offers = checkNotNull(offers);
    }

    @Override
    public int getItemCount() {
        return offers.size();
    }

  public static class OfferViewHolder extends RecyclerView.ViewHolder {

      public TextView textViewTitle;
      public TextView textViewTeaser;
      public TextView textViewPayout;
      public ImageView imageViewOfferThumbnail;

        public OfferViewHolder(View itemView) {
            super(itemView);
            textViewTitle = (TextView) itemView.findViewById(R.id.textView_title);
            textViewTeaser = (TextView) itemView.findViewById(R.id.textView_teaser);
            textViewPayout = (TextView) itemView.findViewById(R.id.textView_payout);
            imageViewOfferThumbnail = (ImageView) itemView.findViewById(R.id.imgView_offer_thumbnail);
        }

    }
}