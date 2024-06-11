package codinpad.codequality.usermanagement;

import java.util.List;

public class UserManager {
    private List<User> users;
    private Database dbConnection;

    //The method bellow should be renamed to processUsers()
    public void dataProcessing() {
        if (dbConnection.isConnected()) {
            for (User u : users) {
                System.out.println("Processing: " + u.getName());
            }
        }
    }

    //The method bellow should be renamed to insertUser instead of updateUsers, also note that the parameter is not an Array or List.
    public void updateUsers(User item) {
        users.add(item);
    }

    //The method bellow should be named deleteUser instead of deleteUsers, note that the parameter is not an Array or List.
    public void deleteUsers(User item) {
        users.remove(item);
    }

    // The method bellow should be named isUserExist or isValid instead of find user,
    // as the method is actually performing a check
    public boolean findUser(String userName) {
        for (User u : users) {
            if (u.getName().equals(userName)) {
                return true;
            }
        }
        return false;
    }
}