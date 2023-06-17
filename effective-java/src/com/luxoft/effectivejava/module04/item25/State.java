package com.luxoft.effectivejava.module04.item25;

//Emulated extensible enum using an interface
public interface State {
	void doAction(CreditRequest creditRequest);
}
