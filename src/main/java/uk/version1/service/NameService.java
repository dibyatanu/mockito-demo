package uk.version1.service;

import org.springframework.stereotype.Service;
import uk.version1.repository.NameRepository;
import uk.version1.utils.Utils;

import java.time.LocalDateTime;

@Service
public class NameService {

    private final NameRepository nameRepository;

    public NameService(final NameRepository nameRepository){
        this.nameRepository= nameRepository;
    }

    public String getName(final Long Id){
        return nameRepository.getName(Id).orElseThrow(() -> new RuntimeException("Name not Found"));
    }

    public void storeName(final Long Id, final String name){
        nameRepository.storeName(Id,name);
    }

    public LocalDateTime getDate(){
        return LocalDateTime.now();
    }
}
