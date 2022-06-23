package com.example.demo.props;

import com.example.demo.util.StringUtil;

public enum State {

	DEFAULT(0, "", ""),
	ACRE(1, "AC", "ACRE"),
	ALAGOAS(2, "AL", "ALAGOAS"),
	AMAPA(3, "AP", "AMAPÁ"),
	AMAZONAS(4, "AM", "AMAZONAS"),
	BAHIA(5, "BA", "BAHIA"),
	CEARA(6, "CE", "CEARÁ"),
	DISTRITO_FEDERAL(7, "DF", "DISTRITO FEDERAL"),
	ESPIRITO_SANTO(8, "ES", "ESPÍRITO SANTO"),
	GOIAS(9, "GO", "GOIÁS"),
	MARANHAO(10, "MA", "MARANHÃO"),
	MATO_GROSSO(11, "MT", "MATO GROSSO"),
	MATO_GROSSO_DO_SUL(12, "MS", "MATO GROSSO DO SUL"),
	MINAS_GERAIS(13, "MG", "MINAS GERAIS"),
	PARA(14, "PA", "PARÁ"),
	PARAIBA(15, "PB", "PARAÍBA"),
	PARANA(16, "PR", "PARANÁ"),
	PERNAMBUCO(17, "PE", "PERNAMBUCO"),
	PIAUI(18, "PI", "PIAUÍ"),
	RIO_DE_JANEIRO(19, "RJ", "RIO DE JANEIRO"),
	RIO_GRANDE_DO_NORTE(20, "RN", "RIO GRANDE DO NORTE"),
	RIO_GRANDE_DO_SUL(21, "RS", "RIO GRANDE DO SUL"),
	RONDONIA(22, "RO", "RONDÔNIA"),
	RORAIMA(23, "RR", "RORAIMA"),
	SANTA_CATARINA(24, "SC", "SANTA CATARINA"),
	SAO_PAULO(25, "SP", "SÃO PAULO"),
	SERGIPE(26, "SE", "SERGIPE"),
	TOCANTINS(27, "TO", "TOCANTINS");

	private final int id;
	private final String acronym;
	private final String name;

	private State(int id, String acronym, String state) {
		this.id = id;
		this.acronym = acronym;
		this.name = state;
	}

	public String getAcronym() {
		return acronym;
	}

	public String getName() {
		return name;
	}
	
	/** @param comparison - The state acronym or name to compare **/
	public boolean compare(String comparison) {
		return this.acronym.equals(comparison.toUpperCase()) || this.name.equals(comparison.toUpperCase());
	}
	
	/** @param state - The state acronym or name to get **/
	public static State get(String state) {
		if (StringUtil.isNotEmpty(state))
			for(State s : State.values())
				if(s.compare(state))
					return s;
		
		return State.DEFAULT;
	}
	
	@Override
	public String toString() {
		return name;
	}

}
