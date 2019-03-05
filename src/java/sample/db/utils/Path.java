package sample.db.utils;

import javafx.scene.layout.Pane;
import sun.rmi.runtime.Log;

import java.util.logging.Logger;

/**
 * @author: LD
 * @date:
 * @description:
 */
public class Path {
    public String path() {
        String s = getClass().getResource("/db/crm.db").toString();
        s = s.replaceAll("file:/", "");
        s = s.replaceAll("jar:", "");
        System.out.println(s);
        return s;
    }

}
