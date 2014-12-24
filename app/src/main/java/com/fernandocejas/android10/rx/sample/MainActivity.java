package com.fernandocejas.android10.rx.sample;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import java.util.List;
import rx.Observable;
import rx.Observer;
import rx.Subscription;

public class MainActivity extends Activity {

  private static final String LOG_TAG = "MainActivity";

  @InjectView(android.R.id.list) RecyclerView rv_elements;
  @InjectView(android.R.id.button1) Button btn_AddElement;

  private DataManager dataManager;
  private List<String> elements;
  private ElementsAdapter adapter;
  private ElementsLayoutManager layoutManager;

  private Subscription subscription;
  private Observable<String> observable;
  private MyObserver observer;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.inject(this);
    initialize();
    getData();
  }

  private void getData() {
    this.observer = new MyObserver();
    this.observable = this.dataManager.getElements();
    this.subscription = this.observable.subscribe(this.observer);
  }

  private void initialize() {
    this.dataManager = new DataManager();
    this.elements = this.dataManager.getElementsList();

    this.layoutManager = new ElementsLayoutManager(this);
    this.adapter = new ElementsAdapter(this, this.elements);

    this.rv_elements.setLayoutManager(this.layoutManager);
    this.rv_elements.setAdapter(this.adapter);
  }

  @OnClick(android.R.id.button1) void onAddElementClick() {
    dataManager.addRandomString();
  }

  private static class MyObserver implements Observer<String> {

    @Override public void onNext(String s) {
      Log.d(LOG_TAG, "String ------> " + s);
    }

    @Override public void onCompleted() {
      Log.d(LOG_TAG, "Complete!!!!");
    }

    @Override public void onError(Throwable e) {
      Log.d(LOG_TAG, "Error!!!!");
    }
  }
}
