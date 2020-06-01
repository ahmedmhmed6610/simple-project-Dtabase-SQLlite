package com.ahmed6610.simpleprojectdatabasesqllite.business;


import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ahmed6610.simpleprojectdatabasesqllite.DatabaseHelper;
import com.ahmed6610.simpleprojectdatabasesqllite.R;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements View.OnClickListener, RecyclerviewAdapter.OnUserClickListener{
    RecyclerView recyclerView;
    EditText edtName,edtAge,edtphone, edtcampnu;
    Button btnSubmit;
    RecyclerView.LayoutManager layoutManager;
    Context context;
    List<PersonBean> listPersonInfo;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context=getActivity();
        recyclerView=view.findViewById(R.id.recyclerview);
        layoutManager=new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        edtName=view.findViewById(R.id.edtname);
        edtcampnu=view.findViewById(R.id.edtcampny);
        edtAge=view.findViewById(R.id.edtage);
        edtphone=view.findViewById(R.id.edtphone);
        btnSubmit=view.findViewById(R.id.btnsubmit);


///////////////
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String name = edtName.getText().toString().trim();
                String age = edtAge.getText().toString().trim();
                String phone = edtphone.getText().toString().trim();

                if (TextUtils.isEmpty(name)){
                    Toast.makeText(context, R.string.Please_name_customer, Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(age)){
                    Toast.makeText(context, R.string.Please_phone_customer, Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(phone)){
                    Toast.makeText(context, R.string.Please_address_customer, Toast.LENGTH_SHORT).show();
                }else {

                    insert_Update();
                }

            }
        });
       ////////////////////////////




    setupRecyclerView();
    }



    private void setupRecyclerView() {
        DatabaseHelper db=new DatabaseHelper(context);
        listPersonInfo=db.selectUserData();

            RecyclerviewAdapter adapter=new RecyclerviewAdapter(context,listPersonInfo,this);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();


    }


    private void insert_Update() {

               String btnStatus=btnSubmit.getText().toString();




        if(btnStatus.equals("حفظ")) {
            Save_Data(); }
        else   if(btnStatus.equals("Save")) {
            Save_Data(); }
        else if(btnStatus.equals("تحديث البيانات")){
            update_Data(); }
        else if(btnStatus.equals("Update Date")){
            update_Data(); }


        setupRecyclerView();
        edtName.setText("");
        edtAge.setText("");
        edtphone.setText("");
        edtcampnu.setText("");
        edtName.setFocusable(true);
        btnSubmit.setText(R.string.save);






    }

    @Override
    public void onClick(View v) {

    }


    public void Save_Data() {
        DatabaseHelper db = new DatabaseHelper(context);
        PersonBean currentPerson = new PersonBean();
        currentPerson.setName(edtName.getText().toString());
        currentPerson.setCampny(edtcampnu.getText().toString());
        currentPerson.setAge(edtAge.getText().toString());
        currentPerson.setPhone(edtphone.getText().toString());
        db.insert(currentPerson);
        Toast.makeText(context, R.string.successfully_Save_Data, Toast.LENGTH_SHORT).show();

    }
    public void update_Data() {
        DatabaseHelper db = new DatabaseHelper(context);
        PersonBean currentPerson = new PersonBean();
        currentPerson.setName(edtName.getText().toString());
        currentPerson.setCampny(edtcampnu.getText().toString());
        currentPerson.setAge(edtAge.getText().toString());
        currentPerson.setPhone(edtphone.getText().toString());
        db.update(currentPerson);
        Toast.makeText(context, R.string.successfully_update_Data, Toast.LENGTH_SHORT).show();

    }


    @Override
    public void onUserClick(PersonBean currentPerson, String action) {
        if(action.equals("Edit")){
            edtName.setText(currentPerson.getName());
//            edtName.setFocusable(false);
            edtcampnu.setText(currentPerson.getCampny());
            edtphone.setText(currentPerson.getPhone());
            edtAge.setText(currentPerson.getAge()+"");
            btnSubmit.setText(R.string.update_Data);
        }
        if(action.equals("Delete")){
            DatabaseHelper db=new DatabaseHelper(context);
            db.delete(currentPerson.getName());
            setupRecyclerView();
        }
    }
}
