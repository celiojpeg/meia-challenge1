package org.engcia.cf.model;

public class Evidence extends FactCF{

	public static final String PRECO_PRIORITARIO = "O preço é o fator prioritário em relação ao consume e design";
	public static final String CONSUMO_PRIORITARIO = "O consumo é o fator prioritário em relação ao design";
	public static final String DESIGN_PRIORITARIO = "O design é o fator prioritário";
	public static final String QUAL_O_PRECO = "Disposto a pagar mais de 10.000€";
	public static final String ANTIGUIDADE_KILOMETRAGEM = "Prefere um carro mais antigo ao invés de um com mais kilometragem";
	public static final String IMPOSTO_VEICULO = "Quer ter em conta o imposto anual";
	public static final String ESTADO_VEICULO = "O estado do veiculo é relevante";
	public static final String COMBUSTIVEIS_FOSSEIS = "Prefere combustíveis fósseis em vez de um carro elétrico";

	public Evidence(double cf, String description, String value) {
		super(cf, description, value);
	}
	
	public String toString() {
		return super.toString();
	}

}
