package app;

import java.io.IOException;

/**
 * This file is used to run the Talk To a Club app. Please carefully follow the steps on the README.md file
 * for instructions on how to run the app. Note that if the user unsuccessfully connects to the database, the app will
 * throw an error.
 */
public class Main {

    /**
     * This method will run the Talk To a Club app. Please follow the instructions on how to run the app.
     * @param args this can be ignored.
     */
    public static void main(String[] args) {
        // Enter the file path to the ServiceAccountKey.json file here. Please carefully follow the instructions.
        final String filePath = "C:/dev/uoft/courses/csc207/ServiceAccountKey.json";

        final App app = new App();
        try {
            app.run(filePath);
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
