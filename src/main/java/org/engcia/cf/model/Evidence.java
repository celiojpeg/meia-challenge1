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
	public static final String MEDIA_CONSUMO = "A média de consumos é relevante";
	public static final String MAIS_200KM = "Irá realizar mais de 200 quilometros diários";
	public static final String EMISSOES	= "O consumo de emissões é um fator relevante";
	public static final String PERFORMANCE = "A performance do veículo é relevante";
	public static final String CARREGAMENTO_DIÁRIO = "Tem a possibilidade de efatuar um carregamento diário";
	public static final String PILOTO_AUTOMATICO = "Quer introduzir modo de piloto automático";
	public static final String CARRO_VERSATIL = "Dá preferência a um carro versátil a um familiar";
	public static final String CARRO_FAMILIAR = "Dá preferência a um carro familiar a um desportivo";
	public static final String TIPO_SUV = "Prefere um SUV off-road a on-road";
	public static final String DESPORTIVO_PORTAS = "Deseja um desportivo de 2 pçortas";
	public static final String DESPORTIVO_CABRIO = "Deseja um desportivo cabrio";
	public static final String FAMILIAR_LUGARES = "O seu carro familiar deve ter 7 lugares";

	public Evidence(double cf, String description, String value) {
		super(cf, description, value);
	}
	
	public String toString() {
		return super.toString();
	}

}
