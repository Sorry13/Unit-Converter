package com.example.unitconvertersm.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unitconvertersm.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<String> iconTitles;
    List<Integer> iconImages;
    Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.recyclerView = findViewById(R.id.menuRecyclerView);

        iconTitles = new ArrayList<>();
        iconImages = new ArrayList<>();

        iconTitles.add(getString(R.string.Temperature));
        iconTitles.add(getString(R.string.Weight));
        iconTitles.add(getString(R.string.Time));
        iconTitles.add(getString(R.string.Memory));
        iconTitles.add(getString(R.string.BMI));
        iconTitles.add(getString(R.string.Currency));

        iconImages.add(R.drawable.ic_baseline_thermostat_24);
        iconImages.add(R.drawable.ic_baseline_monitor_weight_24);
        iconImages.add(R.drawable.ic_baseline_timer_24);
        iconImages.add(R.drawable.ic_baseline_memory_24);
        iconImages.add(R.drawable.ic_baseline_calculate_24);
        iconImages.add(R.drawable.ic_baseline_currency_exchange_24);

        adapter = new Adapter(this, iconTitles, iconImages);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    private class Adapter extends RecyclerView.Adapter<ViewHolder> {

        List<String> iconTitles;
        List<Integer> iconImages;
        LayoutInflater inflater;

        public Adapter(Context context, List<String> iconTitles, List<Integer> iconImages) {
            this.iconTitles = iconTitles;
            this.iconImages = iconImages;
            this.inflater = LayoutInflater.from(context);
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.menu_item, parent, false);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.iconTitle.setText(iconTitles.get(position));
            holder.gridIcon.setImageResource(iconImages.get(position));
        }

        @Override
        public int getItemCount() {
            return iconTitles.size();
        }
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        TextView iconTitle;
        ImageView gridIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iconTitle = itemView.findViewById(R.id.iconTextView);
            gridIcon = itemView.findViewById(R.id.iconImageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent;

                    switch (getAdapterPosition()) {
                        case 0:
                            intent = new Intent();
                            intent.setClass(getApplicationContext(), TemperatureConverterActivity.class);
                            startActivity(intent);
                            break;
                        case 1:
                            intent = new Intent();
                            intent.setClass(getApplicationContext(), WeightConverterActivity.class);
                            startActivity(intent);
                            break;
                        case 2:
                            intent = new Intent();
                            intent.setClass(getApplicationContext(), TimeConverterActivity.class);
                            startActivity(intent);
                            break;
                        case 3:
                            intent = new Intent();
                            intent.setClass(getApplicationContext(), MemoryConverterActivity.class);
                            startActivity(intent);
                            break;
                        case 4:
                            intent = new Intent();
                            intent.setClass(getApplicationContext(), BMICalculatorActivity.class);
                            startActivity(intent);
                            break;
                        case 5:
                            intent = new Intent();
                            intent.setClass(getApplicationContext(), CurrencyConverterActivity.class);
                            startActivity(intent);
                            break;
                    }
                }
            });
        }
    }
}