package com.example.countrycalculator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class BalanceAdapter extends RecyclerView.Adapter<BalanceAdapter.VH> {

    private final List<BalanceCalculator.PersonBalance> data = new ArrayList<>();
    private final DecimalFormat df = new DecimalFormat("#,##0.00");

    static class VH extends RecyclerView.ViewHolder {
        TextView name, paid, transfers, fair, net;
        VH(@NonNull View v) {
            super(v);
            name = v.findViewById(R.id.tvName);
            paid = v.findViewById(R.id.tvPaid);
            transfers = v.findViewById(R.id.tvTransfers);
            fair = v.findViewById(R.id.tvFair);
            net = v.findViewById(R.id.tvNet);
        }
    }

    @NonNull @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_balance, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH h, int i) {
        BalanceCalculator.PersonBalance b = data.get(i);
        h.name.setText(b.name);
        h.paid.setText("Paid: " + df.format(b.paid));
        h.transfers.setText("Transfers: +" + df.format(b.incoming) + " / -" + df.format(b.outgoing));
        h.fair.setText("Fair share: " + df.format(b.fairShare));
        String sign = b.net >= 0 ? "Receive " : "Pay ";
        h.net.setText(sign + df.format(Math.abs(b.net)));
        h.net.setTextColor(b.net >= 0 ? 0xFF2E7D32 : 0xFFC62828);
    }

    @Override public int getItemCount() { return data.size(); }

    public void submit(List<BalanceCalculator.PersonBalance> items) {
        data.clear();
        if (items != null) data.addAll(items);
        notifyDataSetChanged();
    }
}
