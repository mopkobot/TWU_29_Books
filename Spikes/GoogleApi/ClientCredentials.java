package GoogleApi;

public class ClientCredentials {

    /** Value of the "API key" shown under "Simple API Access". */
    static final String API_KEY =
            "AIzaSyAMR4F-UvVtgGByBiSa6vwHRpYitMy2jLY";

    static void errorIfNotSpecified() {
        if (API_KEY.startsWith("Enter ")) {
            System.err.println(API_KEY);
            System.exit(1);
        }
    }
}
