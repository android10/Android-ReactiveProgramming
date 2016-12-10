/**
 * Copyright (C) 2015 android10.org Open Source Project
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fernandocejas.android10.rx.sample.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.fernandocejas.android10.rx.sample.R;
import com.fernandocejas.android10.rx.sample.adapter.ElementsAdapter;
import com.fernandocejas.android10.rx.sample.data.DataManager;
import com.fernandocejas.android10.rx.sample.data.NumberGenerator;
import com.fernandocejas.android10.rx.sample.data.StringGenerator;
import com.fernandocejas.android10.rx.sample.executor.JobExecutor;

public class ActivitySubscriberSample extends BaseActivity
    implements ElementsAdapter.ElementAddedListener {

  @Bind(android.R.id.list) RecyclerView rv_elements;

  private DataManager dataManager;
  private ElementsAdapter adapter;

  public static Intent getCallingIntent(Context context) {
    return new Intent(context, ActivitySubscriberSample.class);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sample_subscriber);

    ButterKnife.bind(this);
    initialize();
    fillData();
  }

  @Override
  protected void onDestroy() {
    adapter.dispose();
    super.onDestroy();
  }

  private void initialize() {
    dataManager =
        new DataManager(new StringGenerator(), new NumberGenerator(), JobExecutor.getInstance());
    adapter = new ElementsAdapter(this, this);
    rv_elements.setLayoutManager(new LinearLayoutManager(this));
    rv_elements.setAdapter(this.adapter);
  }

  private void fillData() {
    this.dataManager.elements().subscribe(this.adapter);
  }

  @OnClick(android.R.id.button1) void onAddElementClick() {
    this.dataManager.newElement().subscribe(this.adapter);
    Toast.makeText(this, "Element added using an observable!!!", Toast.LENGTH_SHORT).show();
  }

  @Override
  public void onElementAdded() {
    rv_elements.smoothScrollToPosition(adapter.getItemCount());
  }
}
