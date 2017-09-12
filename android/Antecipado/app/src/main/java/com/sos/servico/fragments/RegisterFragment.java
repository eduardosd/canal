package com.sos.servico.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.gc.materialdesign.views.ButtonRectangle;
import com.sos.servico.R;
import com.sos.servico.rest.RestClient;
import com.sos.servico.rest.pojo.Cliente;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {

    private EditText nome;
    private EditText email;
    private EditText password;
    private EditText sexo;
    private ButtonRectangle button;


    public RegisterFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        findViewsFromXml();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO - validar se campos validos
                new RestClient().getApiService().registerClient(nome.getText().toString(), email.getText().toString(), password.getText().toString(), sexo.getText().toString(), new Callback<Cliente>() {
                    @Override
                    public void success(Cliente cliente, Response response) {
                        cliente.setSenha(password.getText().toString());

                        Fragment fragment = new DashboardFragment(cliente);
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.container, fragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
                });

            }
        });
    }

    private void findViewsFromXml() {
        nome = (EditText) getView().findViewById(R.id.register_name);
        email = (EditText) getView().findViewById(R.id.register_email);
        password = (EditText) getView().findViewById(R.id.register_password);
        sexo = (EditText) getView().findViewById(R.id.register_sexo);
        button = (ButtonRectangle) getView().findViewById(R.id.register_button);
    }


}
