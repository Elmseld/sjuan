package sjuan;
import java.io.*;

public class Request implements Serializable {
	private static final long serialVersionUID = 1L;
	private String request;

	public Request(String request) {
		this.request = request;
	}

	public String getRequest() {
		return request;
	}
}
