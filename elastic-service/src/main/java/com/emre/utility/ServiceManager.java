package com.emre.utility;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import java.util.Optional;


/** <T> Bu sınıfı miras almak isteyen bir service hangi entity'e hizmet ediyorsa onun type
 * olarak verecek
 *
 * <ID> Bu sınıfı miras alan servisin kullanmakta olduğu id tipini belirlemesi gerekir.
 * Long,String ,Integer*/
public class ServiceManager<T ,ID> implements IService<T,ID>{

    /**T EXTENDS BASEENTİTY YAPMAYI UNUTMA !!!!!!!!!!!!!!!!!!!!!!!!**/

    /**Tüm serviceler için ortak bir kullanım sunacak ise, burada repository
     * üzerinden işlem yapabiliyor olmalı
     ***/
    private final ElasticsearchRepository<T,ID> repository;

    public ServiceManager(ElasticsearchRepository<T, ID> repository) {
        this.repository = repository;
    }

    @Override
    public T save(T t) {

        return repository.save(t);
    }

    @Override
    public Iterable<T> saveAll(Iterable<T> t) {
        t.forEach(p->{

        });
        return repository.saveAll(t);
    }

    @Override
    public T update(T t) {

        return repository.save(t);
    }

    @Override
    public void delete(T t) { /**asla silmiyoruz aktifliğini değiştiriyoruz*/

        repository.save(t);
    }

    @Override
    public void deleteById(ID id) {
        Optional<T> t = repository.findById(id);  /**asla silmiyoruz aktifliğini değiştiriyoruz*/
         /**aratılan id varsa aktifliğini değiştirip kaydediyoruz.*/
        if(t.isPresent())

        repository.save(t.get());

    }

    @Override
    public Iterable<T> findAll() {

        return repository.findAll();
    }

    @Override
    public Optional<T> findById(ID id) {

        return repository.findById(id);
    }


}
