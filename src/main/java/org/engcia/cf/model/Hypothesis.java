package org.engcia.cf.model;

public class Hypothesis extends FactCF{

	public static final String PRIORIDADE_PRECO = "Preço Prioritário";
	public static final String PRIORIDADE_CONSUMO= "Consumo prioritário";
	public static final String PRIORIDADE_DESIGN = "Design Prioritário";
	public static final String MAIS_QUILOMETRAGEM = "Prefere um carro com mais KMs";
	public static final String MAIS_ANTIGO = "Prefere um carro mais antigo";
	public static final String COMBUSTIVEIS_FOSSEIS = "Prefere um carro a combustiveis fosseis";
	public static final String CARRO_ELETRICO = "Prefere um carro elétrico";
	public static final String CARRO_GASOLEO = "Carro a gasóleo";
	public static final String CARRO_GASOLINA = "Carro a Gasolina";
	public static final String PERFIL_VERSATIL = "Prefere um carro versátil";
	public static final String PERFIL_DESPORTIVO = "Prefere um carro desportivo";
	public static final String PERFIL_FAMILIAR = "Prefere um carro familiar";
	public static final String FAMILIAR_7LUGARES = "Carro familiar de 7 lugares";
	public static final String FAMILIAR_45LUGARES = "Carro familiar de 4/5 lugares";
	public static final String DESPORTIVO_45PORTAS = "Carro desportivo de 4/5 portas";
	public static final String DESPORTIVO_2PORTAS = "Carro desportivo de 2 portas";


	public Hypothesis(double cf, String description) {
		super(cf, description, "true");
	}

	public String toString() {
		return super.toString();
	}

}