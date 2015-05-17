/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logika;

/**
 *Třída datového typu kategorie statických stránek
 * @author Petr Kokeš
 */
public class StatickeStrankyKategorie {
    
    private String id;
    private String icon;
    private String nazev;
    private String poradi;
    private String viditelnost;
    
    public StatickeStrankyKategorie(String id,String icon,String nazev,String poradi,String viditelnost){
        this.id=id;
        this.icon=icon;
        this.nazev=nazev;
        this.poradi=poradi;
        this.viditelnost=viditelnost;
    }
    public String getViditelnost(){
    return viditelnost;}
    
    public String getPoradi(){
    return poradi;}
    
    public String getNazev(){
    return nazev;}
    
    public String getIcon(){
    return icon;}
    
    public String getId(){
    return id;}
}
