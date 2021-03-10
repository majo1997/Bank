import io.github.cdimascio.dotenv.Dotenv;

import java.util.HashMap;
import java.util.Map;

/**
 * This class loads and provides enviroment variables
 * */
public class EnvVariables {
    private static Map<String, String> envVariables;
    private static final String[] definedVariables= {
            "DATABASE_URL",
            "DATABASE_USER",
            "DATABASE_PASSWORD"
    };

    /**
     * Loads all defined enviroment variables
     * */
    public static void loadEnvVars() {
        envVariables = new HashMap<>();

        Dotenv dotenv = Dotenv.load();

        for(String var: definedVariables) {
            envVariables.put(var, dotenv.get(var));
        }
    }

    /**
     * Returns enviroment variable value stored under certain key.
     *
     * @param varName key under which is the value stored
     * @return enviroment variable value
     * */
    public static String getEnvVar(String varName) {
        return envVariables.get(varName);
    }
}
