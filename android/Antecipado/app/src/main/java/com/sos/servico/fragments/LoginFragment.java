package com.sos.servico.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.gc.materialdesign.views.ButtonRectangle;
import com.sos.servico.R;
import com.sos.servico.rest.RestClient;
import com.sos.servico.rest.pojo.Cliente;
import com.sos.servico.util.CacheUtil;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class LoginFragment extends Fragment {

    private EditText loginEmail;
    private EditText loginPassword;
    private ButtonRectangle buttonLogin;
    private ButtonRectangle buttonRegister;
    private View container;

    public static LoginFragment newInstance(String text) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString("default",text);
        fragment.setArguments(args);
        return fragment;
    }

    public LoginFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN|WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        findViewsFromXml();
        buttonRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Fragment fragment = new RegisterFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("TESTE");
                System.out.println("EMAIL: " + loginEmail.getText().toString());
                System.out.println("PASSWORD: " + loginPassword.getText().toString());
                String passwordEnconded = Base64.encodeToString(loginPassword.getText().toString().getBytes(), Base64.DEFAULT);
                System.out.println("ecncoded value is " + passwordEnconded);
                //TODO - Fazer trazer o cartao na mesma chamada do cliente
                new RestClient().getApiService().getClient(loginEmail.getText().toString(), passwordEnconded, new Callback<Cliente>() {
                    @Override
                    public void success(Cliente cliente, Response response) {
                        if(null == cliente){
                            System.out.println("NENHUM");
                            Toast.makeText(getActivity(), R.string.nenhum, Toast.LENGTH_SHORT).show();
                            return;
                        }
                        cliente.setSenha(Base64.encodeToString(loginPassword.getText().toString().getBytes(), Base64.DEFAULT));
                        System.out.println("NOME " + cliente.getNome());
                        System.out.println("CARTAO " + cliente.get_cartao_cache());

                        CacheUtil.store(getActivity(), "nome", cliente.getNome());
                        CacheUtil.store(getActivity(), "email", cliente.getEmail());
                        CacheUtil.store(getActivity(), "sexo", cliente.getSexo());
                        CacheUtil.store(getActivity(), "senha", cliente.getSenha());
                        CacheUtil.store(getActivity(), "id", cliente.getId().toString());
                        CacheUtil.store(getActivity(), "cartao_id", cliente.getCartao_id());
                        CacheUtil.store(getActivity(), "cartao_saldo", cliente.get_cartao_cache().getSaldo());

                        Fragment fragment = new DashboardFragment(cliente);
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.container, fragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();



                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(getActivity(), R.string.nenhum, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    private void findViewsFromXml() {
        loginEmail = (EditText) getView().findViewById(R.id.login_email);
        loginPassword = (EditText) getView().findViewById(R.id.login_password);
        container = getView().findViewById(R.id.container);
        buttonLogin = (ButtonRectangle) getView().findViewById(R.id.login_button);
        buttonRegister = (ButtonRectangle) getView().findViewById(R.id.login_register);
    }
}
