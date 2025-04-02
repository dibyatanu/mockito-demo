package uk.version1.repository;



import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Repository
public class NameRepository {
    private static  Map<Long,String> nameList = new HashMap<>();

    static {
        nameList = new HashMap<>();
        nameList.put(1L,"David");
    }


    public Optional<String> getName(final Long Id){
        return Optional.of(nameList.get(Id));
    }

    public void storeName(final Long Id, final String name){

        nameList.put(Id,name);
    }


}
