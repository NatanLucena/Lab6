package Models;
import Interfaces.Situação;

public class Situação1 implements Situação {

	@Override
	public String getSituacao() {
		return "Não habitilidada para vacina.";
	}

}
