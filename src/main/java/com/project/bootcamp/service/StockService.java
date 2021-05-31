package com.project.bootcamp.service;

import com.project.bootcamp.exceptions.BusinessException;
import com.project.bootcamp.exceptions.NotFoundException;
import com.project.bootcamp.mapper.StockMapper;
import com.project.bootcamp.model.Stock;
import com.project.bootcamp.model.dto.StockDTO;
import com.project.bootcamp.repository.StockRepository;
import com.project.bootcamp.util.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/* A camada service faz a intermediação enter a camada de controle e a camada repository */

@Service
public class StockService {


    @Autowired
    private StockMapper mapper;

    @Autowired
    private StockRepository repository;

    @Transactional
    public StockDTO save(StockDTO dto){

        /* Regra de negócio:
        Não é permitido cadastrar duas ações com mesmo nome e data.
        Por isso primeiro fazemos a verificação se o envio do cadastro
        já não existe no banco de dados */

        Optional<Stock> optionalStock = repository.findByNameAndDate(dto.getName(), dto.getDate());

        if(optionalStock.isPresent()){
              throw new BusinessException(MessageUtils.STOCK_ALREADY_EXISTS);
        }


        /* Objeto mapeado de StockDTO para Stock */
        Stock stock = mapper.toEntity(dto);
        repository.save(stock);
        return mapper.toDto(stock);

    }

    @Transactional
    public StockDTO update(StockDTO dto) {

        Optional<Stock> optionalStock = repository.findByStockUpdate(dto.getName(), dto.getDate(), dto.getId());

        if(optionalStock.isPresent()){
            throw new BusinessException(MessageUtils.STOCK_ALREADY_EXISTS);
        }

        /* Objeto mapeado de StockDTO para Stock */
        Stock stock = mapper.toEntity(dto);
        repository.save(stock);
        return mapper.toDto(stock);

    }



    public StockDTO delete(Long id) {
        /* primeiro verifamos se o id encontra-se na base de dados */
        StockDTO dto = this.findById(id); /* se não houver já levanta exceção. */
        repository.deleteById(dto.getId());

        return dto;
    }



    @Transactional(readOnly = true) /* garante que a transação é só para select */
    public List<StockDTO> findAll() {
        List<Stock> list = repository.findAll();
        return mapper.toDto(list);
    }

    @Transactional(readOnly = true) /* garante que a transação é só para select */
    public StockDTO findById(Long id) {
       return repository.findById(id).map(mapper::toDto).orElseThrow(NotFoundException::new);

    }

    @Transactional(readOnly = true)
    public List<StockDTO> findByToday() {
        return repository.findByToday(LocalDate.now()).map(mapper::toDto).orElseThrow(NotFoundException::new);
    }
}
