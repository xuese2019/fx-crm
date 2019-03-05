package sample.db.utils;

/**
 * @author: LD
 * @date:
 * @description:
 */
public class Path {
    public String path() {
        String s = getClass().getResource("/crm.db").toString();
        return s.substring(6);
    }

}
