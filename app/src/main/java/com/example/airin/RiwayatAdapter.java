package com.example.airin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RiwayatAdapter extends RecyclerView.Adapter<RiwayatAdapter.ViewHolder> {

    private List<RiwayatModel> riwayatList;
    private Context context;
    private RiwayatModel riwayatModel;
    private RiwayatAdapterListener listener;

    public void setListener(RiwayatAdapter.RiwayatAdapterListener listener) {this.listener = listener;
    }
    public interface RiwayatAdapterListener {
        void goTo(String id_konsumsi, String id_konsumen, String id_kopi, String quantity,
                  String kafein, String glukosa, String sugar, String waktu,
                  String bahan_tambahan);
    }

    public RiwayatAdapter(Context context, List<RiwayatModel> riwayatList) {
        this.context = context;
        this.riwayatList = riwayatList;
    }
    public void setAdapterList(List<RiwayatModel> riwayatList) {
        this.riwayatList = riwayatList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.riwayat_konsumsi, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RiwayatModel riwayatModel = riwayatList.get(position);
        // Set data to views
        holder.tvquantity.setText(riwayatModel.getQuantity());
        holder.tvkafein.setText(riwayatModel.getKafein());
        holder.tvglukosa.setText(riwayatModel.getGlukosa());
        holder.tvsugar.setText(riwayatModel.getSugar());
        holder.tvtanggal.setText(riwayatModel.getWaktu());

    }

    @Override
    public int getItemCount() {
        return riwayatList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // Declare your views here
        TextView tvnama;
        TextView tvserve;
        TextView tvquantity;
        TextView tvkafein;
        TextView tvglukosa;
        TextView tvsugar;
        TextView tvtanggal;
        TextView bahanTambahanTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

//            // Initialize your views here
//            idKonsumenTextView = itemView.findViewById(R.id.idKonsumenTextView);
//            idKopiTextView = itemView.findViewById(R.id.idKopiTextView);
//            quantityTextView = itemView.findViewById(R.id.quantityTextView);
//            kafeinTextView = itemView.findViewById(R.id.kafeinTextView);
//            glukosaTextView = itemView.findViewById(R.id.glukosaTextView);
//            sugarTextView = itemView.findViewById(R.id.sugarTextView);
//            waktuTextView = itemView.findViewById(R.id.waktuTextView);
//            bahanTambahanTextView = itemView.findViewById(R.id.bahanTambahanTextView);
        }
    }
}
