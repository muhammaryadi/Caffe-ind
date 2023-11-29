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

import com.squareup.picasso.Picasso;

import java.util.List;

public class KopiAdapter extends RecyclerView.Adapter<KopiAdapter.ViewHolder> {

    private List<KopiModel> kopiList;
    private Context context;
    private UserModel userModel;
    private KopiAdapterListener listener;

    public void setListener(KopiAdapterListener listener) {
        this.listener = listener;
    }
    public interface KopiAdapterListener {
        void goToDetailKopi(String idkopi, String namaKopi, String lessSugar, String normalSugar, String extraSugar, String bahanTambahan, String kafein, String lessGlukosa, String normalGlukosa, String extraGlukosa, String image);
    }

    public KopiAdapter(List<KopiModel> kopiList, Context context) {
        this.kopiList = kopiList;
        this.context = context;
    }

        public void setKopiList(List<KopiModel> kopiList) {
            this.kopiList = kopiList;
        }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.kopi_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        KopiModel kopi = kopiList.get(position);

        // Menggunakan Picasso untuk memuat gambar dari URL atau resource
        Picasso.get().load(kopi.getFoto()).into(holder.imageKopi);
        holder.namaKopi.setText(kopi.getNamaKopi());
        holder.penyajianKopi.setText(kopi.getDeskripsiKopi());

        // Handle button click event jika diperlukan
        holder.pilihmenuini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("KopiAdapter", "Pilih Menu Ini Diklik");
                if (listener != null){
                    listener.goToDetailKopi(
                            kopi.getIdKopi(),
                            kopi.getNamaKopi(),
                            kopi.getLessSugar(),
                            kopi.getNormalSugar(),
                            kopi.getExtraSugar(),
                            kopi.getBahanTambahan(),
                            kopi.getKafein(),
                            kopi.getLessGlukosa(),
                            kopi.getNormalGlukosa(),
                            kopi.getExtraGlukosa(),
                            kopi.getFoto()
                    );
                } else {
                    Log.e("KopiAdapter", "Listener kosong (null)");
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return kopiList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageKopi;
        TextView namaKopi;
        TextView penyajianKopi;
        Button pilihmenuini;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageKopi = itemView.findViewById(R.id.imageKopi);
            namaKopi = itemView.findViewById(R.id.namaKopi);
            penyajianKopi = itemView.findViewById(R.id.penyajianKopi);
            pilihmenuini = itemView.findViewById(R.id.pilihmenuini);
        }
    }
}
