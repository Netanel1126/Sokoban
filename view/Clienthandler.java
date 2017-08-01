package view;

import java.io.InputStream;
import java.io.OutputStream;

public interface Clienthandler {
	public void handleclient(InputStream in,OutputStream out);
}
