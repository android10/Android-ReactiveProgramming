package com.fernandocejas.android10.rx.sample.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.fernandocejas.android10.rx.sample.data.DataManager;
import com.fernandocejas.android10.rx.sample.adapter.ElementsAdapter;
import com.fernandocejas.android10.rx.sample.view.ElementsLayoutManager;
import com.fernandocejas.android10.rx.sample.R;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

public class SampleActivityObserver extends Activity {

  @InjectView(android.R.id.list) RecyclerView rv_elements;
  @InjectView(android.R.id.button1) Button btn_AddElement;

  private DataManager dataManager;
  private ElementsAdapter adapter;
  private ElementsLayoutManager layoutManager;

  private Subscription subscription;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.inject(this);
    initialize();
    fillData();
  }

  @Override
  protected void onDestroy() {
    this.subscription.unsubscribe();
    super.onDestroy();
  }

  private void initialize() {
    this.subscription = Subscriptions.empty();
    this.dataManager = new DataManager();
    this.layoutManager = new ElementsLayoutManager(this);
    this.adapter = new ElementsAdapter(this);

    this.rv_elements.setLayoutManager(this.layoutManager);
    this.rv_elements.setAdapter(this.adapter);
  }

  private void fillData() {
    this.subscription = this.dataManager.getElements().subscribe(this.adapter);
  }

  @OnClick(android.R.id.button1) void onAddElementClick() {
    this.subscription = this.dataManager.getNewElement().subscribe(this.adapter);
  }
}
