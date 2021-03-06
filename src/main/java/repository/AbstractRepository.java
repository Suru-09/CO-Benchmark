package repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import domain.BaseEntity;
import domain.exception.CustomException;
import domain.strategy.AnnotationExclusionStrategy;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractRepository<ID, T extends BaseEntity<ID>> implements Repository<T, ID>{
    protected Map<ID,T> elems;

    public AbstractRepository() {
        elems = new HashMap<>();
    }

    @Override
    public void add(T el) throws CustomException{
        if ( elems.containsKey((el.getId())) ) {
            throw new CustomException("The element is already in the repo.");
        } else {
            elems.put(el.getId(), el);
        }
    }

    @Override
    public void delete(ID id) throws CustomException{
        if (elems.remove(id) == null) throw new CustomException("Element not in repo.");
    }

    @Override
    public void update(T el) throws CustomException{
        if (elems.containsKey(el.getId()))
            elems.put(el.getId(), el);
        else
            throw new CustomException("Element not updated.");
    }

    @Override
    public Collection<T> getAll() {
        return elems.values();
    }

    @Override
    public T findById(ID id) throws CustomException{
        if (elems.containsKey(id))
            return elems.get(id);
        else
            throw new CustomException("Element not in repo.");
    }

    public ID getLastID() {
        if ( elems.isEmpty() ){
            return null;
        }

        Object[] arr = elems.keySet().toArray();

        return (ID)arr[elems.size()-1];
    }

    public void updateRepository(String path){
        Gson gson = new GsonBuilder()
                .setExclusionStrategies(new AnnotationExclusionStrategy())
                .setPrettyPrinting()
                .create();

        try {
//            JsonWriter writer = new JsonWriter(new FileWriter(path));
//            gson.toJson(new JsonParser().parse(gson.toJson(this.getAll())), writer);
            FileWriter writer = new FileWriter(path);

            String json = gson.toJson(this.getAll());

            writer.write(json);
            writer.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void resetRepository(String path){
        elems.clear();
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(path));
            String json = "[]";

            writer.write(json);
            writer.close();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
