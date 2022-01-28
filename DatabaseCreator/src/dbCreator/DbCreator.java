package dbCreator;

import java.io.IOException;

public class DbCreator {
    public static void main(String[] args) throws IOException {

        DbLogicController controller = new DbLogicController();
        int action = controller.getAction();

        switch (action) {
            case 0 -> controller.generateCsvs();
            case 1 -> controller.compileDb();
        }

    }

}

