/**
 * Copyright (C) 2015 android10.org Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fernandocejas.android10.rx.sample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.fernandocejas.android10.rx.sample.R;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import java.util.List;

public class ElementsAdapter extends RecyclerView.Adapter<ElementsAdapter.ElementViewHolder>
    implements Observer<String> {

  public interface ElementAddedListener {
    void onElementAdded();
  }

  private static final String LOG_TAG = "ElementsAdapter";

  private final List<String> elements;
  private final LayoutInflater layoutInflater;
  private final ElementAddedListener listener;

  private Disposable disposable;

  public ElementsAdapter(Context context, ElementAddedListener listener) {
    if (listener == null) {
      throw new IllegalArgumentException("Listener cannot be null");
    }
    this.elements = new ArrayList<>();
    this.layoutInflater =
        (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    this.listener = listener;
  }

  @Override public ElementViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    final View view = layoutInflater.inflate(R.layout.row_element, parent, false);
    return new ElementViewHolder(view);
  }

  @Override public void onBindViewHolder(ElementViewHolder holder, int position) {
    final String element = elements.get(position);
    holder.textViewText.setText(element);
  }

  @Override public int getItemCount() {
    return elements.size();
  }

  @Override public void onSubscribe(Disposable disposable) {
    this.disposable = disposable;
  }

  @Override public void onNext(String stringElement) {
    elements.add(stringElement);
  }

  @Override public void onComplete() {
    notifyDataSetChanged();
    listener.onElementAdded();
  }

  @Override public void onError(Throwable e) {
    Log.e(LOG_TAG, e.getMessage());
  }

  public void dispose() {
    if (disposable != null && !disposable.isDisposed()) disposable.dispose();
  }

  static class ElementViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.title) TextView textViewText;

    ElementViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
