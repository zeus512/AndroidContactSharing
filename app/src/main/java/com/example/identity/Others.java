package com.example.identity;

import java.io.Serializable;

public class Others implements Serializable {
	private static final long serialVersionUID = 1L;
	private final String name;
	private final String number, cont;

	public Others(String name, String number, String cont) {
		this.name = name;
		this.number = number;
		this.cont = cont;
	}

	public String getName() {
		return this.name;
	}

	public String getNumber() {
		return this.number;
	}

	public String getCont() {
		return this.cont;
	}
}
