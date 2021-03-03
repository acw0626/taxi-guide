package taxiguider.external;


public class TaximanageServiceFallback implements TaximanageService {
    
	@Override
	public void 택시할당요청(Taximanage 택시관리) //, fallback = 결제이력ServiceFallback.class)
	{
		System.out.println("Circuit breaker has been opened. Fallback returned instead.");
	}
}
