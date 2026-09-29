package model;

import java.io.File;
import java.util.List;

/**
 *
 * @author etnag
 */
public interface UserDAO {
    public List<User> getAllUser();
    public User getUserById(User user);
    public boolean purchaseGame(File fichero);
    public boolean insertUser();
    public boolean viewUserGames(File fichero);
}
