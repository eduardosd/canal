package com.sos.servico.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


import com.gc.materialdesign.views.ButtonRectangle;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.sos.servico.R;
import com.sos.servico.rest.RestClient;
import com.sos.servico.rest.pojo.Cartao;
import com.sos.servico.rest.pojo.Cliente;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment {

    private Cliente cliente;
    private ButtonRectangle button;
    private TextView userName;
    private TextView saldo;

    public DashboardFragment() {
    }

    public DashboardFragment(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        findViewsFromXml();
        if(null == cliente || null == cliente.get_cartao_cache()){
            System.out.println("ERRO");
            return;
        }

        userName.setText(cliente.getNome());
        saldo.setText("Saldo: " + cliente.get_cartao_cache().getSaldo());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator.initiateScan(getActivity());
            }
        });
    }

    private void findViewsFromXml() {
        userName = (TextView) getView().findViewById(R.id.dashboard_user_name);
        saldo = (TextView) getView().findViewById(R.id.dashboard_saldo);
        button = (ButtonRectangle) getView().findViewById(R.id.dashboard_button_put_money);
    }


}
