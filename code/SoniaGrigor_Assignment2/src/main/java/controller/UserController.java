package controller;

import view.AdminView;
import view.SpecificOperationView;

public class UserController {

    public static void handleBasicOperationButtonEvent() {
        new AdminView();
    }

    public static void handleSpecificOperationButtonEvent() {
        new SpecificOperationView();
    }

}
