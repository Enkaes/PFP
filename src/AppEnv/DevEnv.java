package AppEnv;

public class DevEnv {

    public static final String DOMAIN = "https://dev.precisefp";
    public static final String PORT = ".com/";

    public static String baseUrl() {
        return(DOMAIN+PORT);
    }
}
