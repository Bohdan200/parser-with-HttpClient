package web.methods;

import java.io.IOException;
import java.net.URISyntaxException;

public interface IWorkingWithData {
    String get(String url) throws IOException, URISyntaxException, InterruptedException;
}
