package dbCreator;

public class DbCreator {
    public static void main(String[] args){

        DbLogicController controller = new DbLogicController();
        int action = controller.getAction();

        switch (action) {
            case 0 -> controller.generateCsvs();
            case 1 -> controller.compileDb();
        }

    }

}

