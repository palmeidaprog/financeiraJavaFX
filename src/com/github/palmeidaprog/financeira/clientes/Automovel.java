package com.github.palmeidaprog.financeira.clientes;

import java.security.InvalidParameterException;

/*
 * Financeira App
 * UNICAP - Univesidade Catolica de Pernambuco
 * @author Aluno: Paulo R. Almeida Filho
 * http://www.github.com/palmeidaprog/financeira
 * @email pauloalmeidaf@gmail.com
 * @email palmeidaprogramming@gmail.com
 * Professor: Antonio Canvalcanti
 */

public class Automovel extends Bem {
    private final String marca;
    private final String modelo;
    private final int anoFabricacao;
    private final int anoModelo;

    public Automovel(double valor, String marca, String modelo, int
            anoModelo) throws InvalidParameterException  {
        this(valor, marca, modelo, anoModelo, anoModelo);
    }

    public Automovel(double valor, String marca, String modelo, int anoModelo,
                     int anoFabricacao) throws InvalidParameterException  {
        super(valor);
        this.marca = marca;
        this.modelo = modelo;
        valida(anoModelo);
        this.anoModelo = anoModelo;
        valida(anoFabricacao);
        this.anoFabricacao = anoFabricacao;
    }

    private void valida(int ano) throws InvalidParameterException {
        if(ano < 1769) {
            throw new InvalidParameterException("Ano não pode ser menor que "
                    + "1769");
        }
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getAnoFabricacao() {
        return anoFabricacao;
    }

    public int getAnoModelo() {
        return anoModelo;
    }
}
