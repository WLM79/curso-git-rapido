package com.example.demo.props;

public enum RegisterStatus {

	ACTIVE {
		@Override
		public String toString() {
			return "ATIVO";
		}
	},

	INACTIVE {
		@Override
		public String toString() {
			return "INATIVO";
		}
	},
	
	PENDING {
		@Override
		public String toString() {
			return "PENDENTE";
		}
	};
	
	public boolean compare(String value) {
		return this.toString().equals(value.toUpperCase());
	}

	public static RegisterStatus get(String value) {
		for (RegisterStatus l : RegisterStatus.values())
			if (l.compare(value))
				return l;
		
		return null;
	}

}