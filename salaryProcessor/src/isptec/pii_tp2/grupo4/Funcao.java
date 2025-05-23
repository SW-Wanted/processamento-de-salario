/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isptec.pii_tp2.grupo4;

import java.util.ArrayList;

/**
 *
 * @author hp
 */
public class Funcao {
    public int codigo;
    public String nome;
    public double salarioBase;
    public double bonus;
    
    public static ArrayList<Funcao> funcoes;
    
    public Funcao()
    {
        funcoes = new ArrayList<Funcao>();
        Funcao funcao1 = new Funcao();
        funcao1.codigo = 1;
        funcao1.nome = "Recursos Humanos";
        funcao1.salarioBase = 50000;
        funcao1.bonus = 100;
        funcoes.add(funcao1);
    }
    
    public static void Imprimir()
    {
         for (Funcao c : funcoes)
        {
            System.out.println(c.codigo + " " + c.nome);
        }
    }
}