/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package com.fernandocejas.android10.rx.sample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.fernandocejas.android10.rx.sample.R;
import java.util.ArrayList;
import java.util.List;
import rx.Observer;

public class ElementsAdapter extends RecyclerView.Adapter<ElementsAdapter.ElementViewHolder>
    implements Observer<String> {

  private static final String LOG_TAG = "ElementsAdapter";

  private List<String> elements;
  private LayoutInflater layoutInflater;

  public ElementsAdapter(Context context) {
    this.elements = new ArrayList<>();
    this.layoutInflater =
        (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
  }

  @Override public ElementViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    final View view = this.layoutInflater.inflate(R.layout.row_element, parent, false);
    final ElementViewHolder userViewHolder = new ElementViewHolder(view);

    return userViewHolder;
  }

  @Override public void onBindViewHolder(ElementViewHolder holder, int position) {
    final String element = this.elements.get(position);
    holder.textViewText.setText(element);
  }

  @Override public int getItemCount() {
    return (this.elements != null) ? this.elements.size() : 0;
  }

  @Override public void onNext(String stringElement) {
    elements.add(stringElement);
  }

  @Override public void onCompleted() {
    notifyDataSetChanged();
  }

  @Override public void onError(Throwable e) {
    Log.e(LOG_TAG, e.getMessage());
  }

  static class ElementViewHolder extends RecyclerView.ViewHolder {
    @InjectView(R.id.title) TextView textViewText;

    public ElementViewHolder(View itemView) {
      super(itemView);
      ButterKnife.inject(this, itemView);
    }
  }
}
