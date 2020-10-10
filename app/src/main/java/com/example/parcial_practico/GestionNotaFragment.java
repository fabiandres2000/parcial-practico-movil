package com.example.parcial_practico;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GestionNotaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GestionNotaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GestionNotaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RnotaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GestionNotaFragment newInstance(String param1, String param2) {
        GestionNotaFragment fragment = new GestionNotaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    Spinner lista;
    EditText notatallercorte1,notaquizcorte1,notapteoricocorte1,notappracticocorte1,porcentajetallercorte1,
    porcentajequizcorte1,porcentajepteoricocorte1,porcentajeppracticocorte1;

    EditText notatallercorte2,notaquizcorte2,notapteoricocorte2,notappracticocorte2,porcentajetallercorte2,
            porcentajequizcorte2,porcentajepteoricocorte2,porcentajeppracticocorte2;

    EditText notatallercorte3,notaquizcorte3,notapteoricocorte3,notappracticocorte3,porcentajetallercorte3,
            porcentajequizcorte3,porcentajepteoricocorte3,porcentajeppracticocorte3;

    ArrayList<String> listaInformacion;
    ArrayList<Asignatura> asignaturas;
    databaseHelper con;

    Button  guardar_nota,mostrar,mostrar2,mostrar3;
    LinearLayout oculto_,oculto2_,oculto3_;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_rnota, container, false);
        lista = view.findViewById(R.id.spiner_asignatura);
        guardar_nota = view.findViewById(R.id.guardar_nota);
        notatallercorte1 = view.findViewById(R.id.notatallercorte1);
        notatallercorte2 = view.findViewById(R.id.notatallercorte2);
        notatallercorte3 = view.findViewById(R.id.notatallercorte3);
        mostrar = view.findViewById(R.id.mostrar);
        mostrar2 = view.findViewById(R.id.mostrar2);
        mostrar3 = view.findViewById(R.id.mostrar3);
        oculto_ = view.findViewById(R.id.oculto);
        oculto2_ = view.findViewById(R.id.oculto2);
        oculto3_ = view.findViewById(R.id.oculto3);

        notaquizcorte1 = view.findViewById(R.id.notaquizcorte1);
        notaquizcorte2 = view.findViewById(R.id.notaquizcorte2);
        notaquizcorte3 = view.findViewById(R.id.notaquizcorte3);

        notapteoricocorte1 = view.findViewById(R.id.notateoricocorte1);
        notapteoricocorte2 = view.findViewById(R.id.notateoricocorte2);
        notapteoricocorte3 = view.findViewById(R.id.notateoricocorte3);

        notappracticocorte1 = view.findViewById(R.id.notapracticocorte1);
        notappracticocorte2 = view.findViewById(R.id.notapracticocorte2);
        notappracticocorte3 = view.findViewById(R.id.notapracticocorte3);


        porcentajetallercorte1 = view.findViewById(R.id.porcentajetallercorte1);
        porcentajetallercorte2 = view.findViewById(R.id.porcentajetallercorte2);
        porcentajetallercorte3 = view.findViewById(R.id.porcentajetallercorte3);


        porcentajequizcorte1 = view.findViewById(R.id.porcentajequizcorte1);
        porcentajequizcorte2 = view.findViewById(R.id.porcentajequizcorte2);
        porcentajequizcorte3 = view.findViewById(R.id.porcentajequizcorte3);

        porcentajepteoricocorte1 = view.findViewById(R.id.porcentajeteoricocorte1);
        porcentajepteoricocorte2 = view.findViewById(R.id.porcentajeteoricocorte2);
        porcentajepteoricocorte3 = view.findViewById(R.id.porcentajeteoricocorte3);

        porcentajeppracticocorte1 = view.findViewById(R.id.porcentajepracticocorte1);
        porcentajeppracticocorte2 = view.findViewById(R.id.porcentajepracticocorte2);
        porcentajeppracticocorte3 = view.findViewById(R.id.porcentajepracticocorte3);




        con = new databaseHelper(this.getContext(),"parcial",null,1);
        llenarspinner();

        ArrayAdapter adaptador = new ArrayAdapter(this.getContext(),R.layout.support_simple_spinner_dropdown_item,listaInformacion);
        lista.setAdapter(adaptador);

        guardar_nota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcularnota();
            }
        });

        mostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oculto_.setVisibility(View.VISIBLE);
                oculto2_.setVisibility(view.GONE);
                oculto3_.setVisibility(view.GONE);
            }
        });

        mostrar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oculto_.setVisibility(View.GONE);
                oculto2_.setVisibility(view.VISIBLE);
                oculto3_.setVisibility(view.GONE);
            }
        });

        mostrar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oculto_.setVisibility(View.GONE);
                oculto2_.setVisibility(view.GONE);
                oculto3_.setVisibility(view.VISIBLE);
            }
        });
        return  view;

    }

    public void  llenarspinner(){
        SQLiteDatabase db = con.getReadableDatabase();
        Asignatura asignatura = null;
        asignaturas = new ArrayList<Asignatura>();
        Cursor cursor = db.rawQuery("SELECT * FROM asignatura",null);

        while (cursor.moveToNext()){
            asignatura = new Asignatura();
            asignatura.setCodigo(cursor.getString(1));
            asignatura.setNombre(cursor.getString(2));
            asignaturas.add(asignatura);
        }
        obtenerLista();
        db.close();
    }

    public  void obtenerLista(){
        listaInformacion = new ArrayList<String>();
        for (int i = 0; i<asignaturas.size();i++){
            listaInformacion.add(asignaturas.get(i).Nombre);
        }
    }

    public void calcularnota(){
        int porcentajecorte1 = (Integer.parseInt(porcentajetallercorte1.getText().toString())+Integer.parseInt(porcentajequizcorte1.getText().toString())+Integer.parseInt(porcentajepteoricocorte1.getText().toString())+Integer.parseInt(porcentajeppracticocorte1.getText().toString()));
        int porcentajecorte2 = (Integer.parseInt(porcentajetallercorte2.getText().toString())+Integer.parseInt(porcentajequizcorte2.getText().toString())+Integer.parseInt(porcentajepteoricocorte2.getText().toString())+Integer.parseInt(porcentajeppracticocorte2.getText().toString()));
        int porcentajecorte3 = (Integer.parseInt(porcentajetallercorte3.getText().toString())+Integer.parseInt(porcentajequizcorte3.getText().toString())+Integer.parseInt(porcentajepteoricocorte3.getText().toString())+Integer.parseInt(porcentajeppracticocorte3.getText().toString()));
        //verificar si los porcentajes de cada corte dan 100
        if (porcentajecorte1==100 && porcentajecorte2 ==100 && porcentajecorte3==100){
            //verificar que todas las notas tengan datos
            if (notatallercorte1.getText().toString().equals("")||notaquizcorte1.getText().toString().equals("")||notapteoricocorte1.getText().toString().equals("")||notappracticocorte1.getText().toString().equals("")||
                    notatallercorte2.getText().toString().equals("")||notaquizcorte2.getText().toString().equals("")||notapteoricocorte2.getText().toString().equals("")||notappracticocorte2.getText().toString().equals("")||
                    notatallercorte3.getText().toString().equals("")||notaquizcorte3.getText().toString().equals("")||notapteoricocorte3.getText().toString().equals("")||notappracticocorte3.getText().toString().equals("")) {

                new SweetAlertDialog(this.getContext())
                        .setTitleText("LLene todas las notas; si no existen notas de calificacion para un item coloque (0)")
                        .show();

            }else {
                //verificar que las notas esten entre 0 y 5
                double notat1= Double.parseDouble(notatallercorte1.getText().toString());
                double notat2 = Double.parseDouble(notatallercorte2.getText().toString());
                double notat3 = Double.parseDouble(notatallercorte3.getText().toString());
                double notaq1 = Double.parseDouble(notaquizcorte1.getText().toString());
                double notaq2 = Double.parseDouble(notaquizcorte2.getText().toString());
                double notaq3 = Double.parseDouble(notaquizcorte3.getText().toString());
                double notapt1 = Double.parseDouble(notapteoricocorte1.getText().toString());
                double notapt2 = Double.parseDouble(notapteoricocorte2.getText().toString());
                double notapt3 = Double.parseDouble(notapteoricocorte3.getText().toString());
                double notapp1 = Double.parseDouble(notappracticocorte1.getText().toString());
                double notapp2 = Double.parseDouble(notappracticocorte2.getText().toString());
                double notapp3 = Double.parseDouble(notappracticocorte3.getText().toString());

                if ((notat1>=0 && notat1<=5)&&(notat2>=0 && notat2<=5)&&(notat3>=0 && notat3<=5)&&
                        (notaq1>=0 && notaq1<=5)&&(notaq2>=0 && notaq2<=5)&&(notaq3>=0 && notaq3<=5)&&
                        (notapt1>=0 && notapt1<=5)&&(notapt2>=0 && notapt2<=5)&&(notapt3>=0 && notapt3<=5)&&
                        (notapp1>=0 && notapp1<=5)&&(notapp2>=0 && notapp2<=5)&&(notapp3>=0 && notapp3<=5)){

                    //corte 1 definitiva
                    double taller1 = Integer.parseInt(porcentajetallercorte1.getText().toString()) * Double.parseDouble(notatallercorte1.getText().toString());
                    double quiz1 = Integer.parseInt(porcentajequizcorte1.getText().toString()) * Double.parseDouble(notaquizcorte1.getText().toString());
                    double parcialteorico1 = Integer.parseInt(porcentajepteoricocorte1.getText().toString()) * Double.parseDouble(notapteoricocorte1.getText().toString());
                    double parcialpractico1 = Integer.parseInt(porcentajeppracticocorte1.getText().toString()) * Double.parseDouble(notappracticocorte1.getText().toString());
                    double corte1 = ((taller1 + quiz1 + parcialteorico1 + parcialpractico1)) / 100;

                    //corte 2 definitiva
                    double taller2 = Integer.parseInt(porcentajetallercorte2.getText().toString()) * Double.parseDouble(notatallercorte2.getText().toString());
                    double quiz2 = Integer.parseInt(porcentajequizcorte2.getText().toString()) * Double.parseDouble(notaquizcorte2.getText().toString());
                    double parcialteorico2 = Integer.parseInt(porcentajepteoricocorte2.getText().toString()) * Double.parseDouble(notapteoricocorte2.getText().toString());
                    double parcialpractico2 = Integer.parseInt(porcentajeppracticocorte2.getText().toString()) * Double.parseDouble(notappracticocorte2.getText().toString());
                    double corte2 = ((taller2 + quiz2 + parcialteorico2 + parcialpractico2)) / 100;

                    //corte 3 definitiva
                    double taller3 = Integer.parseInt(porcentajetallercorte3.getText().toString()) * Double.parseDouble(notatallercorte3.getText().toString());
                    double quiz3 = Integer.parseInt(porcentajequizcorte3.getText().toString()) * Double.parseDouble(notaquizcorte3.getText().toString());
                    double parcialteorico3 = Integer.parseInt(porcentajepteoricocorte3.getText().toString()) * Double.parseDouble(notapteoricocorte3.getText().toString());
                    double parcialpractico3 = Integer.parseInt(porcentajeppracticocorte3.getText().toString()) * Double.parseDouble(notappracticocorte3.getText().toString());
                    double corte3 = ((taller3 + quiz3 + parcialteorico3 + parcialpractico3)) / 100;

                    //definitiva del semestre
                    double definitiva = corte1*0.3 + corte2*0.3+ corte3*0.3;
                    //guardo en la tabla notas
                    guardar_nota(lista.getSelectedItem().toString(),corte1,corte2,corte3,definitiva);

                }else{
                    new SweetAlertDialog(this.getContext())
                            .setTitleText("verifique las notas Ingresadas: estas deben de estar entre 0 y 5")
                            .show();
                }
            }
        }else{
            new SweetAlertDialog(this.getContext())
                    .setTitleText("Los porcentajes de cada corte deben sumar 100%")
                    .show();
        }
    }

    public void guardar_nota(String asignatura,double corte1,double corte2,double corte3,double notafinal){
            databaseHelper con = new databaseHelper(this.getContext(),"parcial",null,1);
            SQLiteDatabase db = con.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("ASIGNATURA",asignatura);
            values.put("CORTE1",corte1);
            values.put("CORTE2",corte2);
            values.put("CORTE3",corte3);
            values.put("NOTA",notafinal);
            Long id_resultado = db.insert("nota","ASIGNATURA",values);

            new SweetAlertDialog(this.getContext()).setTitleText("Notas guardada correctamente!   Corte1: "+corte1+"    corte2: "+
                    corte2+"   corte3: "+corte3+"    definitiva: "+notafinal).show();
            db.close();

    }
}