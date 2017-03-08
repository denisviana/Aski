package br.com.aski.aski.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import br.com.aski.aski.POJO.Pergunta;
import br.com.aski.aski.R;
import br.com.aski.aski.adapters.AdapterPerguntasRecebidas;

/**
 * Created by denisvcosta on 18/02/2017.
 */

public class ResponderFragment extends Fragment {

    private RecyclerView list_perguntas_recebidas;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView;

        rootView = inflater.inflate(R.layout.responder_fragment,container,false);

        list_perguntas_recebidas = (RecyclerView) rootView.findViewById(R.id.list_perguntas_recebida);
        list_perguntas_recebidas.setHasFixedSize(true);

        ArrayList<Pergunta> perguntas = new ArrayList<>();

        Pergunta pergunta = new Pergunta();
        pergunta.setNome_usuario("Denis Viana");
        pergunta.setData("16/02/2017");
        pergunta.setPontos("15 PONTOS");
        pergunta.setPergunta("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse tellus mauris, " +
                "facilisis sed elementum eget, euismod in ante. Pellentesque porta et elit et vestibulum.");
        pergunta.setFoto_perfil(R.drawable.perfil);
        perguntas.add(pergunta);

        pergunta = new Pergunta();
        pergunta.setNome_usuario("Ned Stark");
        pergunta.setData("18/02/2017");
        pergunta.setPontos("20 PONTOS");
        pergunta.setPergunta("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse tellus mauris, " +
                "facilisis sed elementum eget, euismod in ante. Pellentesque porta et elit et vestibulum.");
        pergunta.setFoto_perfil(R.drawable.nedstark);
        perguntas.add(pergunta);

        pergunta = new Pergunta();
        pergunta.setNome_usuario("Jon Snow");
        pergunta.setData("10/02/2017");
        pergunta.setPontos("20 PONTOS");
        pergunta.setPergunta("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse tellus mauris, " +
                "facilisis sed elementum eget, euismod in ante. Pellentesque porta et elit et vestibulum.");
        pergunta.setFoto_perfil(R.drawable.jonsnow);
        perguntas.add(pergunta);

        pergunta = new Pergunta();
        pergunta.setNome_usuario("HODOR");
        pergunta.setData("17/02/2017");
        pergunta.setPontos("15 PONTOS");
        pergunta.setPergunta("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse tellus mauris, " +
                "facilisis sed elementum eget, euismod in ante. Pellentesque porta et elit et vestibulum.");
        pergunta.setFoto_perfil(R.drawable.hodor);
        perguntas.add(pergunta);

        AdapterPerguntasRecebidas adapter = new AdapterPerguntasRecebidas(perguntas,getActivity());

        list_perguntas_recebidas.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        list_perguntas_recebidas.setLayoutManager(layoutManager);

        return rootView;
    }
}
