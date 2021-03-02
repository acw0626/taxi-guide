package taxiguider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import taxiguider.config.kafka.KafkaProcessor;

@Service
public class TaxiassignPolicyHandler{
	@Autowired TaxiassignRepository AssignRepository;
    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }
    
    private static String[][] driverBank = 
	{
		{"백영곤", "010-2345-6789", "34가4567"},
		{"안채우", "010-3345-7789", "44나4567"},
		{"임광현", "010-4345-8789", "54다4567"},
		{"장윤정", "010-5345-9789", "64라4567"},
		{"옥준삼", "010-6345-0789", "74마4567"},
		{"유승오", "010-7345-1789", "84사4567"}
	};
	
	public static TaxiassignCompleted getTaxiassigned() {
		TaxiassignCompleted 할당확인됨 = new TaxiassignCompleted();
		
		int randDriver = (int)(Math.random() * 6);
		할당확인됨.setDriver(driverBank[randDriver][0]);
		할당확인됨.setDrivertel(driverBank[randDriver][1]);
        할당확인됨.setTaxiid(driverBank[randDriver][2]);
        return 할당확인됨;
	}
    
    //private String 호출상태; //호출,호출중,호출확정,호출취소
    @StreamListener(KafkaProcessor.INPUT)
    public void whenever택시할당요청됨_(@Payload TaximanageAssigned txAssigned){
    	System.out.println("##### EVT TYPE[택시할당요청됨]  : " + txAssigned.getEventType());
        if(txAssigned.isMe()){
            System.out.println("##### listener  : " + txAssigned.toJson());
            
            if(txAssigned.getStatus() != null  && txAssigned.getStatus().equals("호출중"))
            {
            	
            	txAssigned.setStatus("호출확정");
            	//할당확인됨 할당확인됨 = Assigner.get택시할당됨();
            	//BeanUtils.copyProperties(택시할당요청됨, 할당확인됨);
            	//할당확인됨.setEventType("할당확인됨");
            	txAssigned.publish();
            	
            	TaxiassignCompleted 할당확인됨 = getTaxiassigned();
            	할당확인됨.setId(txAssigned.getId());
            	할당확인됨.setStatus("할당확정");
                할당확인됨.setTel(txAssigned.getTel());
                할당확인됨.setLocation(txAssigned.getLocation());
            	할당확인됨.setEventType("TaxiassignCompleted");
            	//택시할당요청됨.publishAfterCommit();
            	할당확인됨.publish(); 
            }  
        }
    }
    
    @StreamListener(KafkaProcessor.INPUT)
    public void whenever할당확인됨_(@Payload TaxiassignCompleted 할당확인됨){
    	System.out.println("##### EVT TYPE[할당확인됨]  : " + 할당확인됨.getEventType());
        if(할당확인됨.isMe()){
            System.out.println("##### listener  : " + 할당확인됨.toJson());
            
            if(할당확인됨.getStatus() != null  && 할당확인됨.getStatus().equals("할당확정"))
            {
            	
//            	할당확인됨 할당확인됨 = Assigner.get택시할당됨();
//            	BeanUtils.copyProperties(택시할당요청됨, 할당확인됨);
//            	
//                //할당확인됨.setEventType("할당확인됨");
//            	할당확인됨.setEventType("할당확인됨");
//            	//택시할당요청됨.publishAfterCommit();
//            	할당확인됨.publish(); 
            }  
        }
    }
    
    
    @StreamListener(KafkaProcessor.INPUT)
    public void whenever택시할당취소됨_(@Payload TaximanageCancelled 택시할당취소됨){
    	
        if(택시할당취소됨.isMe()){
            System.out.println("##### listener  : " + 택시할당취소됨.toJson());
            
            
            AssignRepository.findById(Long.valueOf(택시할당취소됨.getId())).ifPresent((taxiassign) -> {
            	taxiassign.setStatus("할당취소");
				AssignRepository.save(taxiassign);
			});
            
            택시할당취소됨.publish();
        }
    }

}
