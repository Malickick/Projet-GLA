package com.example.trouversoiree;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

//
public class ChoixParamsBarFrag extends android.support.v4.app.Fragment {

    public ArrayList<String> listeParamsBar = new ArrayList<String>();
    boolean vin = false ;
    boolean biere = false;
    boolean chicha = false;
    boolean cocktail = false;
    CheckBox checkBoxVin;
    CheckBox checkBoxBiere;
    CheckBox checkBoxCocktails;
    CheckBox checkBoxChicha;

    public ChoixParamsBarFrag() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root_view = inflater.inflate(R.layout.activity_choix_params_bar, container, false);

        checkBoxBiere = (CheckBox) root_view.findViewById(R.id.checkBox5);
        checkBoxBiere.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkBoxBiere.isChecked() && !biere)  {
                    listeParamsBar.add("bière");
                    biere = true;
                } else if(!checkBoxVin.isChecked() && biere ) {
                    int index = listeParamsBar.indexOf("bière");
                    listeParamsBar.remove(index);
                    biere = false;
                }
            }
        });

        checkBoxVin = (CheckBox) root_view.findViewById(R.id.checkBox6);
        checkBoxVin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkBoxVin.isChecked() && !vin)  {
                    listeParamsBar.add("vin");
                    vin = true;
                } else if(!checkBoxVin.isChecked() && vin ) {
                    int index = listeParamsBar.indexOf("vin");
                    listeParamsBar.remove(index);
                    vin = false;
                }
            }
        });

        checkBoxCocktails = (CheckBox) root_view.findViewById(R.id.checkBox7);
        checkBoxCocktails.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkBoxCocktails.isChecked() && !cocktail)  {
                    listeParamsBar.add("cocktail");
                    cocktail = true;
                } else if(!checkBoxCocktails.isChecked() && cocktail ) {
                    int index = listeParamsBar.indexOf("cocktail");
                    listeParamsBar.remove(index);
                    cocktail = false;
                }
            }
        });

        checkBoxChicha = (CheckBox) root_view.findViewById(R.id.checkBox8);
        checkBoxChicha.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkBoxChicha.isChecked() && !chicha)  {
                    listeParamsBar.add("chicha");
                    chicha = true;
                } else if(!checkBoxChicha.isChecked() && chicha ) {
                    int index = listeParamsBar.indexOf("chicha");
                    listeParamsBar.remove(index);
                    chicha = false;
                }
            }
        });

        Button valBar_Button = (Button) root_view.findViewById(R.id.button_val_bar);
        valBar_Button.setOnClickListener(new View.OnClickListener() {
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
