package taxiguider;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface TaximanageRepository extends PagingAndSortingRepository<Taximanage, Long>{

	//Optional<Taximanage> findBy고객휴대폰번호(String get고객휴대폰번호);


}