package com.example.demo.props;

public enum RegisterStatusFilter {

	ACTIVE_AND_PENDING("ATIVO E PENDENTE", "<> '" + RegisterStatus.INACTIVE.toString() + "'"),
	ACTIVE("ATIVO", "= '" + RegisterStatus.ACTIVE.toString() + "'"),
	PENDING("PENDENTE", "= '" + RegisterStatus.PENDING.toString() + "'"),
	INACTIVE("INATIVO", "= '" + RegisterStatus.INACTIVE.toString() + "'");
	
	private final String name;
	private final String sqlClause;
	
	private RegisterStatusFilter(String name, String sqlCause) {
		this.name = name;
		this.sqlClause = sqlCause;
	}
	
	public String getName() {
		return name;
	}

	public String getSqlClause(String columnName) {
		return "UPPER(" + columnName + ") " + sqlClause;
	}
	
	@Override
	public String toString() {
		return this.name;
	}

	public boolean compare(String value) {
		return this.toString().equals(value.toUpperCase());
	}

	public static RegisterStatusFilter get(String value) {
		for (RegisterStatusFilter l : RegisterStatusFilter.values())
			if (l.compare(value))
				return l;
		
		return null;
	}

}