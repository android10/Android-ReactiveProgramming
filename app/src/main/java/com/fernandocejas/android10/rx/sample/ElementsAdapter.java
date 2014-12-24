/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package com.fernandocejas.android10.rx.sample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import java.util.Collection;
import java.util.List;

public class ElementsAdapter extends RecyclerView.Adapter<ElementsAdapter.ElementViewHolder> {

  private List<String> elements;
  private LayoutInflater layoutInflater;

  public ElementsAdapter(Context context, List<String> elements) {
    this.validateElements(elements);
    this.elements = elements;
    this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

  private void validateElements(Collection<String> elementsCollection) {
    if (elementsCollection == null) {
      throw new IllegalArgumentException("The list cannot be null");
    }
  }

  static class ElementViewHolder extends RecyclerView.ViewHolder {
    @InjectView(R.id.title) TextView textViewText;

    public ElementViewHolder(View itemView) {
      super(itemView);
      ButterKnife.inject(this, itemView);
    }
  }
}
