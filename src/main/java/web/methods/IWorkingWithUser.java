package web.methods;

import java.io.IOException;
import java.net.URISyntaxException;

public interface IWorkingWithUser extends IWorkingWithData {
    String post(String url, String newUser) throws IOException, InterruptedException;
    String put(String url, String userData) throws IOException, URISyntaxException, InterruptedException;
    String remove(String url) throws IOException, URISyntaxException, InterruptedException;

}
