package repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import domain.Test;
import domain.exception.CustomException;

import java.io.BufferedWriter;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TestRepository extends AbstractRepository<Long, Test>{
    final String PATH = "src/main/resources/databases/test_database.json";

    public void add(Test test) throws CustomException {
        super.add(test);
    }

    public void delete(Long ID) throws CustomException {
        super.delete(ID);
    }

    public void loadData(){
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
        try {
            loadData();
            ArrayList<Test> data = new ArrayList<>(super.getAll());
            ArrayList<Test> dataFinal = new ArrayList<>();

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
            loadData();
            return new ArrayList<>(super.getAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>(super.getAll());
    }

    public void updateRepository(){
        Gson gson = new GsonBuilder()
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

    public static void main(String[] args) throws CustomException{
        Test test = new Test(20, 4, 2353L);
        test.setTime(50);
        test.setId(534L);

        TestRepository repo = new TestRepository();
        repo.loadData();
        repo.add(test);
        repo.updateRepository();
    }
}
