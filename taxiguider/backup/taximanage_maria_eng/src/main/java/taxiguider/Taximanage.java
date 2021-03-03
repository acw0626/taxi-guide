package taxiguider;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

@Entity
@Table(name="Taximanage_table")
public class Taximanage {
	
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    private String orderId;
    private String tel;
    private String location;
    private String status; //호출,호출중,호출확정,호출취소
    private Integer cost;
    
    private String taxiid;
    private String driver;
    private String drivertel;
    
    @PrePersist
    public void onPrePersist(){
    	System.out.println("###############################=================================");

//    	택시할당요청됨 택시할당요청됨 = new 택시할당요청됨();
//        BeanUtils.copyProperties(this, 택시할당요청됨);
//        택시할당요청됨.publishAfterCommit();
        System.out.println("휴대폰번호 " + tel);
        System.out.println("호출위치 " + location);
        System.out.println("호출상태 " + status);
        System.out.println("예상요금 " + cost);
    	
        System.out.println("orderId " + orderId);
        System.out.println("id " + getId());
        //System.out.println("호출위치 " + 호출위치);
        //System.out.println("호출상태 " + 호출상태);
        //System.out.println("예상요금 " + 예상요금);
    	
        
        if("호출취소".equals(status)){
			TaximanageCancelled txCanceled = new TaximanageCancelled();
            BeanUtils.copyProperties(this, txCanceled);
            txCanceled.publish();

        }else{
//            결제승인됨 결제승인됨 = new 결제승인됨();
//            BeanUtils.copyProperties(this, 결제승인됨);
//
//            //바로 이벤트를 보내버리면 주문정보가 커밋되기도 전에 배송발송됨 이벤트가 발송되어 주문테이블의 상태가 바뀌지 않을 수 있다.
//            // TX 리스너는 커밋이 완료된 후에 이벤트를 발생하도록 만들어준다.
//            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
//                @Override
//                public void beforeCommit(boolean readOnly) {
//                    결제승인됨.publish();
//                }
//            });
        	
//        	택시번호 = "";
//            택시기사이름 = "";
//            택시기사전화번호 = "";
//            orderId = "1";
//            고객휴대폰번호 = "";
//            호출위치 = "";
//            호출상태 = ""; //호출,호출중,호출확정,호출취소
//            예상요금 = 0;
            
        	status = "호출중";
        	TaximanageAssigned txassigned = new TaximanageAssigned();
        	txassigned.setId(Long.valueOf(orderId));
        	
        	txassigned.setLocation(location);
        	txassigned.setTel(tel);
        	txassigned.set예상요금(cost);
        	txassigned.set호출상태(status);
            BeanUtils.copyProperties(this, txassigned);
            txassigned.publishAfterCommit();
            
            
            // 테스트 코드~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//            try {
//                Thread.currentThread().sleep((long) (400 + Math.random() * 220));
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }    
    }
    
    
//    @PostPersist
//    public void onPostPersist(){
//    	System.out.println("###############################=================================");
//
////    	택시할당요청됨 택시할당요청됨 = new 택시할당요청됨();
////        BeanUtils.copyProperties(this, 택시할당요청됨);
////        택시할당요청됨.publishAfterCommit();
//        System.out.println("휴대폰번호 " + 고객휴대폰번호);
//        System.out.println("호출위치 " + 호출위치);
//        System.out.println("호출상태 " + 호출상태);
//        System.out.println("예상요금 " + 예상요금);
//    	
//        System.out.println("orderId " + orderId);
//        System.out.println("id " + getId());
//        //System.out.println("호출위치 " + 호출위치);
//        //System.out.println("호출상태 " + 호출상태);
//        //System.out.println("예상요금 " + 예상요금);
//    	
//        
//        if("호출취소".equals(호출상태)){
////            결제취소됨 결제취소됨 = new 결제취소됨();
////            BeanUtils.copyProperties(this, 결제취소됨);
////            결제취소됨.publish();
////        	택시할당취소됨 택시할당취소됨 = new 택시할당취소됨();
////            BeanUtils.copyProperties(this, 택시할당취소됨);
////            택시할당취소됨.publish();
//
//        }else{
////            결제승인됨 결제승인됨 = new 결제승인됨();
////            BeanUtils.copyProperties(this, 결제승인됨);
////
////            //바로 이벤트를 보내버리면 주문정보가 커밋되기도 전에 배송발송됨 이벤트가 발송되어 주문테이블의 상태가 바뀌지 않을 수 있다.
////            // TX 리스너는 커밋이 완료된 후에 이벤트를 발생하도록 만들어준다.
////            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
////                @Override
////                public void beforeCommit(boolean readOnly) {
////                    결제승인됨.publish();
////                }
////            });
//        	
////        	택시번호 = "";
////            택시기사이름 = "";
////            택시기사전화번호 = "";
////            orderId = "1";
////            고객휴대폰번호 = "";
////            호출위치 = "";
////            호출상태 = ""; //호출,호출중,호출확정,호출취소
////            예상요금 = 0;
//            
//        	호출상태 = "호출중";
//        	택시할당요청됨 택시할당요청됨 = new 택시할당요청됨();
//        	택시할당요청됨.setId(Long.valueOf(orderId));
//        	
//        	택시할당요청됨.set고객위치(호출위치);
//        	택시할당요청됨.set고객휴대폰번호(고객휴대폰번호);
//        	택시할당요청됨.set예상요금(예상요금);
//        	택시할당요청됨.set호출상태(호출상태);
//            BeanUtils.copyProperties(this, 택시할당요청됨);
//            택시할당요청됨.publishAfterCommit();
//            
//            
//            // 테스트 코드~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
////            try {
////                Thread.currentThread().sleep((long) (400 + Math.random() * 220));
////            } catch (InterruptedException e) {
////                e.printStackTrace();
////            }
//        }     
//    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


	public String get고객휴대폰번호() {
		return 고객휴대폰번호;
	}


	public void set휴대폰번호(String 휴대폰번호) {
		this.고객휴대폰번호 = 휴대폰번호;
	}


	public String get호출위치() {
		return 호출위치;
	}


	public void set호출위치(String 호출위치) {
		this.호출위치 = 호출위치;
	}
	
	public String get호출상태() {
		return 호출상태;
	}
	public void set호출상태(String 호출상태) {
		this.호출상태 = 호출상태;
	}

	public Integer get예상요금() {
		return 예상요금;
	}


	public void set예상요금(Integer 예상요금) {
		this.예상요금 = 예상요금;
	}


	public String get택시번호() {
		return 택시번호;
	}


	public void set택시번호(String 택시번호) {
		this.택시번호 = 택시번호;
	}


	public String get택시기사이름() {
		return 택시기사이름;
	}


	public void set택시기사이름(String 택시기사이름) {
		this.택시기사이름 = 택시기사이름;
	}


	public String get택시기사전화번호() {
		return 택시기사전화번호;
	}


	public void set택시기사전화번호(String 택시기사전화번호) {
		this.택시기사전화번호 = 택시기사전화번호;
	}


	public String getOrderId() {
		return orderId;
	}


	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}


	public String getTel() {
		return tel;
	}


	public void setTel(String tel) {
		this.tel = tel;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public Integer getCost() {
		return cost;
	}


	public void setCost(Integer cost) {
		this.cost = cost;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getTaxiid() {
		return taxiid;
	}


	public void setTaxiid(String taxiid) {
		this.taxiid = taxiid;
	}


	public String getDriver() {
		return driver;
	}


	public void setDriver(String driver) {
		this.driver = driver;
	}


	public String getDrivertel() {
		return drivertel;
	}


	public void setDrivertel(String drivertel) {
		this.drivertel = drivertel;
	}




}
