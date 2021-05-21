package repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import domain.IDGenerator;
import domain.Test;
import domain.exception.CustomException;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TestRepository extends AbstractRepository<Long, Test>{
    private static final String PATH = "src/main/resources/databases/test_database.json";
    private final IDGenerator idGenerator;

    public TestRepository(){
        loadData();
        idGenerator = new IDGenerator(super.getLastID());
    }

    public void add(Test test) throws CustomException {
        test.setId(idGenerator.getID());
        super.add(test);
    }

    private void loadData(){
        List<Test> data = new ArrayList<>();

        try {
            Reader reader = Files.newBufferedReader(Paths.get(PATH));
            data = new Gson().fromJson(reader, new TypeToken<ArrayList<Test>>() {}.getType());
            reader.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

        for (Test test : data){
            try {
                super.add(test);
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

    public List<Test> getTestsForUser(Long userID){
        ArrayList<Test> data = new ArrayList<>(super.getAll());
        ArrayList<Test> dataFinal = new ArrayList<>();

        try {
            for (Test test : data){
                if (test.getUserID().equals(userID)){
                    dataFinal.add(test);
                }
            }
            return dataFinal;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public List<Test> getAll() {
        try {
            return new ArrayList<>(super.getAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>(super.getAll());
    }

    public void updateRepository(){
        super.updateRepository(PATH);
    }

    public void resetRepository() {
        super.resetRepository(PATH);
    }
}
