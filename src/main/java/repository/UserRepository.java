package repository;

import com.google.gson.GsonBuilder;
import domain.User;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import domain.exception.CustomException;

import java.io.BufferedWriter;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class UserRepository extends AbstractRepository<Long, User>{
    final String PATH = "src/main/resources/databases/user_database.json";

    public void add(User user) throws CustomException {
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
            loadData();
            return new ArrayList<>(super.getAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>(super.getAll());
    }

    public void updateRepository(){
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();

        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(PATH));
            String json = gson.toJson(super.getAll());
            System.out.println(json);

            writer.write(json);
            writer.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
