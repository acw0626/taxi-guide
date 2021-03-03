package taxiguider;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface TaxicallRepository extends PagingAndSortingRepository<Taxicall, Long>{

//	Optional<택시호출> findBy휴대폰번호(String 휴대폰번호);
}