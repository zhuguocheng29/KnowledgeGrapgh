package com.cetc28.seu.rdf;

public class RDF {
	private Term subject;
	private Term predict;
	private Term object;

	public RDF(Term subject, Term predict, Term object) {
		super();
		this.subject = subject;
		this.predict = predict;
		this.object = object;
	}

	public Term getSubject() {
		return subject;
	}

	public void setSubject(Term subject) {
		this.subject = subject;
	}

	public Term getPredict() {
		return predict;
	}

	public void setPredict(Term predict) {
		this.predict = predict;
	}

	public Term getObject() {
		return object;
	}

	public void setObject(Term object) {
		this.object = object;
	}
	
}
