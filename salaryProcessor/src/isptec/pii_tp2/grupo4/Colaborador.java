/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isptec.pii_tp2.grupo4;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author hp
 */
public class Colaborador {
    public String numero;
    public String nome;
    public String morada;
    public LocalDate DataNascimento;
    public Funcao Funcao;
    public LocalDate DataAdmissao;
    public String email;
    
    public static ArrayList<Colaborador> colaboradores;
    
    public Colaborador()
    {
        
    }
    
    public void Cadastrar(Colaborador colaborador)
    {
        colaboradores.add(colaborador);
    }
    
    public Colaborador Actualizar(Colaborador colaborador)
    {
        return colaborador;
    }
    
    public void Desactivar(Colaborador colaborador)
    {
        
    }
    
    public void Imprimir()
    {
        for (Colaborador c : colaboradores)
        {
            System.out.println(c.nome);
        }
    }
}