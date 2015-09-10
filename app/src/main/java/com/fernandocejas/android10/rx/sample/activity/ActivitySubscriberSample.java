package com.fernandocejas.android10.rx.sample.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.fernandocejas.android10.rx.sample.R;
import com.fernandocejas.android10.rx.sample.adapter.ElementsAdapter;
import com.fernandocejas.android10.rx.sample.data.DataManager;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

public class ActivitySubscriberSample extends BaseActivity {

  @InjectView(android.R.id.list) RecyclerView rv_elements;

  private DataManager dataManager;
  private ElementsAdapter adapter;

  private Subscription subscription;

  public static Intent getCallingIntent(Context context) {
    return new Intent(context, ActivitySubscriberSample.class);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sample_observer);

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
    this.adapter = new ElementsAdapter(this);
    this.rv_elements.setLayoutManager(new LinearLayoutManager(this));
    this.rv_elements.setAdapter(this.adapter);
  }

  private void fillData() {
    this.subscription = this.dataManager.getElements().subscribe(this.adapter);
  }

  @OnClick(android.R.id.button1) void onAddElementClick() {
    this.subscription = this.dataManager.getNewElement().subscribe(this.adapter);
    Toast.makeText(this, "Element added using an observable!!!", Toast.LENGTH_SHORT).show();
  }
}
