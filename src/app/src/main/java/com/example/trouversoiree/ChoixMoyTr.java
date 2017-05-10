package com.example.trouversoiree;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Malik on 05/05/2017.
 */

public class ChoixMoyTr extends android.support.v4.app.Fragment {

    public ChoixMoyTr() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root_view = inflater.inflate(R.layout.activity_choix_moyen_tr, container, false);
        Button valVelo_Button = (Button) root_view.findViewById(R.id.button_velo);
        Button valTr_Button = (Button) root_view.findViewById(R.id.button_tr_en_commun);
        Button valVoiture_Button = (Button) root_view.findViewById(R.id.button_voiture);

        valVelo_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.support.v4.app.Fragment fragment = null;
                fragment = new ChoixParamsRestauFrag();

                android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
                android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.choixparamrestau, fragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });

        return root_view;
    }
}
