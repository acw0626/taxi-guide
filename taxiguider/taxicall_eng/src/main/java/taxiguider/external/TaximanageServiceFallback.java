package taxiguider.external;

import org.springframework.stereotype.Component;

@Component
public class TaximanageServiceFallback implements TaximanageService {
	
	
	@Override
	public void requestTaxiAssign(Taximanage txMange) {
		System.out.println("Circuit breaker has been opened. Fallback returned instead. " 
				+ txMange.getId());
	}

}
