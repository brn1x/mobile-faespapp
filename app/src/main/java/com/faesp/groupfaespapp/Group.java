package com.faesp.groupfaespapp;

public class Group {
    private Integer id;
    private String nmGrupo;
    private Integer qtdMinP;
    private Integer qtdMaxP;
    private Integer qtdEnc;
    private String tpGrupo;
    private String descGrupo;
    private String objGrupo;
    private String situacao;
    private int icon;

    public Group(Integer id, String nmGrupo, Integer qtdMinP, Integer qtdMaxP, Integer qtdEnc, String tpGrupo, String descGrupo, String objGrupo, String situacao) {
        this.id = id;
        this.nmGrupo = nmGrupo;
        this.qtdMinP = qtdMinP;
        this.qtdMaxP = qtdMaxP;
        this.qtdEnc = qtdEnc;
        this.tpGrupo = tpGrupo;
        this.descGrupo = descGrupo;
        this.objGrupo = objGrupo;
        this.situacao = situacao;

        if(tpGrupo.equals("entretenimento") || tpGrupo.equals("Entretenimento")){
            this.icon = R.drawable.entretenimento;
        }
        else if( tpGrupo.equals("esporte") || tpGrupo.equals("Esporte")){
            this.icon = R.drawable.esporte;
        }
        else if( tpGrupo.equals("Religioso") || tpGrupo.equals("Religioso")){
            this.icon = R.drawable.religioso;
        }
        else if( tpGrupo.equals("videogame") || tpGrupo.equals("Videogame")){
            this.icon = R.drawable.jogos;
        }
        else if( tpGrupo.equals("estudo") || tpGrupo.equals("Estudo")){
            this.icon = R.drawable.estudo;
        }
        else {
            this.icon = R.drawable.icongroup;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNmGrupo() {
        return nmGrupo;
    }

    public void setNmGrupo(String nmGrupo) {
        this.nmGrupo = nmGrupo;
    }

    public Integer getQtdMinP() {
        return qtdMinP;
    }

    public void setQtdMinP(Integer qtdMinP) {
        this.qtdMinP = qtdMinP;
    }

    public Integer getQtdMaxP() {
        return qtdMaxP;
    }

    public void setQtdMaxP(Integer qtdMaxP) {
        this.qtdMaxP = qtdMaxP;
    }

    public Integer getQtdEnc() {
        return qtdEnc;
    }

    public void setQtdEnc(Integer qtdEnc) {
        this.qtdEnc = qtdEnc;
    }

    public String getTpGrupo() {
        return tpGrupo;
    }

    public void setTpGrupo(String tpGrupo) {
        this.tpGrupo = tpGrupo;
    }

    public String getDescGrupo() {
        return descGrupo;
    }

    public void setDescGrupo(String descGrupo) {
        this.descGrupo = descGrupo;
    }

    public String getObjGrupo() {
        return objGrupo;
    }

    public void setObjGrupo(String objGrupo) {
        this.objGrupo = objGrupo;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public void setIcon(int icon ){ this.icon = icon; }

    public int getIcon() { return icon; }
}
