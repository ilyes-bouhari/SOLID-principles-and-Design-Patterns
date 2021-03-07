package package_1;

import javax.swing.JButton;

public abstract class IView<T> {
	abstract void show();
	abstract void reset();
	abstract boolean validate();
	
	abstract T fillObject();
	
	abstract JButton getBtnCancel();
	abstract JButton getBtnSubmit();
}

