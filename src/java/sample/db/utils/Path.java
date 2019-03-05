package sample.db.utils;

/**
 * @author: LD
 * @date:
 * @description:
 */
public class Path {
    public String path() {
        String property = System.getProperty("user.dir");
        System.out.println(property);
        return property;
    }

}
