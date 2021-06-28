package com.jatinsinghroha.volleywithjson;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jatinsinghroha.volleywithjson.models.State;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RVListOfStatesAdapter extends RecyclerView.Adapter<RVListOfStatesAdapter.ViewHolder> {

    List<State> mStateList;

    public RVListOfStatesAdapter(List<State> stateList){
        this.mStateList = stateList;
    }

    @NonNull
    @NotNull
    @Override
    public RVListOfStatesAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.state_item_rv, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RVListOfStatesAdapter.ViewHolder holder, int position) {
        State state = mStateList.get(position);

        String idAndName = state.getStateID()+". "+state.getStateName();

        holder.stateNameAndIDTV.setText(idAndName);

        holder.stateNameAndIDTV.setOnClickListener(v -> {
            if (holder.districtsRV.getVisibility() == View.GONE) {
                holder.districtsRV.setVisibility(View.VISIBLE);
            } else {
                holder.districtsRV.setVisibility(View.GONE);
            }
        });

        RVListOfDistrictsAdapter adapter = new RVListOfDistrictsAdapter(state.getDistrictList().getDistricts());

        holder.districtsRV.setAdapter(adapter);

        holder.districtsRV.setLayoutManager(new GridLayoutManager(holder.districtsRV.getContext(), 2));

    }

    @Override
    public int getItemCount() {
        return mStateList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView stateNameAndIDTV;
        RecyclerView districtsRV;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            stateNameAndIDTV = itemView.findViewById(R.id.stateNameAndIDTV);
            districtsRV = itemView.findViewById(R.id.districtsRV);

        }
    }
}
