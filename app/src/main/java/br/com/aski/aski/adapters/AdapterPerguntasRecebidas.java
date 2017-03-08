package br.com.aski.aski.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import br.com.aski.aski.POJO.Pergunta;
import br.com.aski.aski.R;

/**
 * Created by denisvcosta on 18/02/2017.
 */

public class AdapterPerguntasRecebidas extends
        RecyclerView.Adapter<AdapterPerguntasRecebidas.MyViewHolder> {

    private ArrayList<Pergunta> perguntas;
    private LayoutInflater inflater;

    public AdapterPerguntasRecebidas(ArrayList<Pergunta> perguntas, Context context){
        this.perguntas = perguntas;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.layout_pergunta_recebida,parent,false);

        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Pergunta pergunta = perguntas.get(position);

        holder.nome_usuario.setText(pergunta.getNome_usuario());
        holder.data_pergunta.setText(pergunta.getData());
        holder.pontos.setText(pergunta.getPontos());
        holder.pergunta.setText(pergunta.getPergunta());
        holder.foto_usuario.setImageResource(pergunta.getFoto_perfil());

    }

    @Override
    public int getItemCount() {
        return perguntas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nome_usuario;
        TextView data_pergunta;
        TextView pontos;
        TextView pergunta;
        CircularImageView foto_usuario;

        public MyViewHolder(View itemView) {
            super(itemView);
            nome_usuario = (TextView) itemView.findViewById(R.id.nome_usuario_item_responder);
            data_pergunta = (TextView) itemView.findViewById(R.id.data_pergunta_item_responder);
            pontos = (TextView) itemView.findViewById(R.id.pontos_item_responder);
            pergunta = (TextView) itemView.findViewById(R.id.pergunta_item_responder);
            foto_usuario = (CircularImageView) itemView.findViewById(R.id.foto_perfil_item_responder);
        }
    }

}
