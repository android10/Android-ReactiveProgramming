package com.fernandocejas.android10.rx.sample;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import java.util.List;

public class MainActivity extends Activity {

  @InjectView(android.R.id.list) RecyclerView rv_elements;
  @InjectView(android.R.id.button1) Button btn_AddElement;

  private DataManager dataManager;
  private List<String> elements;
  private ElementsAdapter adapter;
  private ElementsLayoutManager layoutManager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.inject(this);
    initialize();
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
}
