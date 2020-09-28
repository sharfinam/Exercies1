package com.example.gslc2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

import java.util.HashMap;
import java.util.Vector;

public class RegisterActivity extends AppCompatActivity  implements View.OnClickListener {


    EditText usernameTxt;
    RadioGroup genderGroup;
    RadioButton femaleGroup, maleGroup;
    Switch chefSwitch;
    Button resetBtn, RegisBtn;
    LinearLayout checkBoxContainer;

    Vector<Object> cuisines;
    Vector<Object> selectedCuisine;
    Vector<CheckBox> checkBoxes;



    //untuk create combo box dan masukin ke dalam Layout
    public void addCuisineIntoLayout(Vector<Object> cuisines, LinearLayout ll){
        CheckBox cb;

        for (int i=0; i < cuisines.size(); i++){
            cb = new CheckBox(this);
            cb.setText(cuisines.get(i).toString());
            addOnCheckListener(cb);
            ll.addView(cb);
            checkBoxes.add(cb);
        }
    }

    //untuk List untuk isi combo box
    public void addCuisines(Vector<Object> cuisines){
        cuisines.add("Japanese Cuisene");
        cuisines.add("Korean Cuisene");
        cuisines.add("Chinese Cuisene");
        cuisines.add("Other Cuisene");
    }

    // untuk memebuat setiap combo box yang ada memiliki listener
    public void addOnCheckListener(final CheckBox cb){
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()){
                    selectedCuisine.add(cb.getText());
                }else{
                    for (int i=0; i<selectedCuisine.size(); i++){
                        if (selectedCuisine.get(i).equals(cb.getText())){
                            selectedCuisine.remove(i);
                            break;
                        }
                    }
                }
            }
        });
    }
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernameTxt = findViewById(R.id.txt_Username);
        genderGroup = findViewById(R.id.gender_gr);
        femaleGroup = findViewById(R.id.female_gr);
        maleGroup = findViewById(R.id.male_gr);
        chefSwitch = findViewById(R.id.switch_chef);
        resetBtn = findViewById(R.id.btn_Reset);
        RegisBtn = findViewById(R.id.btn_Regist);
        checkBoxContainer = findViewById(R.id.container_checkBox);

        cuisines = new Vector<>();
        selectedCuisine = new Vector<>();
        checkBoxes = new Vector<>();

        addCuisines(cuisines);
        addCuisineIntoLayout(cuisines, checkBoxContainer);

        RegisBtn.setOnClickListener(this);
        resetBtn.setOnClickListener(this);
    }


    public void register(){

    }

    public void reset(){
        usernameTxt.setText("");
        genderGroup.clearCheck();
        chefSwitch.setChecked(false);
        for (int i=0; i<checkBoxes.size(); i++){
            checkBoxes.get(i).setChecked(false);
        }

    }

    //untuk memunculkan dialog
    public void createDialog(HashMap<String, String> inputs){
        AlertDialog.Builder alerDialog = new AlertDialog.Builder(this);
        alerDialog.setTitle("are you sure the information is Correct?");
        alerDialog.setMessage("" +
                "Username =" + inputs.get("username") + "\n" +
                "Gender =" + inputs.get("gender") + "\n" +
                "Role =" + inputs.get("role") + "\n" +
                "Preferences =" + inputs.get("cuisines") + "\n" +
                "");
        alerDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //register
                register();

            }
        });

        alerDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //reset Form
                reset();
            }
        });

        alerDialog.show();
    }
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_Regist:
                Editable username = usernameTxt.getText();
                String gender = "";
                if (femaleGroup.isChecked()) {
                    gender = "Female";
                }else{
                    gender = "Male";
                }

                String role;
                if (chefSwitch.isChecked()){
                    role = "Chef";
                }else {
                    role = " ";
                }

                StringBuilder sb = new StringBuilder();
                for (int i=0; i<selectedCuisine.size(); i++){
                    if (i == selectedCuisine.size() -1) {
                        sb.append(selectedCuisine.get(i).toString());
                        break;
                    }else{
                        sb.append(selectedCuisine.get(i).toString() + ", ");
                    }

                }

                //create HashMap
                HashMap<String, String>  inputs = new HashMap<>();
                inputs.put("username", username.toString());
                inputs.put("gender", gender);
                inputs.put("role", role);
                inputs.put("cuisines", sb.toString());

                //create alert dialog
                createDialog(inputs);

                break;

            case R.id.btn_Reset:
                reset();
                break;


        }
    }
}
