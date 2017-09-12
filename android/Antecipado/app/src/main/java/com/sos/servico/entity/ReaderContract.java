package com.sos.servico.entity;

import android.provider.BaseColumns;

/**
 * Created by deivison on 5/18/15.
 */
public class ReaderContract {

    public static class AvaliacaoEntry implements BaseColumns {

        public static final String TABLE_NAME = "avaliacao";
        public static final String COLUMN_SERVICOID = "servicoid";
        public static final String COLUMN_SERVICONAME = "serviconame";
        public static final String COLUMN_SERVICOCATEGORIA = "categoria";

        private String servicoId;
        private String servicoName;
        private String servicoCategoria;

        public String getServicoId() {
            return servicoId;
        }

        public void setServicoId(String servicoId) {
            this.servicoId = servicoId;
        }

        public String getServicoName() {
            return servicoName;
        }

        public void setServicoName(String servicoName) {
            this.servicoName = servicoName;
        }

        public String getServicoCategoria() {
            return servicoCategoria;
        }

        public void setServicoCategoria(String servicoCategoria) {
            this.servicoCategoria = servicoCategoria;
        }
    }

}
