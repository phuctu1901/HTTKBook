package mta.ltnc.BookStore.service.client;

import mta.ltnc.BookStore.dto.client.PublisherDto;
import mta.ltnc.BookStore.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherClientService {
    @Autowired
    private PublisherRepository publisherRepository;
    public List<PublisherDto> getAll(){
        return publisherRepository.getAll();
    }
}
