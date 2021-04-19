package Models;
import Interfaces.Situação;

public class Situação2 implements Situação {

	@Override
	public String getSituacao() {
		return "Habilitada para tomar a 1° dose";
	}

}
