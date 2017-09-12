package com.sos.servico;


import android.animation.ObjectAnimator;
import android.app.Activity;

import android.content.Intent;

import android.os.Bundle;

import android.support.v4.app.Fragment;

import android.view.MenuItem;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.sos.servico.fragments.DashboardFragment;
import com.sos.servico.fragments.LoginFragment;
import com.sos.servico.rest.RestClient;
import com.sos.servico.rest.pojo.Cartao;
import com.sos.servico.rest.pojo.Cliente;
import com.sos.servico.util.CacheUtil;
import com.squareup.picasso.Picasso;

import it.neokree.googlenavigationdrawer.GAccount;
import it.neokree.googlenavigationdrawer.GAccountListener;
import it.neokree.googlenavigationdrawer.GSection;
import it.neokree.googlenavigationdrawer.GoogleNavigationDrawer;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends GoogleNavigationDrawer implements GAccountListener {

    private boolean hasResults;
    private GSection searchSection;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void init(Bundle bundle) {
        FacebookSdk.sdkInitialize(getApplicationContext());
        FacebookSdk.setApplicationId("536346506461027");
        setAccountListener(this);
        GAccount account = new GAccount(CacheUtil.read(this,"name","Bem vindo"),CacheUtil.read(this, "email", "-"),this.getResources().getDrawable(R.drawable.default_profile));
        this.addAccount(account);
        this.setAccountListener(this);
        createSections(CacheUtil.read(this, "name", "-").equals("-"));
        //setupUI(findViewById(R.id.parent));
    }


    @Override
    protected void onStart() {
        super.onStart();
        clearSections();
        createSections(CacheUtil.read(this, "nome", "-").equals("-"));
    }

    private void createSections(boolean withouLogin) {
        //TODO - pegar cliente da sessao
        if(withouLogin){
            this.addSection(this.newSection("Login", this.getResources().getDrawable(R.drawable.search_icon), new LoginFragment()));
        }else{
            System.out.println("NOME: " + CacheUtil.read(this,"nome", "-"));
            Cliente cliente = new Cliente();
            cliente.setNome(CacheUtil.read(this,"nome", "-"));
            cliente.setEmail(CacheUtil.read(this,"email", "-"));
            cliente.setSenha(CacheUtil.read(this,"senha", "-"));
            Cartao cartao = new Cartao();
            cartao.setId(CacheUtil.read(this,"cartao_id", "-"));
            cartao.setSaldo(CacheUtil.read(this,"cartao_saldo", "-"));
            cliente.set_cartao_cache(cartao);

            this.addSection(this.newSection("Dashboard", this.getResources().getDrawable(R.drawable.search_icon), new DashboardFragment(cliente)));
            this.addSection(this.newSection("Dashboard", this.getResources().getDrawable(R.drawable.search_icon), new DashboardFragment(cliente)));
            this.addSection(this.newSection("Fazer Pedido", this.getResources().getDrawable(R.drawable.search_icon), new DashboardFragment(cliente)));
            this.addSection(this.newSection("Pagar", this.getResources().getDrawable(R.drawable.search_icon), new DashboardFragment(cliente)));
        }




        if ( withouLogin ) {
            this.addSection(this.newSection("Login", this.getResources().getDrawable(R.drawable.login_icon),new LoginFragment()));
        }else{
            this.addSection(this.newSection("Logout", this.getResources().getDrawable(R.drawable.logout_icon), new LoginFragment()));
            ObjectAnimator.ofFloat(findViewById(R.id.user_cover),"alpha",0.5f).setDuration(0).start();
            Picasso.with(getApplicationContext()).load("https://graph.facebook.com/" + CacheUtil.read(this, "id", "") + "/picture?type=normal&height=200&width=200").into((ImageView) findViewById(R.id.user_photo));
            Picasso.with(getApplicationContext()).load("https://graph.facebook.com/" + CacheUtil.read(this, "id", "") + "/picture?type=normal&height=300&width=200").into((ImageView) findViewById(R.id.user_cover));
        }
    }

    @Override
    public void onAccountOpening(GAccount gAccount) {
    }

    @Override
    public void openFragment(Fragment fragment) {
    }

    public Activity getMainActivityContext(){
        return this;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if(scanResult != null) {
            System.out.println("format: " + scanResult.getFormatName());
            System.out.println("contents: " + scanResult.getContents());
            System.out.println("NOME: " + CacheUtil.read(this,"nome", "-"));
            if(CacheUtil.read(this,"email", "-").equals("-")){
                System.out.println("EMAIL NAO ESTA NO CACHE");
                return;
            }
            new RestClient().getApiService().putMoney(CacheUtil.read(this,"email", ""), scanResult.getContents(), new Callback<String>() {
                @Override
                public void success(String s, Response response) {
                    System.out.println(s);
                    TextView saldo = (TextView) findViewById(R.id.dashboard_saldo);
                    saldo.setText("Saldo: " + s);
                    CacheUtil.store(getMainActivityContext(),"cartao_saldo", s);
                }

                @Override
                public void failure(RetrofitError error) {

                }
            });
            Toast.makeText(this, "Colocou a grana", Toast.LENGTH_SHORT).show();

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
