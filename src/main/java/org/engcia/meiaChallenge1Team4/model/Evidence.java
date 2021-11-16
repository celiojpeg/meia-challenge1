package org.engcia.meiaChallenge1Team4.model;

public class Evidence extends FactCF{

	public static final String PRECO_PRIORITARIO = "Preço é prioritário em relação a consumo e design?";
	public static final String CONSUMO_PRIORITARIO = "Consumo é prioritário em relação ao design?";
	public static final String QUAL_O_PRECO = "Disposto a pagar mais de 10.000€";
	public static final String ANTIGUIDADE_QUILOMETRAGEM = "Prefere um carro mais antigo ao invés de um com mais kilometragem";
	public static final String IMPOSTO_VEICULO = "Quer ter em conta o imposto anual";
	public static final String ESTADO_VEICULO = "O estado do veiculo é relevante";
	public static final String COMBUSTIVEIS_FOSSEIS = "Prefere combustíveis fósseis em vez de um carro elétrico";
	public static final String MEDIA_CONSUMO = "A média de consumos é relevante";
	public static final String MAIS_200KM = "Irá realizar mais de 200 quilometros diários";
	public static final String EMISSOES	= "O consumo de emissões é um fator relevante";
	public static final String PERFORMANCE = "A performance do veículo é relevante";
	public static final String CARREGAMENTO_DIÁRIO = "Tem a possibilidade de efatuar um carregamento diário";
	public static final String PILOTO_AUTOMATICO = "Quer introduzir modo de piloto automático";
	public static final String CARRO_VERSATIL = "Dá preferência a um carro versátil a um familiar";
	public static final String CARRO_FAMILIAR = "Dá preferência a um carro familiar a um desportivo";
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
