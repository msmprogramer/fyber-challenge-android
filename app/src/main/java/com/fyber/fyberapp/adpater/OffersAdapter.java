package com.fyber.fyberapp.adpater;

import android.content.Context;
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

public class OffersAdapter extends RecyclerView.Adapter<OffersAdapter.OfferViewHolder> {
    private List<Offer> data = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;

    public OffersAdapter(Context context, List<Offer> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public OfferViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.offer_item, parent, false);
        OfferViewHolder holder = new OfferViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(OfferViewHolder offerViewHolder, int position) {
        Offer offer = data.get(position);
        offerViewHolder.getTextViewTitle().setText(
                offer.getTitle())
        ;

        offerViewHolder.getTextViewTeaser().setText(
                offer.getTeaser()
        );

        offerViewHolder.getTextViewPayout().setText(
                String.valueOf(offer.getPayout())
        );

        Picasso.with(context).load(offer.getOfferThumbnail().getHires())
                .placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher)
                .into(offerViewHolder.getImageViewOfferThumbnail());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

  public static class OfferViewHolder extends RecyclerView.ViewHolder {

      private TextView textViewTitle;
      private TextView textViewTeaser;
      private TextView textViewPayout;
      private ImageView imageViewOfferThumbnail;

        public OfferViewHolder(View itemView) {
            super(itemView);
            textViewTitle = (TextView) itemView.findViewById(R.id.textView_title);
            textViewTeaser = (TextView) itemView.findViewById(R.id.textView_teaser);
            textViewPayout = (TextView) itemView.findViewById(R.id.textView_payout);
            imageViewOfferThumbnail = (ImageView) itemView.findViewById(R.id.imgView_offer_thumbnail);
        }

      public TextView getTextViewTitle() {
          return textViewTitle;
      }

      public TextView getTextViewTeaser() {
          return textViewTeaser;
      }

      public TextView getTextViewPayout() {
          return textViewPayout;
      }

      public ImageView getImageViewOfferThumbnail() {
          return imageViewOfferThumbnail;
      }
    }
}