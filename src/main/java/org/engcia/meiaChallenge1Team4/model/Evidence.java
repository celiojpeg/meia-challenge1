package org.engcia.meiaChallenge1Team4.model;

public class Evidence extends FactCF{

	public static final String PRECO_PRIORITARIO = "Preco e prioritario em relacao a consumo e design";
	public static final String CONSUMO_PRIORITARIO = "Consumo e prioritario em relacao ao design";
	public static final String QUAL_O_PRECO = "Disposto a pagar mais de 10.000";
	public static final String ANTIGUIDADE_QUILOMETRAGEM = "Prefere um carro mais antigo ao inves de um com mais kilometragem";
	public static final String IMPOSTO_VEICULO = "Quer ter em conta o imposto anual";
	public static final String ESTADO_VEICULO = "O estado do veiculo e relevante";
	public static final String COMBUSTIVEIS_FOSSEIS = "Prefere combustiveis fosseis em vez de um carro eletrico";
	public static final String MEDIA_CONSUMO = "A media de consumos e relevante";
	public static final String MAIS_200KM = "Ira realizar mais de 200 quilometros diarios";
	public static final String EMISSOES	= "O consumo de emissoes e um fator relevante";
	public static final String PERFORMANCE = "A performance do veiculo e relevante";
	public static final String PILOTO_AUTOMATICO = "Quer introduzir modo de piloto automatico";
	public static final String CARRO_VERSATIL = "Da preferencia a um carro versatil a um familiar";
	public static final String CARRO_FAMILIAR = "Da preferencia a um carro familiar a um desportivo";
	public static final String TIPO_SUV_OFF_ROAD = "Prefere um SUV off-road a on-road";
	public static final String DESPORTIVO_2PORTAS = "Deseja um desportivo de 2 portas";
	public static final String DESPORTIVO_CABRIO = "Deseja um desportivo cabrio";
	public static final String FAMILIAR_7_LUGARES = "O seu carro familiar deve ter 7 lugares";

	public Evidence(double cf, String description, String value) {
		super(cf, description, value);
	}
	
	public String toString() {
		return super.toString();
	}

}
