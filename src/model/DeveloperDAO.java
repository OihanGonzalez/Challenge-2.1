package model;

/**
 *
 * @author CJ
 */
import java.util.List;

public interface DeveloperDAO {

    public boolean createDeveloper();

    public Developer getDeveloperById(int idDeveloper);

    public List<Developer> getAllDevelopers();
}
