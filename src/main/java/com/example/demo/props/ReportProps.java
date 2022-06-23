package com.example.demo.props;

public enum ReportProps {

	EXPORT_PDF {
		@Override
		public String toString() {
			return "PDF";
		}
	},

	EXPORT_EXCEL {
		@Override
		public String toString() {
			return "Excel";
		}
	};

	public boolean compare(String export) {
		return this.toString().equals(export);
	}

	public static ReportProps getReportPropsExport(String export) {
		for (ReportProps exp : ReportProps.values())
			if (exp.compare(export))
				return exp;
		return ReportProps.EXPORT_PDF;
	}

}
