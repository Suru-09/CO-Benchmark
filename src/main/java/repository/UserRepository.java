package repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import domain.IDGenerator;
import domain.User;
import domain.exception.CustomException;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class UserRepository extends AbstractRepository<Long, User>{
    private final String PATH = "src/main/resources/databases/user_database.json";

    private static UserRepository instance = null;
    private User currentUser;

    private final IDGenerator idGenerator;


    public UserRepository(){
        loadData();
        idGenerator = new IDGenerator(super.getLastID());
    }

    public static UserRepository getInstance(){
        if ( instance == null ){
            instance = new UserRepository();
        }
        return instance;
    }


    public User getCurrentUser() {
        return currentUser;
    }


    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public void add(User user) throws CustomException{
        if ( super.getAll().contains(user) ){
            throw new CustomException("User already exists!");
        }
        user.setId(idGenerator.getID());
        super.add(user);
    }

    public void loadData(){
        List<User> data = new ArrayList<>();

        try {
            Reader reader = Files.newBufferedReader(Paths.get(PATH));
            data = new Gson().fromJson(reader, new TypeToken<ArrayList<User>>() {}.getType());
            reader.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

        for (User user : data){
            try {
                super.add(user);
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

    public List<User> getAll() {
        try {
            return new ArrayList<>(super.getAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>(super.getAll());
    }

    public boolean userExists(String username, String password){
        for (User user : super.getAll()){
            if ( user.getUsername().equals(username) && user.getPassword().equals(password) ){
                return true;
            }
        }
        return false;
    }

    public void updateRepository(){
        super.updateRepository(PATH);
    }

    public void resetRepository() {
        super.resetRepository(PATH);
    }
}
