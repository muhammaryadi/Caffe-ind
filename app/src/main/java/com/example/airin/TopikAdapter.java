package com.example.airin;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TopikAdapter extends RecyclerView.Adapter<TopikAdapter.ViewHolder> {

    private List<TopikModel> topikList;
    private Context context;
    private TopikAdapterListener listener;

    public void setListener(TopikAdapterListener listener) {
        this.listener = listener;
    }

    public interface TopikAdapterListener {
        void onReplyButtonClick(String idTopik, String idPengirim, String nama, String pesanTopik, String waktu);
    }

    public TopikAdapter(List<TopikModel> topikList, Context context) {
        this.topikList = topikList;
        this.context = context;
    }

    public void setTopikList(List<TopikModel> topikList) {
        this.topikList = topikList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.topik_konsumen, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TopikModel topik = topikList.get(position);

        // Set data ke tampilan
        holder.tvNama.setText(topik.getIdPengirim());
        holder.tvTopik.setText(topik.getPesanTopik());
        holder.tvtanggal.setText(topik.getWaktu());

        // Handle button click event jika diperlukan
        holder.btnReply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onReplyButtonClick(
                            topik.getId(),
                            topik.getIdPengirim(),
                            topik.getNama(),
                            topik.getPesanTopik(),
                            topik.getWaktu()
                    );
                } else {
                    Log.e("TopikAdapter", "Listener kosong (null)");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return topikList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView profileImage;
        TextView tvNama, tvTopik, tvtanggal;
        Button btnReply;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tvnama);
            tvTopik = itemView.findViewById(R.id.tvtopik);
            tvtanggal = itemView.findViewById(R.id.tvtanggaltopik);
            btnReply = itemView.findViewById(R.id.reply);
        }
    }
}
