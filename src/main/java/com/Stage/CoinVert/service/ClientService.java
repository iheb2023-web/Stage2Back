package com.Stage.CoinVert.service;
import com.Stage.CoinVert.Ripository.AdminRepository;
import com.Stage.CoinVert.Ripository.ClientRepository;
import com.Stage.CoinVert.entity.Client;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientService {
    private ClientRepository clientRepository;

    public List<Client> getClient(){
        return clientRepository.findAll();
    }
    public Optional<Client> getClientById(long id){
        return clientRepository.findById(id);
    }
    public Client saveClient(Client cl){

        return clientRepository.saveAndFlush(cl);
    }
    public boolean existeClientById(long id){

        return clientRepository.existsById(id);
    }
    public void deleteClient(long id){
        clientRepository.deleteById(id);
    }
}
