package serverproxy;

import org.w3c.dom.Document;

import java.io.File;

public interface IServerProxy {
    public Document readXML(File file);
}
