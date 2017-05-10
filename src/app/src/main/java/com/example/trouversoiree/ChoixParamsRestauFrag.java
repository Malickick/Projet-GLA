package com.example.trouversoiree;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.util.ArrayList;

public class ChoixParamsRestauFrag extends android.support.v4.app.Fragment {

    boolean fastfood = false ;
    boolean thai = false;
    boolean chinois = false;
    boolean japonais = false;
    boolean francais = false ;
    boolean latin = false;
    boolean italien = false;
    boolean indien = false ;
    boolean africain = false;
    boolean oriental = false;
    boolean americain = false;
    CheckBox checkBoxFastfood;
    CheckBox checkBoxThai;
    CheckBox checkBoxChinois;
    CheckBox checkBoxJap;
    CheckBox checkBoxIndien;
    CheckBox checkBoxAfro;
    CheckBox checkBoxOriental;
    CheckBox checkBoxAmericain;
    CheckBox checkBoxFr;
    CheckBox checkBoxLatino;
    CheckBox checkBoxItalien;

    public ChoixParamsRestauFrag() {
    }

    ArrayList<String> listeParamsRestau = new ArrayList<String>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root_view = inflater.inflate(R.layout.activity_choix_param_restau, container, false);
        Button valBar_Button = (Button) root_view.findViewById(R.id.button_val_restau);
        valBar_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.support.v4.app.Fragment fragment = null;
                fragment = new ChoixMoyTr();

                android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
                android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.choixtr, fragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });

        checkBoxFastfood = (CheckBox) root_view.findViewById(R.id.checkBoxfast);
        checkBoxFastfood.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkBoxFastfood.isChecked() && !fastfood)  {
                    listeParamsRestau.add("fast-food");
                    fastfood = true;
                } else if(!checkBoxFastfood.isChecked() && fastfood ) {
                    int index = listeParamsRestau.indexOf("fast-food");
                    listeParamsRestau.remove(index);
                    fastfood = false;
                }
            }
        });

        checkBoxThai = (CheckBox) root_view.findViewById(R.id.checkBoxthai);
        checkBoxThai.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkBoxThai.isChecked() && !thai)  {
                    listeParamsRestau.add("thai");
                    thai = true;
                } else if(!checkBoxThai.isChecked() && thai ) {
                    int index = listeParamsRestau.indexOf("thai");
                    listeParamsRestau.remove(index);
                    thai = false;
                }
            }
        });

        checkBoxChinois = (CheckBox) root_view.findViewById(R.id.checkBoxchinois);
        checkBoxChinois.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkBoxChinois.isChecked() && !chinois)  {
                    listeParamsRestau.add("chinois");
                    chinois = true;
                } else if(!checkBoxChinois.isChecked() && chinois ) {
                    int index = listeParamsRestau.indexOf("chinois");
                    listeParamsRestau.remove(index);
                    chinois = false;

                }
            }
        });

        checkBoxJap = (CheckBox) root_view.findViewById(R.id.checkBoxjap);
        checkBoxJap.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkBoxJap.isChecked() && !japonais)  {
                    listeParamsRestau.add("japonais");
                    japonais = true;
                } else if(!checkBoxJap.isChecked() && japonais ) {
                    int index = listeParamsRestau.indexOf("japonais");
                    listeParamsRestau.remove(index);
                    japonais = false;
                }
            }
        });

        checkBoxIndien = (CheckBox) root_view.findViewById(R.id.checkBoxindien);
        checkBoxIndien.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkBoxIndien.isChecked() && !indien)  {
                    listeParamsRestau.add("indien");
                    indien = true;
                } else if(!checkBoxIndien.isChecked() && indien ) {
                    int index = listeParamsRestau.indexOf("indien");
                    listeParamsRestau.remove(index);
                    indien = false;

                }
            }
        });

        checkBoxAfro = (CheckBox) root_view.findViewById(R.id.checkBoxafro);
        checkBoxAfro.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkBoxAfro.isChecked() && !africain)  {
                    listeParamsRestau.add("africain");
                    africain = true;
                } else if(!checkBoxAfro.isChecked() && africain ) {
                    int index = listeParamsRestau.indexOf("africain");
                    listeParamsRestau.remove(index);
                    africain = false;
                }
            }
        });

        checkBoxOriental = (CheckBox) root_view.findViewById(R.id.checkBoxoriental);
        checkBoxOriental.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkBoxOriental.isChecked() && !oriental)  {
                    listeParamsRestau.add("oriental");
                    oriental = true;
                } else if(!checkBoxOriental.isChecked() && oriental ) {
                    int index = listeParamsRestau.indexOf("oriental");
                    listeParamsRestau.remove(index);
                    oriental = false;
                }
            }
        });

        checkBoxAmericain = (CheckBox) root_view.findViewById(R.id.checkBoxamericain);
        checkBoxAmericain.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkBoxAmericain.isChecked() && !americain)  {
                    listeParamsRestau.add("americain");
                    americain = true;
                } else if(!checkBoxAmericain.isChecked() && americain ) {
                    int index = listeParamsRestau.indexOf("americain");
                    listeParamsRestau.remove(index);
                    americain = false;

                }
            }
        });

        checkBoxFr = (CheckBox) root_view.findViewById(R.id.checkBoxfr);
        checkBoxFr.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkBoxFr.isChecked() && !francais)  {
                    listeParamsRestau.add("français");
                    francais = true;
                } else if(!checkBoxFr.isChecked() && francais ) {
                    int index = listeParamsRestau.indexOf("français");
                    listeParamsRestau.remove(index);
                    francais = false;

                }
            }
        });

        checkBoxLatino = (CheckBox) root_view.findViewById(R.id.checkBoxlatino);
        checkBoxLatino.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkBoxLatino.isChecked() && !latin)  {
                    listeParamsRestau.add("latin");
                    latin = true;
                } else if(!checkBoxThai.isChecked() && latin ) {
                    int index = listeParamsRestau.indexOf("latin");
                    listeParamsRestau.remove(index);
                    latin = false;

                }
            }
        });

        checkBoxItalien = (CheckBox) root_view.findViewById(R.id.checkBoxitalien);
        checkBoxIndien.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkBoxItalien.isChecked() && !italien)  {
                    listeParamsRestau.add("italien");
                    italien = true;
                } else if(!checkBoxItalien.isChecked() && italien ) {
                    int index = listeParamsRestau.indexOf("italien");
                    listeParamsRestau.remove(index);
                    italien = false;
                }
            }
        });

        return root_view;
    }

}
