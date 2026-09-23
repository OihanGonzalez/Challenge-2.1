package model;

import java.util.List;

/**
 *
 * @author etnag
 */
public interface UserDAO {
    public List<User> getAllUser();
    public User getUserById(User user);
}
