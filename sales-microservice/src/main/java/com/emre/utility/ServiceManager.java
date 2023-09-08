package com.emre.utility;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/** <T> Bu sınıfı miras almak isteyen bir service hangi entity'e hizmet ediyorsa onun type
 * olarak verecek
 *
 * <ID> Bu sınıfı miras alan servisin kullanmakta olduğu id tipini belirlemesi gerekir.
 * Long,String ,Integer*/
public class ServiceManager<T extends BaseEntity ,ID> implements IService<T,ID>{

    /**T EXTENDS BASEENTİTY YAPMAYI UNUTMA !!!!!!!!!!!!!!!!!!!!!!!!**/

    /**Tüm serviceler için ortak bir kullanım sunacak ise, burada repository
     * üzerinden işlem yapabiliyor olmalı
     ***/
    private final JpaRepository<T,ID> repository;

    public ServiceManager(JpaRepository<T, ID> repository) {
        this.repository = repository;
    }

    @Override
    public T save(T t) {
        t.setCreatedate(System.currentTimeMillis());
        t.setUpdatedate(System.currentTimeMillis());
        t.setIsactive(true);
        return repository.save(t);
    }

    @Override
    public Iterable<T> saveAll(Iterable<T> t) {
        t.forEach(p->{
            p.setIsactive(true);
            p.setUpdatedate(System.currentTimeMillis());
            p.setCreatedate(System.currentTimeMillis());
        });
        return repository.saveAll(t);
    }

    @Override
    public T update(T t) {
        t.setUpdatedate(System.currentTimeMillis());
        return repository.save(t);
    }

    @Override
    public void delete(T t) { /**asla silmiyoruz aktifliğini değiştiriyoruz*/
        t.setIsactive(false);
        repository.save(t);
    }

    @Override
    public void deleteById(ID id) {
        Optional<T> t = repository.findById(id);  /**asla silmiyoruz aktifliğini değiştiriyoruz*/
         /**aratılan id varsa aktifliğini değiştirip kaydediyoruz.*/
        if(t.isPresent())
            t.get().setIsactive(false);
        repository.save(t.get());

    }

    @Override
    public List<T> findAll() {

        return repository.findAll();
    }

    @Override
    public Optional<T> findById(ID id) {

        return repository.findById(id);
    }

    public List<T> findAllActive(){
        /**Eğer veritabanında hiç kayıt yok ise ya da genellikle bigData ,mongoDb gibi vt larında
         * mevcut olmayan alanlarda filtreleme yapıldığında NullPointerException fırlatır,
         * bunun önüne geçmek için en genel tanımı ile ilgili kaydın null olup olmadığına bakılır*/
        return repository.findAll().stream()
                // .filter(x->x.isIsactive()!=null)
                .filter(
                x->x.isIsactive())
                .collect(Collectors.toList());
    }
}
