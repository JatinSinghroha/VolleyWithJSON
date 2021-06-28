package com.jatinsinghroha.volleywithjson;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jatinsinghroha.volleywithjson.models.District;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RVListOfDistrictsAdapter extends RecyclerView.Adapter<RVListOfDistrictsAdapter.ViewHolder> {

    List<District> mDistrictList;

    public RVListOfDistrictsAdapter(List<District> districtList) {
        this.mDistrictList = districtList;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.district_item_rv, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {

        District district = mDistrictList.get(position);

        TextView districtNameTV = (TextView) holder.itemView;

        String idAndName = district.getDistrictId()+". "+district.getDistrictName();

        districtNameTV.setText(idAndName);
    }

    @Override
    public int getItemCount() {
        return mDistrictList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
        }
    }
}
