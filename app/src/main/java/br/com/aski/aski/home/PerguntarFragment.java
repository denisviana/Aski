package br.com.aski.aski.home;



import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import br.com.aski.aski.R;

/**
 * Created by denisvcosta on 18/02/2017.
 */

public class PerguntarFragment extends Fragment{


    private Spinner spinner_pontos_home;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        View rootView = inflater.inflate(R.layout.fragment_perguntar, container, false);

        List<String> array_pontos = new ArrayList<>();

        for(int i=10;i<=100;i+=5){
            array_pontos.add(i+" Pontos");
        }

        spinner_pontos_home = (Spinner) rootView.findViewById(R.id.spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                R.layout.layout_spinner,array_pontos);

        adapter.setDropDownViewResource(R.layout.layout_spinner_dropdown);
        spinner_pontos_home.setAdapter(adapter);

        return rootView;
    }

}
