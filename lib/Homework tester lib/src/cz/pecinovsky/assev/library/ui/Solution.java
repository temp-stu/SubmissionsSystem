/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.pecinovsky.assev.library.ui;

/**
 *
 * @author Petr
 */
public class Solution {
    
    private String id;
    private String soubor;
    private String cesta;
    private String body;
    private String userId;
    private String ukolId;
    private String fileExt;

    public Solution(String id, String soubor, String cesta,  String body,
             String userId, String ukolId, String fileExt) {

        this.id = id;
        this.soubor = soubor;
        this.cesta = cesta;
        this.body = body;
        this.userId = userId;
        this.ukolId = ukolId;
        this.fileExt = fileExt;       
    }

    public String getBody() {
        return body;
    }

    public String getCesta() {
        return cesta;
    }

    public String getSoubor() {
        return soubor;
    }

    public String getUserId() {
        return userId;
    }

    public String getFileExt() {
        return fileExt;
    }

    public String getUkolId() {
        return ukolId;
    }

    public String getId() {
        return id;
    }
    
    public void setBody(String body){
    this.body = body;
    }
    
}
