package com.tp2.base;

import javax.swing.JButton;

public abstract class IView<T> {
	abstract public void show();
	abstract public void reset();
	abstract public boolean validate();
	
	abstract public T fillObject();
	
	abstract public JButton getBtnCancel();
	abstract public JButton getBtnSubmit();
}

